package dao;

//インポート
import java.sql.Connection; 			// データベースへの接続を表すインターフェース
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;    // JNDI APIを使って名前とオブジェクトを対応付けるための初期コンテキスト
import javax.sql.DataSource;		   // データベース接続プールのためのインターフェース



public class ClassNumDao {
    static DataSource ds;

    public Connection getConnection() throws Exception {
        if (ds == null) {
            InitialContext ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:/comp/env/jdbc/TAKUMAx2database");
        }
        return ds.getConnection();
    }

    public List<String> getClassNumList() {
        List<String> classNumList = new ArrayList<>();
        String sql = "SELECT class_name FROM ClassNum";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                classNumList.add(rs.getString("class_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return classNumList;
    }
}