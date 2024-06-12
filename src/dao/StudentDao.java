package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentDao extends Dao {

    // Studentを取得するgetメソッド
    public Student get(String no) throws Exception {

    	Connection con = getConnection();

        //STUDENTテーブルからそれぞれ取得
        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM STUDENT WHERE NO = ?");
        ResultSet rs = st.executeQuery();

    	//学生ビーンをインスタンス化して情報をセット
        Student student = new Student();
        student.setNo(rs.getString("NO"));
        student.setStudent_name(rs.getString("student_name"));
        student.setCourse_id(rs.getInt("course_id"));



        //コースビーンを学生ビーンにセット
        student.setCourse(course);


        st.close();

        con.close();

        return student;
    }

    // 1件分のStudentを削除するdeleteメソッド
    public int delete(int student_id) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("DELETE FROM STUDENT WHERE STUDENT_ID = ?");
        st.setInt(1, student_id);
        int line = st.executeUpdate();
        st.close();
        con.close();
        return line;
    }
//
//    // 1件分のStudentを挿入するinsertメソッド
//    public int insert(Student student) throws Exception {
//        Connection con = getConnection();
//
//        PreparedStatement st = con.prepareStatement(
//            "INSERT INTO STUDENT (STUDENT_ID, STUDENT_NAME, COURSE_ID) VALUES (?, ?, ?)"); // COURSE_IDを含める
//        st.setInt(1, student.getStudent_id());
//        st.setString(2, student.getStudent_name());
//        st.setInt(3, student.getCourse_id()); // コースIDをセット
//
//        int line = st.executeUpdate();
//
//        st.close();
//        con.close();
//        return line;
//    }
//
//    // 1件分のStudentを更新するupdateメソッド
//    public int update(Student student) throws Exception {
//        Connection con = getConnection();
//        PreparedStatement st = con.prepareStatement(
//        	"UPDATE STUDENT SET STUDENT_NAME = ?, COURSE_ID = ? WHERE STUDENT_ID = ?");
//        st.setString(1, student.getStudent_name());
//        st.setInt(2, student.getCourse_id()); // コースIDをセット
//        st.setInt(3, student.getStudent_id());
//
//        int line = st.executeUpdate();
//        st.close();
//        con.close();
//        return line;
//    }
//
    // student_idから1件分のStudentデータを取得するoneSstudentメソッド
    public Student getOneStudent(int one) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
                "SELECT * FROM STUDENT LEFT OUTER JOIN COURSE ON STUDENT.COURSE_ID = COURSE.COURSE_ID WHERE STUDENT_ID = ?");
        st.setInt(1, one);
        ResultSet rs = st.executeQuery();

        Student student = null;
        if (rs.next()) {
            student = new Student();
            student.setStudent_id(rs.getInt("student_id"));
            student.setStudent_name(rs.getString("student_name"));
            student.setCourse_id(rs.getInt("course_id"));

            Course course = new Course();
            course.setCourse_id(rs.getInt("course_id"));
            course.setCourse_name(rs.getString("course_name"));
            student.setCourse(course);
        }
//
//        st.close();
//        con.close();
//        return student;
//    }
//
}
