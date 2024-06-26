package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestDao extends Dao {

    private String baseSql = "SELECT * FROM test";  // ここでベースのSQLを定義

    // Get method
    public Test get(String studentNo, String subjectCd, String schoolCd, int no) {
        Test test = null;
        String sql = baseSql + " WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, studentNo);
            ps.setString(2, subjectCd);
            ps.setString(3, schoolCd);
            ps.setInt(4, no);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    test = new Test();
                    test.setStudentNo(rs.getString("student_no"));
                    test.setClassNum(rs.getString("classNum"));
                    test.setSubjectCd(rs.getString("subject_cd"));
                    test.setSchoolCd(rs.getString("school_cd"));
                    test.setNo(rs.getInt("no"));
                    test.setPoint(rs.getInt("point"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    // 全てのレコードを取得するメソッド
    public List<Test> testAll() throws Exception {
        List<Test> testList = new ArrayList<>();
        String sql = "SELECT * FROM test";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Test test = new Test();
                test.setStudentNo(rs.getString("student_no"));
                test.setClassNum(rs.getString("class_num"));
                test.setSubjectCd(rs.getString("subject_cd"));
                test.setSchoolCd(rs.getString("school_cd"));
                test.setNo(rs.getInt("no"));
                test.setPoint(rs.getInt("point"));
                testList.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Database error occurred: " + e.getMessage(), e);
        }

        return testList;
    }

    // Post filter method
    public List<Test> postFilter(ResultSet rSet) {
        List<Test> tests = new ArrayList<>();
        try {
            while (rSet.next()) {
                Test test = new Test();
                test.setStudentNo(rSet.getString("student_no"));
                test.setClassNum(rSet.getString("class_num"));
                test.setSubjectCd(rSet.getString("subject_cd"));
                test.setSchoolCd(rSet.getString("school_cd"));
                test.setNo(rSet.getInt("no"));
                test.setPoint(rSet.getInt("point"));
                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    // Filter method
    public List<Test> filter(int entYear, String classNum, String subjectCd, int num, String schoolCd) {
        List<Test> tests = new ArrayList<>();
        String sql = baseSql + " WHERE ent_year = ? AND class_num = ? AND subject_cd = ? AND no = ? AND school_cd = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, entYear);
            ps.setString(2, classNum);
            ps.setString(3, subjectCd);
            ps.setInt(4, num);
            ps.setString(5, schoolCd);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Test test = new Test();
                    test.setStudentNo(rs.getString("student_no"));
                    test.setClassNum(rs.getString("class_num"));
                    test.setSubjectCd(rs.getString("subject_cd"));
                    test.setSchoolCd(rs.getString("school_cd"));
                    test.setNo(rs.getInt("no"));
                    test.setPoint(rs.getInt("point"));
                    tests.add(test);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    // Save method for list
    public boolean save(List<Test> list) {
        boolean result = true;
        try (Connection con = getConnection()) {
            for (Test test : list) {
                result &= save(test, con);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    // Save method for single test with connection
    public boolean save(Test test, Connection connection) {
        boolean result = false;
        String sql = "INSERT INTO test (student_no, class_num, subject_cd, school_cd, no, point) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, test.getStudentNo());
            ps.setString(2, test.getClassNum());
            ps.setString(3, test.getSubjectCd());
            ps.setString(4, test.getSchoolCd());
            ps.setInt(5, test.getNo());
            ps.setInt(6, test.getPoint());
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Delete method for list
    public boolean delete(List<Test> list) {
        boolean result = true;
        try (Connection con = getConnection()) {
            for (Test test : list) {
                result &= delete(test, con);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    // Delete method for single test with connection
    public boolean delete(Test test, Connection connection) {
        boolean result = false;
        String sql = "DELETE FROM test WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, test.getStudentNo());
            ps.setString(2, test.getSubjectCd());
            ps.setString(3, test.getSchoolCd());
            ps.setInt(4, test.getNo());
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
