package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class ToSubjectUpdateAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {


		// FrontControllerを使用しているためreturn文でフォワードできる
		return "subject_update.jsp";
	}
}
