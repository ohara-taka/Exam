package student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;
import util.Util;

public class TestListAction extends Action {


	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {

		setTestListSubject(request,response);


        // FrontControllerを使用しているためreturn文でフォワードできる
		request.getRequestDispatcher("test_list.jsp").forward(request, response);


        return null; // 戻り値を追加

	}



	private void setTestListSubject(HttpServletRequest request, HttpServletResponse response
			) throws Exception {

		Teacher teacher = Util.getUser(request);

		LocalDate todaysDate = LocalDate.now(); // LocalDateインスタンスを取得
		int year = todaysDate.getYear(); // 現在の年を取得
		ClassNumDao classNumDao = new ClassNumDao(); // クラス番号Daoを初期化

		SubjectDao subjectDao = new SubjectDao();

        // リストを初期化
        List<Integer> entYearSet = new ArrayList<>();
        // 10年前から1年後まで年をリストに追加
        for (int i = year - 10; i <= year + 10; i++) {
            entYearSet.add(i);
        }
		//       DBからデータ取得
		//       ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = classNumDao.filter(teacher.getSchool());

		List<Subject> subjects = subjectDao.filter(teacher.getSchool());

        //リクエストにクラス番号をセット
		request.setAttribute("ent_year_set", entYearSet);
		request.setAttribute("class_num_set", list);
		request.setAttribute("subject_list_set", subjects);

	}


//	private void setTestListStudent(HttpServletRequest request, HttpServletResponse response
//			) throws Exception {
//
//	}

}
