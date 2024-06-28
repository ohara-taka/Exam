package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;
import util.Util;


public class StudentUpdateAction extends Action {
    @Override
    public String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        String studentNo = request.getParameter("no");

        // セッションからTeacherオブジェクトを取得

    	Teacher teacher = Util.getUser(request);

//        HttpSession session = request.getSession();
//        Teacher teacher = (Teacher) session.getAttribute("user");

        // ClassNumDaoを使用してクラス番号のリストを取得
        ClassNumDao classNumDao = new ClassNumDao();

        // ClassNumDaoを使用してクラス番号のリストを取得
        StudentDao studentDao = new StudentDao();

        List<String> classNumList = classNumDao.filter(teacher.getSchool()); // Schoolオブジェクトを渡さない


        Student student = studentDao.get(studentNo); // Schoolオブジェクトを渡さない


        //レスポンス値をセット
        //リクエストに名前・クラスをセット
        request.setAttribute("ent_year", student.getEntYear());
        request.setAttribute("no", student.getNo());
        request.setAttribute("name", student.getName());
        request.setAttribute("class_num", student.getClassNum());


        // リクエストにクラス番号のリストをセット
        request.setAttribute("classNumList", classNumList);

        request.getRequestDispatcher("student_update.jsp").forward(request, response);
        return null;

    }

}

