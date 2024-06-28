package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        boolean isAttend = "true".equals(request.getParameter("is_attend"));

        // Logging input values
        System.out.println("Received parameters: no=" + no + ", name=" + name + ", classNum=" + classNum + ", isAttend=" + isAttend);

        StudentDao studentDao = new StudentDao();

        try {
            Student student = studentDao.get(no);

            if (student != null) {
                student.setName(name);
                student.setClassNum(classNum);
                student.setAttend(isAttend);

                boolean success = studentDao.update(student);
                if (success) {
                    request.setAttribute("message", "更新が成功しました");
                    System.out.println("Update successful for student: " + student);
                } else {
                    request.setAttribute("message", "更新が失敗しました");
                    System.err.println("Failed to update student: " + student);
                }
            } else {
                request.setAttribute("message", "学生が見つかりませんでした");
                System.err.println("Student not found: " + no);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "エラーが発生しました: " + e.getMessage());
            System.err.println("Exception occurred: " + e.getMessage());
        }

        return "student_update_done.jsp";
    }
}
