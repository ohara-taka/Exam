package student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Subject;
//import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import dao.TestListSubjectDao;
import tool.Action;
import util.Util;


public class TestListStudentExecuteAction extends Action {


	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {

		String StAction = request.getParameter("action");

		if ("st".equals(StAction)) {

			// ティーチャー情報を取得
			Teacher teacher = Util.getUser(request);

			String entYearStr = request.getParameter("f1");
			String classNum = request.getParameter("f2");
			String subjectName = request.getParameter("f3");
			String studentNo = request.getParameter("f4");

			int entYear = 0; // 入学年度

			//entYearStrをint型のentYearに変換
			if (entYearStr != null && !entYearStr.isEmpty()) {
				try {
					entYear = Integer.parseInt(entYearStr);
				} catch (NumberFormatException e) {
					entYear = 0; // デフォルト値を設定
				}
			}


			// クラス番号と科目情報を取得
			ClassNumDao classNumDao = new ClassNumDao();
			StudentDao studentDao = new StudentDao();
			Student student = studentDao.get(studentNo);
			String studentName = student.getName();

			SubjectDao subjectDao = new SubjectDao();
			TestListSubjectDao testListSubjectDao = new TestListSubjectDao();
			TestListStudentDao testListStudentDao = new TestListStudentDao();
			List<TestListSubject> testListSubjects = null; // 学生リスト
			List<TestListStudent> testListStudents = null; // 学生リスト
			List<String> classNumList = classNumDao.filter(teacher.getSchool());
			List<Subject> subjectList = subjectDao.filter(teacher.getSchool());


			// 入学年度のリストを設定
			LocalDate todaysDate = LocalDate.now();
			int year = todaysDate.getYear();
			List<Integer> entYearSet = new ArrayList<>();
			for (int i = year - 10; i <= year + 10; i++) {
				entYearSet.add(i);
			}

			testListSubjects = testListSubjectDao.filter(teacher.getSchool(), entYear, classNum, subjectName);
			testListStudents = testListStudentDao.filter(student);

			//デフォルト値の再セット
			//レスポンス値をセット
			//リクエストに入学年度をセット
			request.setAttribute("f1", entYear);
			request.setAttribute("f2", classNum);
			request.setAttribute("f3", subjectName);
			request.setAttribute("f4", studentNo);

			//検索後の科目名表示の所のセット
			request.setAttribute("subjectName", subjectName);

			// リクエストにクラス番号と科目情報を設定
			request.setAttribute("class_num_set", classNumList);
			request.setAttribute("subject_list_set", subjectList);
			request.setAttribute("ent_year_set", entYearSet);

			request.setAttribute("studentName", studentName);



			//リクエストに得点リストをセット
			request.setAttribute("test_list_subjects", testListSubjects);
			request.setAttribute("test_list_students", testListStudents);


			// FrontControllerを使用しているためreturn文でフォワードできる
			request.getRequestDispatcher("test_list.jsp").forward(request, response);

		}

		return null; // 戻り値を追加


	}



}
