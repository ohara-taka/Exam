package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassNum;
import bean.Subject;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		//クラス呼び出し
		ClassNumDao classNumDao = new ClassNumDao();
	    List<ClassNum> classNumList = null;

	    try {
	    	classNumList = classNumDao.classNumAll();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    request.setAttribute("classNumList", classNumList);

	    //subject呼び出し
	    SubjectDao subjectDao = new SubjectDao();
	    List<Subject> subjectList = null;

	    try {
	        subjectList = subjectDao.subjectAll();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    request.setAttribute("subjectList", subjectList);


	  //テスト呼び出し
		TestDao testDao = new TestDao();
		    List<Test> testList = null;

		    try {
		    	testList = testDao.testAll();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    request.setAttribute("testList", testList);




		// FrontControllerを使用しているためreturn文でフォワードできる
		return "test-regist.jsp";
	}
}
