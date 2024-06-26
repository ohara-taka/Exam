package student;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Test;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;



public class TestRegistAction extends Action {
	public String execute(HttpServletRequest req, HttpServletResponse response)throws Exception {




	    //subject呼び出し
	    SubjectDao subjectDao = new SubjectDao();
	    List<Subject> subjectList = null;

	    try {
	        subjectList = subjectDao.subjectAll();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    req.setAttribute("subjectList", subjectList);


	  //テスト呼び出し
		TestDao testDao = new TestDao();
		    List<Test> testList = null;

		    try {
		    	testList = testDao.testAll();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    req.setAttribute("testList", testList);




		// FrontControllerを使用しているためreturn文でフォワードできる
		return "test-regist.jsp";
	}
}
