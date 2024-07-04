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

	private String baseSql = "SELECT STUDENT.ENT_YEAR, TEST.CLASS_NUM, STUDENT_NO, STUDENT.NAME, POINT, TEST.SUBJECT_CD, TEST.NO " +
		    "FROM TEST INNER JOIN STUDENT ON STUDENT.NO = TEST.STUDENT_NO ";



	// Get method （1人の生徒の、ある科目の１回分の点数）
	public Test get(Student student, Subject subject, School school, int no) throws Exception {
		Test test = null;
		Connection con = getConnection();
		PreparedStatement st = null;

		try {
			// TESTテーブルからそれぞれ取得
			st = con.prepareStatement(
					baseSql + "WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND TEST.SCHOOL_CD = ? AND TEST.NO = ?"
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
				student.setEntYear(rs.getInt("ENT_YEAR"));
				student.setName(rs.getString("NAME"));


				Subject subject = new Subject();
				subject.setCd(rs.getString("SUBJECT_CD"));

				Test test = new Test();

				test.setStudent(student);
				test.setSubject(subject);
				test.setSchool(school);
				test.setNo(rs.getInt("TEST.NO"));
				test.setPoint(rs.getInt("POINT"));
				test.setClassNum(rs.getString("TEST.CLASS_NUM"));


	            // デバッグ用出力
	            System.out.println("Fetched Test: " + test);


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

	        // デバッグ用出力
	        System.out.println("Executing SQL: " + ps.toString());

	        try (ResultSet rs = ps.executeQuery()) {
	            tests = postFilter(rs, school);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return tests;
	}

	public boolean update(Test test, Connection connection) {
	    boolean result = false;
	    String sql = "UPDATE test SET point = ? WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND NO = ? AND SCHOOL_CD = ?";
	    try (PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setInt(1, test.getPoint());
	        ps.setString(2, test.getStudent().getNo());
	        ps.setString(3, test.getSubject().getCd());
	        ps.setInt(4, test.getNo());
	        ps.setString(5, test.getSchool().getCd());
	        result = ps.executeUpdate() > 0;

	        System.out.println("Executing SQL: " + ps.toString());


	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return result;
	}


////新規作成
//public List<Test> save(String classNum, Subject subject, int num, School school) {
//    boolean result = false;
//    String sql = "INSERT INTO test (STUDENT_NO,SUBJECT_CD,SCHOOL_CD,NO,POINT,CLASS_NUM) VALUES(?,?,?,?,null,?) ";
//    try (Connection con = getConnection();
//	         PreparedStatement ps = con.prepareStatement(sql)) {
//        ps.setInt(1, ());
//        ps.setInt(2, ());
//	        ps.setString(3,  school.getCd());
//	        ps.setInt(4, num);
//	        ps.setString(5, point);
//	        ps.setString(6,classNum ());
//        result = ps.executeUpdate() > 0;
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//    return result;
//
//}
////  新規作成
//  public boolean save(Test test, Connection connection) {
//      boolean result = false;
//      String sql = "INSERT INTO test (STUDENT_NO,SUBJECT_CD,SCHOOL_CD,NO,POINT,CLASS_NUM) VALUES(?,?,?,?,?,?) ";
//      try (Connection con = getConnection();
// 	         PreparedStatement ps = con.prepareStatement(sql)) {
//      	   ps.setInt(1, );
//             ps.setInt(2, subject.getCd());
//   	        ps.setString(3,  school.getCd());
//   	        ps.setInt(4, num);
//   	        ps.setString(5, );
//   	        ps.setString(6,classNum ());
//          result = ps.executeUpdate() > 0;
//      } catch (SQLException e) {
//          e.printStackTrace();
//      }
//      return result;
//
//  }


	public boolean insertInitialTests(String studentNo, String classNum, School school) throws Exception {
        String sql = "INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, NULL, ?)";
        Connection con = getConnection();
        PreparedStatement ps = null;
        boolean result = false;

        try {
            ps = con.prepareStatement(sql);

            for (int i = 1; i <= 2; i++) {
                for (String subjectCd : new String[]{"A01", "A02", "A03", "A04"}) {
                    ps.setString(1, studentNo);
                    ps.setString(2, subjectCd);
                    ps.setString(3, school.getCd());
                    ps.setInt(4, i);
                    ps.setString(5, classNum);
                    ps.addBatch();
                }
            }

            int[] results = ps.executeBatch();
            result = results.length == 8;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
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

        return result;
    }

}
