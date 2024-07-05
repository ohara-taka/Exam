package student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
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

            String studentNo = request.getParameter("f4");
            request.setAttribute("f4", studentNo);

            // クラス番号と科目情報を取得
            ClassNumDao classNumDao = new ClassNumDao();
            StudentDao studentDao = new StudentDao();
            Student student = studentDao.get(studentNo);

            // 入学年度のリストを設定
            LocalDate todaysDate = LocalDate.now();
            int year = todaysDate.getYear();
            List<Integer> entYearSet = new ArrayList<>();
            for (int i = year - 10; i <= year + 10; i++) {
                entYearSet.add(i);
            }

            // クラス番号と科目情報を取得
            List<String> classNumList = classNumDao.filter(teacher.getSchool());
            List<Subject> subjectList = new SubjectDao().filter(teacher.getSchool());

            // フォームに必要な情報を常に設定する
            request.setAttribute("class_num_set", classNumList);
            request.setAttribute("subject_list_set", subjectList);
            request.setAttribute("ent_year_set", entYearSet);

            if (student == null) {
                request.setAttribute("studentNotFound", true);
                request.setAttribute("test_list_students", new ArrayList<TestListStudent>());

            } else {
                String studentName = student.getName();
                request.setAttribute("studentName", studentName);

                TestListStudentDao testListStudentDao = new TestListStudentDao();
                List<TestListStudent> testListStudents = testListStudentDao.filter(student);

                // ここでnull値の点数を「－」に変更
                for (TestListStudent test : testListStudents) {
                    if (test.getPoint() == null) {
                        test.setPoint(null); // nullをセットするだけで、"－" ではなく null にする
                    }
                }

                request.setAttribute("test_list_students", testListStudents);
            }

            // FrontControllerを使用しているためreturn文でフォワードできる
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
        }

        return null; // 戻り値を追加
    }
}
