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

@WebServlet("/subject/update")
public class StudentUpdateExecuteAction extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // フォームから送信されたデータを取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // セッションからTeacherオブジェクトを取得し、nullチェック
        HttpSession session = request.getSession();
        Teacher teacher = Util.getUser(request);
        if (teacher == null) {
            System.out.println("Teacher object is null");
            response.sendRedirect(request.getContextPath() + "/student/error.jsp");
            return;
        }

        // TeacherオブジェクトからSchoolオブジェクトを取得
        School school = teacher.getSchool();
        if (school == null) {
            System.out.println("School object is null");
            response.sendRedirect(request.getContextPath() + "/student/error.jsp");
            return;
        }
        String schoolCd = school.getCd(); // 学校コードを取得

        // Subjectオブジェクトを作成
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school); // 学校オブジェクトを設定

        // DAOを使ってデータベースを更新
        SubjectDao subjectDao = new SubjectDao();
        boolean updated = false;
        try {
            updated = subjectDao.update(subject);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/student/error.jsp");
            return;
        }

        // 更新結果に応じてリダイレクト先を設定
        if (updated) {
            response.sendRedirect(request.getContextPath() + "/student/subject_update_done.jsp");
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
