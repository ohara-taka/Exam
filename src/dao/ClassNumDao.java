package dao;

//インポート
import java.sql.Connection; 			// データベースへの接続を表すインターフェース
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;


public class ClassNumDao extends Dao {

//	    static DataSource ds;

//	    public Connection getConnection() throws Exception {
//	        if (ds == null) {
//	            InitialContext ic = new InitialContext();
//	            ds = (DataSource) ic.lookup("java:/comp/env/jdbc/TAKUMAx2database");
//	        }
//	        return ds.getConnection();
//	    }


	public List<String> filter(School school) throws Exception {
		List<String> classNumList = new ArrayList<>();
		String sql = "SELECT class_name FROM ClassNum WHERE school_id = ?";

		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql)) {

			st.setString(1, school.getCd()); // Schoolオブジェクトからschool_idを取得して設定

			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					ClassNum classNum = new ClassNum();
					classNum.setSchool(school); // Schoolオブジェクトを設定
					classNum.setNum(rs.getString("class_name")); // class_nameをnumフィールドに設定
					classNumList.add(classNum.getNum());
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Database error occurred", e); // SQLExceptionを一般的なExceptionに変換
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return classNumList;
	}
}
