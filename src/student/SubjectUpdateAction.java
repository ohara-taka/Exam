package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cd = request.getParameter("cd"); // クエリパラメータから科目コードを取得

        if (cd != null) {
            SubjectDao subjectDao = new SubjectDao();
            Subject subject = subjectDao.get(cd); // 新しいgetメソッドを使用してSubjectを取得

            if (subject != null) {
                request.setAttribute("subject", subject); // 取得したSubjectをリクエスト属性に設定
            }
        }

        // FrontControllerを使用しているためreturn文でフォワードできる
        return "subject_update.jsp";
    }
}
