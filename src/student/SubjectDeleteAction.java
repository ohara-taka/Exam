package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cd = request.getParameter("cd"); // クエリパラメータから科目コードを取得

        if (cd != null) {
            SubjectDao subjectDao = new SubjectDao();
            Subject subject = subjectDao.get(cd); // 科目コードを使用してSubjectを取得

            if (subject != null) {
                request.setAttribute("subjectCd", subject.getCd()); // 取得したSubjectの科目コードをリクエスト属性に設定
                request.setAttribute("subjectName", subject.getName()); // 取得したSubjectの科目名をリクエスト属性に設定
            }
        }

        // Forward to subject_delete.jsp
        return "subject_delete.jsp";
    }
}
