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
        String sql = baseSql + " WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, studentNo);
            ps.setString(2, subjectCd);
            ps.setString(3, schoolCd);
            ps.setInt(4, no);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    test = new Test();
                    test.setStudentNo(rs.getString("STUDENT_NO"));
                    test.setClassNum(rs.getString("CLASS_NUM"));
                    test.setSubjectCd(rs.getString("SUBJECT_CD"));
                    test.setSchoolCd(rs.getString("SCHOOL_CD"));
                    test.setNo(rs.getInt("NO"));
                    test.setPoint(rs.getInt("POINT"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }
 // 全てのレコードを取得するメソッド
    public List<Test> testAll() {
        List<Test> tests = new ArrayList<>();
        String sql = baseSql;  // 既に定義されているベースのSQLを使用

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Test test = new Test();
                    test.setStudentNo(rs.getString("STUDENT_NO"));
                    test.setClassNum(rs.getString("CLASS_NUM"));
                    test.setSubjectCd(rs.getString("SUBJECT_CD"));
                    test.setSchoolCd(rs.getString("SCHOOL_CD"));
                    test.setNo(rs.getInt("NO"));
                    test.setPoint(rs.getInt("POINT")); // テストテーブルの構造に応じて設定
                    tests.add(test);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tests;
    }

    // Post filter method
    public List<Test> postFilter(ResultSet rSet) {
        List<Test> tests = new ArrayList<>();
        try {
            while (rSet.next()) {
                Test test = new Test();
                test.setStudentNo(rSet.getString("STUDENT_NO"));
                test.setClassNum(rSet.getString("CLASS_NUM"));
                test.setSubjectCd(rSet.getString("SUBJECT_CD"));
                test.setSchoolCd(rSet.getString("SCHOOL_CD"));
                test.setNo(rSet.getInt("NO"));
                test.setPoint(rSet.getInt("POINT"));
                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return tests;
    }

    // Filter method
    public List<Test> filter(int entYear, String classNum, String subjectCd, int num, String schoolCd) {
        List<Test> tests = new ArrayList<>();
        String sql = baseSql + " WHERE ent_year = ? AND CLASS_NUM = ? AND SUBJECT_CD = ? AND NO = ? AND SCHOOL_CD = ?";

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
                    test.setStudentNo(rs.getString("STUDENT_NO"));
                    test.setClassNum(rs.getString("CLASS_NUM"));
                    test.setSubjectCd(rs.getString("SUBJECT_CD"));
                    test.setSchoolCd(rs.getString("SCHOOL_CD"));
                    test.setNo(rs.getInt("NO"));
                    test.setPoint(rs.getInt("POINT"));
                    tests.add(test);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
			// TODO 自動生成された catch ブロック
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
        catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // Save method for single test with connection
    public boolean save(Test test, Connection connection) {
        boolean result = false;
        String sql = "INSERT INTO test (STUDENT_NO, CLASS_NUM, SUBJECT_CD, SCHOOL_CD, NO, POINT) VALUES (?, ?, ?, ?, ?, ?)";

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
        try (Connection con = getConnection();) {
            for (Test test : list) {
                result &= delete(test, con);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Delete method for single test with connection
    public boolean delete(Test test, Connection connection) {
        boolean result = false;
        String sql = "DELETE FROM test WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, test.getStudentNo());
            ps.setString(2, test.getSubjectCd());
            ps.setString(3, test.getSchoolCd());
            ps.setInt(4, test.getNo());
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

