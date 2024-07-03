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
    public String execute(HttpServletRequest req, HttpServletResponse response) throws Exception {

        Teacher teacher = Util.getUser(req);

        ClassNumDao classNumDao = new ClassNumDao(); // クラス番号Daoを初期化

        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3");
        String numberStr = req.getParameter("f4");

        System.out.println("entYearStr: " + entYearStr);
        System.out.println("classNum: " + classNum);
        System.out.println("subjectCd: " + subjectCd);
        System.out.println("numberStr: " + numberStr);

        int entYear = 0; // 入学年度
        int number = 0; // 回数

        if (teacher != null) {
            School school = teacher.getSchool();
            SubjectDao subjectDao = new SubjectDao();
            List<Subject> subjectList = subjectDao.filter(school);
            req.setAttribute("subjectList", subjectList);
            req.setAttribute("schoolCd", school.getCd());

            Subject subject = subjectDao.get(subjectCd, school.getCd());
            req.setAttribute("subjectName",subject.getName());

            System.out.println("subjectName:" + subject.getName());

            req.setAttribute("subjectCd",subject.getCd());
        }

        // DBからデータ取得
        // ログインユーザーの学校コードをもとにクラス番号の一覧を取得
        List<String> list = classNumDao.filter(teacher.getSchool());
        req.setAttribute("class_num_set", list);

        // テスト呼び出し
        TestDao testDao = new TestDao();
        List<Test> testList = null;

        try {
            if (entYearStr != null && !entYearStr.isEmpty()) {
                entYear = Integer.parseInt(entYearStr);
            }
            if (numberStr != null && !numberStr.isEmpty()) {
                number = Integer.parseInt(numberStr);
            }

            // subjectCdからSubjectオブジェクトを作成
            Subject subject = new Subject();
            subject.setCd(subjectCd);

            System.out.println("Before calling testDao.filter()");
            testList = testDao.filter(entYear, classNum, subject, number, teacher.getSchool());
            System.out.println("After calling testDao.filter()");
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("testList", testList);
        req.setAttribute("f1", entYearStr);
        req.setAttribute("f2", classNum);
        req.setAttribute("f4", numberStr);

        // FrontControllerを使用しているためreturn文でフォワードできる
        req.getRequestDispatcher("test-regist.jsp").forward(req, response);
        return null; // 戻り値を追加
    }
}
