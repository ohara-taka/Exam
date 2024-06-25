package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cd = request.getParameter("cd"); // フォームから科目コードを取得

        SubjectDao subjectDao = new SubjectDao();
        boolean deleted = subjectDao.deleteByCd(cd); // Subjectを削除し、結果を取得

        if (deleted) {
            return "subject_delete_done.jsp"; // 削除成功時はsubject_delete_done.jspにリダイレクト
        } else {
            // 削除が失敗した場合の処理（エラー処理など）
            // ここではエラー処理の例として、削除失敗を通知するページにフォワードすることを示します
            return "delete_error.jsp"; // 例: delete_error.jspにフォワード
        }
    }
}
