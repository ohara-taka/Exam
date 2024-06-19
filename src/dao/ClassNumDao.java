package dao;

//インポート
import java.sql.Connection;            // データベースへの接続を表すインターフェース
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {

    public List<String> filter(School school) throws Exception {
        List<String> classNumList = new ArrayList<>();

        String sql = "SELECT CLASS_NUM FROM CLASS_NUM WHERE SCHOOL_CD=?";

        System.out.println("School CD: " + school.getCd());

        // 初期化処理
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            st = con.prepareStatement(sql);
            st.setString(1, school.getCd()); // 学校コードをパラメータとして設定

            rs = st.executeQuery();
            while (rs.next()) {
                ClassNum classNum = new ClassNum();
                classNum.setSchool(school); // Schoolオブジェクトを設定
                classNum.setNum(rs.getString("CLASS_NUM")); // class_nameをnumフィールドに設定
                classNumList.add(classNum.getNum());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Database error occurred: " + e.getMessage(), e);
        } finally {
            // リソースのクローズ
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

        return classNumList;
    }
}
