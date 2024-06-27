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

                // pointsマップが初期化されていない場合は初期化
                if (testListSubject.getPoints() == null) {
                    testListSubject.setPoints(new HashMap<>());
                }

                // 1回目と2回目の点数を設定
                testListSubject.putPoint(1, rs.getInt("POINT1"));
                testListSubject.putPoint(2, rs.getInt("POINT2"));

                // リストにTestListSubjectを追加
                testListSubjects.add(testListSubject);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return testListSubjects;
    }

    public List<TestListSubject> filter(School school, int entYear, String classNum, String subjectName) throws Exception {
        // リストを初期化
        List<TestListSubject> testListSubject = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        // SQLの条件を設定
        String condition = "AND STUDENT.ENT_YEAR = ? AND TEST.CLASS_NUM = ? AND SUBJECT.NAME = ? ";
        // SQLのグループ化とソート順を設定
        String order = " GROUP BY STUDENT.ENT_YEAR, TEST.CLASS_NUM, STUDENT.NO, STUDENT.NAME, SUBJECT.NAME ORDER BY STUDENT.NO";


        try {
            // データベース接続を取得
            con = getConnection();
            // プリペアドステートメントにSQL文を設定
            st = con.prepareStatement(baseSql + condition + order);
            // 学校コードを設定
            st.setString(1, school.getCd());
            // 入学年度を設定
            st.setInt(2, entYear);
            // クラス番号を設定
            st.setString(3, classNum);

            st.setString(4, subjectName);

            // クエリを実行し、結果セットを取得
            rs = st.executeQuery();
            // 結果セットを処理
            testListSubject = postFilter(rs);
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
        return testListSubject;
    }
}
