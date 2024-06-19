package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import dao.TeacherDao;
import tool.Action;


public class StudentCreateAction extends Action {
<<<<<<< Updated upstream
    @Override
    public String execute(
            HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        // ClassNumDaoを使用してクラス番号のリストを取得
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumList = classNumDao.filter(); // List<String> に変更
=======
>>>>>>> Stashed changes

	@Override
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session = request.getSession(); // セッション
		Teacher teacher = (Teacher)session.getAttribute("user");



		// FrontControllerを使用しているためreturn文でフォワードできる
		return "student_create.jsp";
	}
}
