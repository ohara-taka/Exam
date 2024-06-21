package student;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/student/*")
public class LoginRequiredFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // フィルター初期化時の処理（必要に応じて実装）
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // ログイン画面（login.jsp）はフィルターをスキップする
        String loginURI = request.getContextPath() + "/student/";
        boolean loggedIn = false;

        // セッションからTeacherオブジェクトを取得してログイン状態を確認
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("teacher") != null) {
            loggedIn = true;
        }

        // ログイン済みの場合は次のフィルターを呼び出す
        if (loggedIn || request.getRequestURI().equals(loginURI)) {
            chain.doFilter(request, response);
        } else {
            // ログインしていない場合はログイン画面にリダイレクト
            response.sendRedirect(request.getContextPath() + "/student/");
        }
    }

    @Override
    public void destroy() {
        // フィルターの破棄時の処理（必要に応じて実装）
    }
}
