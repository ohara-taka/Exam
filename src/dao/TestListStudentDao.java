package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    public String baseSql = "SELECT SUBJECT.NAME, SUBJECT.CD, TEST.NO, POINT FROM TEST INNER JOIN STUDENT ON STUDENT.NO = TEST.STUDENT_NO INNER JOIN SUBJECT ON SUBJECT.CD = TEST.SUBJECT_CD WHERE STUDENT.NO = ? ORDER BY SUBJECT.CD, TEST.NO";

    private List<TestListStudent> postFilter(ResultSet rs) throws Exception {

        List<TestListStudent> testListStudents = new ArrayList<>();

        try {
            while (rs.next()) {
                TestListStudent testListStudent = new TestListStudent();

                testListStudent.setSubjectName(rs.getString("SUBJECT.NAME"));
                testListStudent.setSubjectCd(rs.getString("SUBJECT.CD"));
                testListStudent.setNum(rs.getInt("TEST.NO"));
                int point = rs.getInt("POINT");
                testListStudent.setPoint(rs.wasNull() ? null : point); // Null値チェック

                // リストにTestListStudentを追加
                testListStudents.add(testListStudent);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return testListStudents;
    }

    public List<TestListStudent> filter(Student student) throws Exception {
        List<TestListStudent> testListStudent = new ArrayList<>();

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            st = con.prepareStatement(baseSql);
            st.setString(1, student.getNo());

            rs = st.executeQuery();
            testListStudent = postFilter(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return testListStudent;
    }
}
