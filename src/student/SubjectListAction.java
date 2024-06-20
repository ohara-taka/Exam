package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {

    // メインの処理メソッド
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(); // セッションの取得
        Teacher teacher = (Teacher) session.getAttribute("teacher"); // セッションからTeacherオブジェクトを取得

        if (teacher != null) { // Teacherオブジェクトが存在する場合
            School school = teacher.getSchool(); // TeacherからSchoolオブジェクトを取得
            SubjectDao subjectDao = new SubjectDao(); // SubjectDaoのインスタンスを生成
            List<Subject> subjectList = subjectDao.filter(school); // school_cdに基づいて科目をフィルタリング

            request.setAttribute("subjectList", subjectList); // フィルタリングされた科目リストをリクエストに設定
            request.setAttribute("schoolCd", school.getCd()); // Schoolのcdをリクエストに設定
        } else {
            request.setAttribute("subjectList", null); // Teacherオブジェクトがない場合、科目リストをnullに設定
            request.setAttribute("schoolCd", "No School Found"); // Schoolのcdが見つからないメッセージを設定
        }

        return "subject_list.jsp"; // JSPページへのフォワード
    }
}
