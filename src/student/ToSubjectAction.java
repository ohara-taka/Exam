package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class ToSubjectAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {


		// FrontControllerを使用しているためreturn文でフォワードできる
		return "subject.jsp";
	}
}
