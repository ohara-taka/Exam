package student;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import util.Util;

@WebServlet("/somepath")
public class kensho_you extends HttpServlet {

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
