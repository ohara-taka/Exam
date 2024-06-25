package student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;
import util.Util;

public class StudentCreateExecuteAction extends Action {
    @Override
    public String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

    	// ログインユーザーを取得
       	Teacher teacher = Util.getUser(request);


//        HttpSession session = request.getSession();
//        Teacher teacher = (Teacher) session.getAttribute("user");
//
//        // デバッグ用：Teacherが正しく取得されているか確認
//        if (teacher == null) {
//            throw new ServletException("ログイン情報が取得できません。");
//        }

        // フォームから送信されたデータを取得
        String entYearStr = request.getParameter("ent_year");
        String studentNo = request.getParameter("no");
        String studentName = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        String isAttendStr = request.getParameter("isAttend");
        // String schoolCd = request.getParameter("school");

        // entYearをint型に変換
        int entYear = 0;
        try {
            entYear = Integer.parseInt(entYearStr);
        } catch (NumberFormatException e) {
            throw new ServletException("入学年度の形式が正しくありません。");
        }

        // isAttendをboolean型に変換
        boolean isAttend = false;
        if (isAttendStr != null && isAttendStr.equalsIgnoreCase("true")) {
            isAttend = true;
        }

        // schoolCdに基づいてSchoolオブジェクトを取得
        School school = teacher.getSchool();
        try {
            String schoolCd = school.getCd();
            if (schoolCd == null) {
                throw new ServletException("無効な学校コードです。");
            }
        } catch (Exception e) {
            throw new ServletException("学校の取得中にエラーが発生しました。", e);

        }

        // Studentオブジェクトを作成
        Student student = new Student();
        student.setEntYear(entYear);
        student.setNo(studentNo);
        student.setName(studentName);
        student.setClassNum(classNum);
        student.setAttend(isAttend);
        student.setSchool(school);



        // データベースに保存
        StudentDao studentDao = new StudentDao();
        try {
            studentDao.save(student);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("データベースエラー: " + e.getMessage());
        }

        // 完了後のページにリダイレクト
        return "student_create_done.jsp";  // 学生一覧ページにリダイレクト
    }
}
