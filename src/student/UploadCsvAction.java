package student;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.School;
import bean.Student;
import dao.SchoolDao;
import dao.StudentDao;
import tool.Action;

public class UploadCsvAction extends Action {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Part filePart = request.getPart("file"); // ファイルのパートを取得
        if (filePart == null) {
            throw new ServletException("ファイルが選択されていません。");
        }

        InputStream fileContent = filePart.getInputStream(); // ファイルの内容を取得

        BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
        String line;

        // CSVのヘッダー行をスキップ
        reader.readLine();

        StudentDao studentDao = new StudentDao();

        while ((line = reader.readLine()) != null) {
            // CSVの各行を処理します
            String[] data = line.split(",");
            if (data.length < 6) {
                // CSVのフォーマットが正しくない場合のエラーハンドリング
                continue;
            }

            // データをStudentオブジェクトに設定
            Student student = new Student();
            student.setNo(data[0]);
            student.setName(data[1]);
            student.setEntYear(Integer.parseInt(data[2]));
            student.setClassNum(data[3]);
            student.setAttend(Boolean.parseBoolean(data[4]));

            // ここで適切なSchoolオブジェクトを取得
            SchoolDao schoolDao = new SchoolDao();
            School school = schoolDao.get(data[5]);

            student.setSchool(school);

            // 学生情報をデータベースに保存
            studentDao.save(student);

        }
        reader.close();
        // ファイルを処理するロジックをここに追加


        return "student_create_done.jsp"; // 成功時にリダイレクトするページ
    }
}
