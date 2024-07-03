package student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.Student;
import dao.StudentDao;

@WebServlet("/UploadCsv.action")
@MultipartConfig
public class UploadCsvServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("csvFile");
        if (filePart == null) {
            request.setAttribute("errorMessage", "CSVファイルを選択してください");
            request.getRequestDispatcher("student_create.jsp").forward(request, response);
            return;
        }

        try (InputStream inputStream = filePart.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length != 6) {
                    continue; // スキップするか、エラーハンドリングを行う
                }
                String studentNo = data[0].trim();
                String name = data[1].trim();
                int year = Integer.parseInt(data[2].trim());
                int classNum = Integer.parseInt(data[3].trim());
                boolean isGraduated = Boolean.parseBoolean(data[4].trim());
                String department = data[5].trim();

                // 学生データを保存
                Student student = new Student();
                student.setNo(studentNo);
                student.setName(name);
                student.setEntYear(year);
                student.setClassNum(classNum);
                student.setAttend(!isGraduated); // ここでisGraduatedの反対を設定
                student.setDepartment(department);

                StudentDao studentDao = new StudentDao();
                studentDao.save(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("CSVの読み込み中にエラーが発生しました: " + e.getMessage());
        }

        response.sendRedirect("student_create_done.jsp");
    }
}
