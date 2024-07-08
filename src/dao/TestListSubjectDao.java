package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bean.School;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    public String baseSql = "SELECT STUDENT.ENT_YEAR, TEST.CLASS_NUM, STUDENT.NO, STUDENT.NAME, SUBJECT.NAME AS SUBJECT_NAME, " +
            "MAX(CASE WHEN TEST.NO = 1 THEN TEST.POINT END) AS POINT1, " +
            "MAX(CASE WHEN TEST.NO = 2 THEN TEST.POINT END) AS POINT2 " +
            "FROM TEST INNER JOIN STUDENT ON TEST.STUDENT_NO = STUDENT.NO " +
            "INNER JOIN SUBJECT ON TEST.SUBJECT_CD = SUBJECT.CD " +
            "WHERE TEST.SCHOOL_CD = ? ";

    private List<TestListSubject> postFilter(ResultSet rs) throws Exception {
        List<TestListSubject> testListSubjects = new ArrayList<>();
        try {
            while (rs.next()) {
                TestListSubject testListSubject = new TestListSubject();

                testListSubject.setEntYear(rs.getInt("STUDENT.ENT_YEAR"));
                testListSubject.setClassNum(rs.getString("TEST.CLASS_NUM"));
                testListSubject.setStudentNo(rs.getString("STUDENT.NO"));
                testListSubject.setStudentName(rs.getString("STUDENT.NAME"));

                if (testListSubject.getPoints() == null) {
                    testListSubject.setPoints(new HashMap<>());
                }

                testListSubject.putPoint(1, rs.getObject("POINT1") != null ? rs.getInt("POINT1") : null);
                testListSubject.putPoint(2, rs.getObject("POINT2") != null ? rs.getInt("POINT2") : null);

                testListSubjects.add(testListSubject);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return testListSubjects;
    }

    public List<TestListSubject> filter(School school, int entYear, String classNum, String subjectName) throws Exception {
        List<TestListSubject> testListSubject = new ArrayList<>();

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String condition = "AND STUDENT.ENT_YEAR = ? AND TEST.CLASS_NUM = ? AND SUBJECT.NAME = ? ";
        String order = " GROUP BY STUDENT.ENT_YEAR, TEST.CLASS_NUM, STUDENT.NO, STUDENT.NAME, SUBJECT.NAME ORDER BY STUDENT.NO";

        try {
            con = getConnection();
            st = con.prepareStatement(baseSql + condition + order);
            st.setString(1, school.getCd());
            st.setInt(2, entYear);
            st.setString(3, classNum);
            st.setString(4, subjectName);

            rs = st.executeQuery();
            testListSubject = postFilter(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Database error occurred", e);
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
        return testListSubject;
    }
}
