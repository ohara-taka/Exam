package util;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
//import bean.Subject;
//import dao.ClassNumDao;
//import dao.SubjectDao;
//import java.sql.SQLException;
//import java.util.Set;

import bean.Teacher;

public class Util {

    private static DataSource dataSource;

    // デフォルトコンストラクタ
    public Util() {
        // 通常は何も行いませんが、必要に応じて初期化処理を追加できます。
        // 例えば、特定の初期化処理やログ出力など。
    }

    public static void setDataSource(DataSource ds) {
        dataSource = ds;
    }

    public static Teacher getUser(HttpServletRequest request) {
        return (Teacher) request.getSession().getAttribute("teacher");
    }


//
//    public static void setClassNumSet(HttpServletRequest request) {
//        Teacher teacher = getUser(request);
//        String schoolCd = teacher.getSchool().getCd();
//        try {
//            ClassNumDao classNumDao = new ClassNumDao(dataSource);
//            Set<String> classNumSet = classNumDao.getClassNumSetBySchool(schoolCd);
//            request.setAttribute("classNumSet", classNumSet);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // エラーメッセージをリクエストに設定するなどのエラーハンドリングを行う
//        }
//    }
//
//    public static void setYearSet(HttpServletRequest request) {
//        Teacher teacher = getUser(request);
//        String schoolCd = teacher.getSchool().getCd();
//        try {
//            SubjectDao subjectDao = new SubjectDao(dataSource);
//            Set<String> yearSet = subjectDao.getYearSetBySchool(schoolCd);
//            request.setAttribute("yearSet", yearSet);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // エラーハンドリングを適切に行う
//        }
//    }
//
//    public static void setSubjects(HttpServletRequest request) {
//        Teacher teacher = getUser(request);
//        String schoolCd = teacher.getSchool().getCd();
//        try {
//            SubjectDao subjectDao = new SubjectDao(dataSource);
//            Set<Subject> subjectSet = subjectDao.getSubjectsBySchool(schoolCd);
//            request.setAttribute("subjects", subjectSet);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // エラーハンドリングを適切に行う
//        }
//    }
//
//    public static void setNumSet(HttpServletRequest request) {
//        Teacher teacher = getUser(request);
//        String schoolCd = teacher.getSchool().getCd();
//        try {
//            SubjectDao subjectDao = new SubjectDao(dataSource);
//            Set<String> numSet = subjectDao.getNumSetBySchool(schoolCd);
//            request.setAttribute("numSet", numSet);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // エラーハンドリングを適切に行う
//        }
//    }
}
