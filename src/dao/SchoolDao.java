package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.School;

public class SchoolDao extends Dao {

    // StudentのIDを取得するsearchAllメソッド
    public School get(String no) throws Exception {
        School school = new School();

        Connection con = getConnection();

        PreparedStatement st = null;

try {

        //SCHOOLテーブルからそれぞれ取得
        st = con.prepareStatement(
            "SELECT * FROM SCHOOL WHERE CD = ?");

        st.setString(1,no);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {

	    	//学生ビーンをインスタンス化して情報をセット
	        school.setCd(rs.getString("NO"));
	        school.setName(rs.getString("NAME"));;

        } else {
        	school = null;
        }

	} catch (Exception e) {
    	throw e;

    }finally{

    	if (st != null) {
    		try {
    			st.close();
    		}	catch (SQLException sqle) {
    				throw sqle;
    		}

    	}

    	if (con != null) {
    		try {
    			con.close();
    		}	catch (SQLException sqle) {
    				throw sqle;
    		}
    	}
    }
	return school;
    }
}




