package student;

import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import tool.Action;
import util.Util;


public class TestRegistExecuteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");


//        HttpSession session = request.getSession();
//        Teacher teacher = (Teacher) session.getAttribute("teacher"); // セッションからTeacherオブジェクトを取得

        Teacher teacher = Util.getUser(request);

        // フォームデータを取得
        String[] studentNos = request.getParameterValues("studentNo[]");
        String[] classNums = request.getParameterValues("classNum[]");
        String[] testNos = request.getParameterValues("testNo[]");
        String[] points = request.getParameterValues("points[]");
        String subjectName = request.getParameter("subjectName");
        String subjectCd = request.getParameter("subjectCd");

        request.setAttribute("subjectName", subjectName);

        System.out.println("subjectCd: " + subjectCd);

        TestDao testDao = new TestDao();


        String schoolCd = teacher.getSchool().getCd(); // TeacherからSchoolCdを取得

        try (Connection connection = testDao.getConnection()) {
            connection.setAutoCommit(false); // トランザクションを開始

            // それぞれの配列の長さが同じであることを確認
            if (studentNos.length == classNums.length && classNums.length == testNos.length && testNos.length == points.length) {
                for (int i = 0; i < studentNos.length; i++) {
                    // 各データを取得して処理
                    String studentNo = studentNos[i];
                    String classNum = classNums[i];
                    String testNo = testNos[i];
                    String point = points[i];

                    System.out.println("studentNo: " + studentNo);
                    System.out.println("classNum: " + classNum);
                    System.out.println("testNo: " + testNo);
                    System.out.println("point: " + point);



                    // データベースに保存する処理など
                    // Testオブジェクトを作成
                    Test test = new Test();

                    Student student = new Student();
                    student.setNo(studentNo);

                    Subject subject = new Subject();
                    subject.setCd(subjectCd);

                    School school = new School();
                    school.setCd(schoolCd);

                    test.setStudent(student);
                    test.setSubject(subject);
                    test.setSchool(school);
                    test.setNo(Integer.parseInt(testNo));
                    test.setPoint(Integer.parseInt(point));
                    test.setClassNum(classNum);

                    System.out.println("testPoint: " + test.getPoint());

                    // TestDaoのupdateメソッドを呼び出して更新
                    testDao.update(test, connection);
                }
            }

            connection.commit(); // トランザクションをコミット


            RequestDispatcher dispatcher = request.getRequestDispatcher("test.regist.done.jsp");
            dispatcher.forward(request, response);


            return null;

        } catch (Exception e) {
            e.printStackTrace();
            // エラーハンドリング
            return "error.jsp";
        }
    }
}



