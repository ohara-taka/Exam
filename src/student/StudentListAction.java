package student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action; // Actionクラスのインポート
import util.Util;

public class StudentListAction extends Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String isAttendStr = req.getParameter("f3");

        int entYear = 0; // 入学年度
        boolean isAttend = false; // 在学フラグ
        List<Student> students = null; // 学生リスト
        LocalDate todaysDate = LocalDate.now(); // LocalDateインスタンスを取得
        int year = todaysDate.getYear(); // 現在の年を取得
        StudentDao sDao = new StudentDao(); // 学生Dao
        ClassNumDao classNumDao = new ClassNumDao(); // クラス番号Daoを初期化
        Map<String, String> errors = new HashMap<>(); // エラーメッセージ

        Teacher teacher = Util.getUser(req);

        // 在学フラグが設定されていた場合
        if (isAttendStr != null) {
            isAttend = true;
        }

        // ビジネスロジック
        if (entYearStr != null) {
            entYear = Integer.parseInt(entYearStr);
        }

        // DBからデータ取得
        List<String> list = classNumDao.filter(teacher.getSchool());

        if (entYear != 0 && !classNum.equals("0")) {
            students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
        } else if (entYear != 0 && classNum.equals("0")) {
            students = sDao.filter(teacher.getSchool(), entYear, isAttend);
        } else if ((entYear == 0 && classNum == null) || (entYear == 0 && classNum.equals("0"))) {
            students = sDao.filter(teacher.getSchool(), isAttend);
        } else if (entYear == 0 && !classNum.equals("0")) {
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
            req.setAttribute("errors", errors);
            students = sDao.filter(teacher.getSchool(), isAttend);
        } else {
            students = sDao.filter(teacher.getSchool(), isAttend);
        }

        // リストを初期化
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", isAttendStr);

        req.setAttribute("students", students);
        req.setAttribute("class_num_set", list);
        req.setAttribute("ent_year_set", entYearSet);

        req.getRequestDispatcher("student_list.jsp").forward(req, response);
        return null;
    }
}
