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

        // セッションから teacher を削除して無効化する
        session.removeAttribute("teacher");
        session.invalidate(); // セッションを無効化する

        // ログアウト後にログアウト画面へリダイレクトする
        response.sendRedirect(request.getContextPath() + "/student/logout.jsp");
        return null; // リダイレクトしたので、ここで処理を終了する
    }
}
