package student;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ClassNumDao;
import tool.Action; // Actionクラスのインポート

public class StudentCreateAction extends Action {
    @Override
    public String execute(
            HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        // ClassNumDaoを使用してクラス番号のリストを取得
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumList = classNumDao.getClassNumList(); // List<String> に変更

        // リクエストにクラス番号のリストをセット
        request.setAttribute("classNumList", classNumList);

        // JSPページにフォワードする
        request.getRequestDispatcher("student_create.jsp").forward(request, response);

        return null; // この行はvoidのように見えるが、実際のActionフレームワークに依存する
    }
}
