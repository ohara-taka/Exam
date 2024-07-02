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

    public String baseSql = "SELECT SUBJECT.NAME, SUBJECT.CD, TEST.NO, POINT " +
            "FROM STUDENT INNER JOIN SUBJECT ON STUDENT.SCHOOL_CD = SUBJECT.SCHOOL_CD " +
            "INNER JOIN TEST ON SUBJECT.CD = TEST.SUBJECT_CD " +
            "WHERE STUDENT.NO = ? " +
            "GROUP BY SUBJECT.NAME, TEST.NO, POINT " +
            "ORDER BY SUBJECT.CD, TEST.NO";

    private List<TestListStudent> postFilter(ResultSet rs) throws Exception {

        List<TestListStudent> testListStudents = new ArrayList<>();

        try {

            while (rs.next()) {
                TestListStudent testListStudent = new TestListStudent();

                testListStudent.setSubjectName(rs.getString("SUBJECT.NAME"));
                testListStudent.setSubjectCd(rs.getString("SUBJECT.CD"));
                testListStudent.setNum(rs.getInt("TEST.NO"));
                testListStudent.setPoint(rs.getInt("POINT"));


                // リストにTestListSubjectを追加
                testListStudents.add(testListStudent);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return testListStudents;
    }

    public List<TestListStudent> filter(Student student) throws Exception {
        // リストを初期化
        List<TestListStudent> testListStudent = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            // データベース接続を取得
            con = getConnection();
            // プリペアドステートメントにSQL文を設定
            st = con.prepareStatement(baseSql);
            // 学校コードを設定
            st.setString(1, student.getNo());


            // クエリを実行し、結果セットを取得
            rs = st.executeQuery();
            // 結果セットを処理
            testListStudent = postFilter(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            // 結果セットを閉じる
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // プリペアドステートメントを閉じる
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // コネクションを閉じる
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
