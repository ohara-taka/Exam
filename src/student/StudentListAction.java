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
//        HttpSession session = req.getSession(); // セッション
//        Teacher teacher = (Teacher)session.getAttribute("user");

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


        //在学フラグが設定されていた場合
        if (isAttendStr != null) {
           // 在学フラグをセットする
           isAttend = true;
        }

//      ビジネスロジック
	     if (entYearStr != null) {
	         // 数値に変換
	         entYear = Integer.parseInt(entYearStr);
	     }

//         DBからデータ取得
//         ログインユーザーの学校コードをもとにクラス番号の一覧を取得
        List<String> list = classNumDao.filter(teacher.getSchool());
//
        if (entYear != 0 && !classNum.equals("0")) {
            // 入学年度とクラス番号を指定
            students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
        } else if (entYear != 0 && classNum.equals("0")) {
            // 入学年度のみ指定
            students = sDao.filter(teacher.getSchool(), entYear, isAttend);
        } else if ((entYear == 0 && classNum == null) || (entYear == 0 && classNum.equals("0"))) {
            // 指定なしの場合
            students = sDao.filter(teacher.getSchool(), isAttend);
        } else {
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
            req.setAttribute("errors", errors);
            // 全学生情報を取得
            students = sDao.filter(teacher.getSchool(), isAttend);
        }


        // リストを初期化
        List<Integer> entYearSet = new ArrayList<>();
        // 10年前から1年後まで年をリストに追加
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        //レスポンス値をセット
        //リクエストに入学年度をセット
        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        // リクエストに在学フラグをセット
        req.setAttribute("f3", isAttendStr);

        //リクエストに学生リストをセット
        req.setAttribute("students", students);
        //リクエストにクラス番号をセット
        req.setAttribute("class_num_set", list);
        req.setAttribute("ent_year_set", entYearSet);

        // FrontControllerを使用しているためreturn文でフォワードできる
        req.getRequestDispatcher("student_list.jsp").forward(req, response);
        return null; // 戻り値を追加

    }
}