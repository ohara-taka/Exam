package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

public class TeacherDao extends Dao {

    // ログインメソッド
    public Teacher login(String id, String password) throws Exception {
        Teacher teacher = null;

        Connection con = getConnection();

        String sql = "SELECT t.ID, t.PASSWORD, t.NAME, t.SCHOOL_CD, s.NAME AS SCHOOL_NAME "
                   + "FROM TEACHER t "
                   + "LEFT JOIN SCHOOL s ON t.SCHOOL_CD = s.CD "
                   + "WHERE t.ID = ? AND t.PASSWORD = ?";

        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, id);
        st.setString(2, password);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            teacher = new Teacher();
            teacher.setId(rs.getString("ID"));
            teacher.setPassword(rs.getString("PASSWORD"));
            teacher.setName(rs.getString("NAME"));

            School school = new School();
            school.setCd(rs.getString("SCHOOL_CD"));
            school.setName(rs.getString("SCHOOL_NAME"));

            teacher.setSchool(school);
        }

        rs.close();
        st.close();
        con.close();

        return teacher;
    }

    // その他のメソッドもここに追加できます（例：全教師の取得など）
}