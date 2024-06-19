package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;
import util.Util;

public class StudentCreateAction extends Action {
    @Override
    public String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        // セッションからTeacherオブジェクトを取得

    	Teacher teacher = Util.getUser(request);

//        HttpSession session = request.getSession();
//        Teacher teacher = (Teacher) session.getAttribute("user");

        // ClassNumDaoを使用してクラス番号のリストを取得
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumList = classNumDao.filter(teacher.getSchool()); // Schoolオブジェクトを渡さない

        // リクエストにクラス番号のリストをセット
        request.setAttribute("classNumList", classNumList);

        // JSPページにフォワードする
        return "student_create.jsp";
    }
}

