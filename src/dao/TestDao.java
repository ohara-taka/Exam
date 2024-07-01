package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

	private String baseSql = "SELECT ENT_YEAR, TEST.CLASS_NUM, STUDENT_NO, NAME, POINT" +
			 "FROM TEST INNER JOIN STUDENT ON STUDENT.NO = TEST.STUDENT_NO";  // ここでベースのSQLを定義



	// Get method （1人の生徒の、ある科目の１回分の点数）
	public Test get(Student student, Subject subject, School school, int no) throws Exception {
		Test test = null;
		Connection con = getConnection();
		PreparedStatement st = null;


		//        try (Connection con = getConnection();
		//             PreparedStatement ps = con.prepareStatement(sql)) {
		//            ps.setStudent(1, student);
		//            ps.setSubject(2, subject);
		//            ps.setSchool(3, school);
		//            ps.setInt(4, no);



		try {
			// TESTテーブルからそれぞれ取得
			st = con.prepareStatement(
					baseSql + "WHERE STUDENT_NO = ? AND SUBJECT_NO = ? AND TEST.SCHOOL_CD = ? AND TEST.NO = ?"
					);

			st.setString(1, student.getNo());
			st.setString(2, subject.getCd());
			st.setString(3, school.getCd());
			st.setInt(4, no);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				test = new Test();
				test.setStudent(student);
				test.setSubject(subject);
				test.setSchool(school);
				test.setNo(rs.getInt("TEST.NO"));
				test.setPoint(rs.getInt("POINT"));
				test.setClassNum(rs.getString("TEST.CLASS_NUM"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		}

		return test;
	}





	// Post filter method
	private List<Test> postFilter(ResultSet rs,School school) {
		List<Test> tests = new ArrayList<>();

		try {
			while (rs.next()) {
				Student student = new Student();
				student.setNo(rs.getString("STUDENT_NO"));

				Subject subject = new Subject();
				subject.setCd(rs.getString("SUBJECT_CD"));

				Test test = new Test();

				test.setStudent(student);
				test.setSubject(subject);
				test.setSchool(school);
				test.setNo(rs.getInt("TEST.NO"));
				test.setPoint(rs.getInt("POINT"));
				test.setClassNum(rs.getString("TEST.CLASS_NUM"));



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
	    String sql = baseSql + "WHERE ENT_YEAR = ? AND TEST.CLASS_NUM = ? AND SUBJECT_CD = ? AND TEST.NO = ? AND TEST.SCHOOL_CD = ?";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, entYear);
	        ps.setString(2, classNum);
	        ps.setString(3, subject.getCd());
	        ps.setInt(4, num);
	        ps.setString(5, school.getCd());

	        try (ResultSet rs = ps.executeQuery()) {
	            tests = postFilter(rs, school);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return tests;
	}


//			try (ResultSet rs = ps.executeQuery()) {
//				while (rs.next()) {
//					Test test = new Test();
//					Student student = new Student();
//
//					test.setStudent(student.getEntYear());
//					test.setClassNum(rs.getString("class_num"));
//					test.setSubjectCd(rs.getString("subject_cd"));
//					test.setSchoolCd(rs.getString("school_cd"));
//					test.setNo(rs.getInt("no"));
//					test.setPoint(rs.getInt("point"));
//					tests.add(test);
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return tests;
//	}




	//    // 全てのレコードを取得するメソッド
	//    public List<Test> testAll() throws Exception {
	//        List<Test> testList = new ArrayList<>();
	//        String sql = "SELECT * FROM test";
	//
	//        try (Connection con = getConnection();
	//             PreparedStatement ps = con.prepareStatement(sql);
	//             ResultSet rs = ps.executeQuery()) {
	//
	//            while (rs.next()) {
	//                Test test = new Test();
	//                test.setStudentNo(rs.getString("student_no"));
	//                test.setClassNum(rs.getString("class_num"));
	//                test.setSubjectCd(rs.getString("subject_cd"));
	//                test.setSchoolCd(rs.getString("school_cd"));
	//                test.setNo(rs.getInt("no"));
	//                test.setPoint(rs.getInt("point"));
	//                testList.add(test);
	//            }
	//        } catch (SQLException e) {
	//            e.printStackTrace();
	//            throw new Exception("Database error occurred: " + e.getMessage(), e);
	//        }
	//
	//        return testList;
	//    }






	// Save method for list
//	public boolean save(List<Test> list) {
//		boolean result = true;
//		try (Connection con = getConnection()) {
//			for (Test test : list) {
//				result &= save(test, con);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			result = false;
//		}
//		return result;
//	}
//
//	// Save method for single test with connection
//	private boolean save(Test test, Connection connection) {
//		boolean result = false;
//		String sql = "INSERT INTO test (student_no, class_num, subject_cd, school_cd, no, point) VALUES (?, ?, ?, ?, ?, ?)";
//
//		try (PreparedStatement ps = connection.prepareStatement(sql)) {
//			ps.setString(1, test.getStudentNo());
//			ps.setString(2, test.getClassNum());
//			ps.setString(3, test.getSubjectCd());
//			ps.setString(4, test.getSchoolCd());
//			ps.setInt(5, test.getNo());
//			ps.setInt(6, test.getPoint());
//			result = ps.executeUpdate() > 0;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//
//	// Delete method for list
//	public boolean delete(List<Test> list) {
//		boolean result = true;
//		try (Connection con = getConnection()) {
//			for (Test test : list) {
//				result &= delete(test, con);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			result = false;
//		}
//		return result;
//	}
//
//	// Delete method for single test with connection
//	private boolean delete(Test test, Connection connection) {
//		boolean result = false;
//		String sql = "DELETE FROM test WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?";
//
//		try (PreparedStatement ps = connection.prepareStatement(sql)) {
//			ps.setString(1, test.getStudentNo());
//			ps.setString(2, test.getSubjectCd());
//			ps.setString(3, test.getSchoolCd());
//			ps.setInt(4, test.getNo());
//			result = ps.executeUpdate() > 0;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
}
