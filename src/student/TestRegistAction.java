package student;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;
import util.Util;



public class TestRegistAction extends Action {
	public String execute(HttpServletRequest req, HttpServletResponse response)throws Exception {

        Teacher teacher = Util.getUser(req);

        ClassNumDao classNumDao = new ClassNumDao(); // クラス番号Daoを初期化

        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String subjectName = req.getParameter("f3");
        String numberStr = req.getParameter("f4");

		int entYear = 0; // 入学年度
		int number = 0; // 入学年度


	    if (teacher != null) { // Teacherオブジェクトが存在する場合
            School school = teacher.getSchool(); // TeacherからSchoolオブジェクトを取得
            SubjectDao subjectDao = new SubjectDao(); // SubjectDaoのインスタンスを生成
            List<Subject> subjectList = subjectDao.filter(school); // school_cdに基づいて科目をフィルタリング
            req.setAttribute("subjectList", subjectList); // フィルタリングされた科目リストをリクエストに設定
            req.setAttribute("schoolCd", school.getCd()); // Schoolのcdをリクエストに設定
	}
//      DBからデータ取得
//      ログインユーザーの学校コードをもとにクラス番号の一覧を取得
	    List<String> list = classNumDao.filter(teacher.getSchool());


<<<<<<< Updated upstream
        	req.setAttribute("class_num_set", list);
=======
//	  //テスト呼び出し
		TestDao testDao = new TestDao();
		    List<Test> testList = null;


		    try {
				entYear = Integer.parseInt(entYearStr);
				number = Integer.parseInt(numberStr);
	            // subjectCdからSubjectオブジェクトを作成
	            Subject subject = new Subject();
	            subject.setCd(subjectName);

		    	testList = testDao.filter(entYear, classNum, subject, number, teacher.getSchool());
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

        	req.setAttribute("class_num_set", list);
		    req.setAttribute("testList", testList);
		    req.setAttribute("subjectName", subjectName);
>>>>>>> Stashed changes


        	  req.setAttribute("f1", entYear);
              req.setAttribute("f2", classNum);
              req.setAttribute("f3", subjectName);
              req.setAttribute("f4", number);

              // FrontControllerを使用しているためreturn文でフォワードできる
              req.getRequestDispatcher("test-regist.jsp").forward(req, response);
              return null; // 戻り値を追加

	}
  }

