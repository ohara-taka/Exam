package student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;


@WebServlet(urlPatterns={"/"})
public class LoginAction extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // GET メソッドの処理（ログイン画面へのフォワード）
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String message = "";

        if (id != null && password != null) {
            TeacherDao teacherDao = new TeacherDao();
            try {
                Teacher teacher = teacherDao.login(id, password);

                if (teacher != null) {
                    HttpSession currentSession = request.getSession();
                    currentSession.setAttribute("teacher", teacher);
                    // ログイン成功後に menu.jsp にリダイレクト
                    response.sendRedirect(request.getContextPath() + "/student/menu.jsp");
                } else {
                    message = "IDまたはパスワードが正しくありません";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                message = "ログイン処理中にエラーが発生しました";
                request.setAttribute("message", message);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            message = "IDとパスワードを入力してください";
            request.setAttribute("message", message);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
