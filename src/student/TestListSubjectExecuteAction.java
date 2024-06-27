package student;

import java.time.LocalDate;
import java.util.ArrayList;
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


public class TestListSubjectExecuteAction extends Action {


	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {

        // ティーチャー情報を取得
        Teacher teacher = Util.getUser(request);

		String entYearStr = request.getParameter("f1");
		String classNum = request.getParameter("f2");
		String subjectName = request.getParameter("f3");

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
        SubjectDao subjectDao = new SubjectDao();
        TestListSubjectDao testListSubjectDao = new TestListSubjectDao();
        List<TestListSubject> testListSubjects = null; // 学生リスト
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

        //デフォルト値の再セット
		//レスポンス値をセット
        //リクエストに入学年度をセット
		request.setAttribute("f1", entYear);
		request.setAttribute("f2", classNum);
		request.setAttribute("f3", subjectName);

		//検索後の科目名表示の所のセット
		request.setAttribute("subjectName", subjectName);

        // リクエストにクラス番号と科目情報を設定
        request.setAttribute("class_num_set", classNumList);
        request.setAttribute("subject_list_set", subjectList);
        request.setAttribute("ent_year_set", entYearSet);


        //リクエストに得点リストをセット
        request.setAttribute("test_list_subjects", testListSubjects);


        // FrontControllerを使用しているためreturn文でフォワードできる
		request.getRequestDispatcher("test_list.jsp").forward(request, response);

        return null; // 戻り値を追加


	}



}
