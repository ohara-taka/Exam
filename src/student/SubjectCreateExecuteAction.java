package student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import util.Util;

@WebServlet("/subject/create")
public class SubjectCreateExecuteAction extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // フォームから送信されたデータを取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // エラーメッセージを保持する変数
        String errorMessage = null;

        // 科目コードの長さをチェック
        if (cd == null || cd.length() != 3) {
            errorMessage = "科目コードは３文字で入力してください";
        }

        // サーブレットからTeacherオブジェクトを取得し、nullチェック
        HttpSession session = request.getSession();
        Teacher teacher = Util.getUser(request);
        if (teacher == null) {
            response.sendRedirect(request.getContextPath() + "/student/error.jsp");
            return;
        }

        // TeacherオブジェクトからSchoolオブジェクトを取得
        School school = teacher.getSchool();
        if (school == null) {
            response.sendRedirect(request.getContextPath() + "/student/error.jsp");
            return;
        }

        // 重複チェック
        SubjectDao subjectDao = new SubjectDao();
        try {
            if (subjectDao.get(cd, school.getCd()) != null) {
                errorMessage = "科目コードが重複しています";
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/student/error.jsp");
            return;
        }

        // エラーメッセージがある場合はフォームに戻る
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/student/subject_create.jsp").forward(request, response);
            return;
        }

        // Subjectオブジェクトを作成
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school); // 学校オブジェクトを設定

        // DAOを使ってデータベースに保存
        boolean saved = false;
        try {
            saved = subjectDao.save(subject);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/student/error.jsp");
            return;
        }

        // 保存結果に応じてリダイレクト先を設定
        if (saved) {
            response.sendRedirect(request.getContextPath() + "/student/subject_create_done.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/student/error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Teacher teacher = Util.getUser(request);

        if (teacher != null) {
            // セッションから取得したTeacherオブジェクトを使って何かを行う
            response.getWriter().println("Hello " + teacher.getName());
            response.getWriter().println(teacher.getSchool().getCd());
        } else {
            response.getWriter().println("Teacher not found in session");
        }
    }
}
