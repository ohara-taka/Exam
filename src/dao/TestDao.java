import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.h2.engine.Database;

import bean.School;
import bean.Student;
import bean.Test;

public class TestDao extends Dao {

    private String baseSql;

    // Get method
    public Test get(Student student, Subject subject, School school, int no) {
        Test test = null;
        String sql = baseSql + " WHERE student_id = ? AND subject_id = ? AND school_id = ? AND test_no = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, student.getNo());
            ps.setInt(2, subject.getId());
            ps.setInt(3, school.getCd());
            ps.setInt(4, no);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    test = new Test();
                    test.setStudent(student);
                    test.setClassNum(rs.getString("class_num"));
                    test.setSubject(subject);
                    test.setSchool(school);
                    test.setNo(no);
                    test.setPoint(rs.getInt("point"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    // Post filter method
    public List<Test> postFilter(ResultSet rSet, School school) {
        List<Test> tests = new ArrayList<>();
        try {
            while (rSet.next()) {
                Test test = new Test();
                test.setStudent(new Student(rSet.getInt("student_id"))); // 適切なコンストラクタやメソッドを使用
                test.setClassNum(rSet.getString("class_num"));
                test.setSubject(new Subject(rSet.getInt("subject_id"))); // 適切なコンストラクタやメソッドを使用
                test.setSchool(school);
                test.setNo(rSet.getInt("test_no"));
                test.setPoint(rSet.getInt("point"));
                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    // Filter method
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) {
        List<Test> tests = new ArrayList<>();
        String sql = baseSql + " WHERE ent_year = ? AND class_num = ? AND subject_id = ? AND test_num = ? AND school_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, entYear);
            ps.setString(2, classNum);
            ps.setInt(3, subject.getId());
            ps.setInt(4, num);
            ps.setInt(5, school.getCd());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Test test = new Test();
                    test.setStudent(new Student(rs.getInt("student_id"))); // 適切なコンストラクタやメソッドを使用
                    test.setClassNum(rs.getString("class_num"));
                    test.setSubject(subject);
                    test.setSchool(school);
                    test.setNo(rs.getInt("test_no"));
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
        try (Connection conn = Database.getConnection()) {
            for (Test test : list) {
                result &= save(test, conn);
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
        String sql = "INSERT INTO tests (student_id, class_num, subject_id, school_id, test_no, point) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, test.getStudent().getNo());
            ps.setString(2, test.getClassNum());
            ps.setInt(3, test.getSubject().getCd());
            ps.setInt(4, test.getSchool().getCd());
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
        try (Connection conn = Database.getConnection()) {
            for (Test test : list) {
                result &= delete(test, conn);
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
        String sql = "DELETE FROM tests WHERE student_id = ? AND subject_id = ? AND school_id = ? AND test_no = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, test.getStudent().getNo());
            ps.setInt(2, test.getSubject().getCd());
            ps.setInt(3, test.getSchool().getCd());
            ps.setInt(4, test.getNo());
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

