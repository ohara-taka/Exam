package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		SubjectDao dao=new SubjectDao();
		List<Subject> subjectList=dao.subjectAll();

		request.setAttribute("subjectList", subjectList);


		// FrontControllerを使用しているためreturn文でフォワードできる
		return "subject_list.jsp";
	}
}
