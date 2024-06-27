package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;
import util.Util;


public class TestListStudentExecuteAction extends Action {


	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {

        // ティーチャー情報を取得
        Teacher teacher = Util.getUser(request);

		String studentName = request.getParameter("f4");

		int entYear = 0; // 入学年度

        // クラス番号と科目情報を取得
        ClassNumDao classNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();
        TestListSubjectDao testListSubjectDao = new TestListSubjectDao();
        List<TestListSubject> testListSubjects = null; // 学生リスト
        List<String> classNumList = classNumDao.filter(teacher.getSchool());
        List<Subject> subjectList = subjectDao.filter(teacher.getSchool());


        // 入学年度のリストを設定
//        LocalDate todaysDate = LocalDate.now();
//        int year = todaysDate.getYear();
//        List<Integer> entYearSet = new ArrayList<>();
//        for (int i = year - 10; i <= year + 10; i++) {
//            entYearSet.add(i);
//        }
//
//        testListSubjects = testListSubjectDao.filter(teacher.getSchool(), entYear, classNum, subjectName);

        //デフォルト値の再セット
		//レスポンス値をセット
        //リクエストに入学年度をセット
		request.setAttribute("f4", studentName);


        // FrontControllerを使用しているためreturn文でフォワードできる
		request.getRequestDispatcher("test_list.jsp").forward(request, response);

        return null; // 戻り値を追加


	}



}
