package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action {
    public String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session = request.getSession();

        if (session.getAttribute("teacher") != null) {
            session.removeAttribute("teacher");
            // ログアウト後に "/" にリダイレクトする
            response.sendRedirect(request.getContextPath() + "/student/");
            return null; // リダイレクトしたので、ここで処理を終了する
        }

        return "login.jsp"; // teacher がセッションにない場合は通常のメニューページに遷移する
    }
}
