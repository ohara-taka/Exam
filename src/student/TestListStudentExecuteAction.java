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

public class TestListStudentExecuteAction2 extends Action {


	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {

		Teacher teacher = Util.getUser(request);

		String entYearStr = request.getParameter("f1");
		String classNum = request.getParameter("f2");
		String subjectName = request.getParameter("f3");

		int entYear = 0; // 入学年度
		LocalDate todaysDate = LocalDate.now(); // LocalDateインスタンスを取得
		int year = todaysDate.getYear(); // 現在の年を取得
		ClassNumDao classNumDao = new ClassNumDao(); // クラス番号Daoを初期化

		SubjectDao subjectDao = new SubjectDao();

//		Map<String, String> errors = new HashMap<>(); // エラーメッセージ

		//      ビジネスロジック
		if (entYearStr != null) {
			// 数値に変換
			entYear = Integer.parseInt(entYearStr);
		}

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

		//レスポンス値をセット
        //リクエストに入学年度をセット
		request.setAttribute("f1", entYear);
		request.setAttribute("f2", classNum);
		request.setAttribute("f3", subjectName);

        //リクエストにクラス番号をセット
		request.setAttribute("ent_year_set", entYearSet);
		request.setAttribute("class_num_set", list);
		request.setAttribute("subject_list_set", subjects);

        // FrontControllerを使用しているためreturn文でフォワードできる
		request.getRequestDispatcher("test_list.jsp").forward(request, response);

        return null; // 戻り値を追加



		//
//		if (entYear != 0 && !classNum.equals("0")) {
//			// 入学年度とクラス番号を指定
//			students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
//		} else if (entYear != 0 && classNum.equals("0")) {
//			// 入学年度のみ指定
//			students = sDao.filter(teacher.getSchool(), entYear, isAttend);
//		} else if ((entYear == 0 && classNum == null) || (entYear == 0 && classNum.equals("0"))) {
//			// 指定なしの場合
//			students = sDao.filter(teacher.getSchool(), isAttend);
//		} else {
//			errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
//			request.setAttribute("errors", errors);
//			// 全学生情報を取得
//			students = sDao.filter(teacher.getSchool(), isAttend);
//		}

	}



//	private void setTestListSubject(HttpServletRequest request, HttpServletResponse response
//			) throws Exception {
//
//	}


//	private void setTestListStudent(HttpServletRequest request, HttpServletResponse response
//			) throws Exception {
//
//	}

}
