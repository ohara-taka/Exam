package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    // コースを全件取得するsubjectAllメソッド
    public List<Subject> subjectAll() throws Exception {
        List<Subject> subjectList = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("SELECT * FROM subject");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Subject subject = new Subject();
            subject.setCd(rs.getString("cd"));
            subject.setName(rs.getString("name"));
            subjectList.add(subject);
        }

        st.close();
        con.close();

        return subjectList;
    }

    // 指定されたidとschoolに対応するSubjectを取得する
    public Subject get(String id, School school) throws Exception {
        Subject subject = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM subject WHERE subject_cd = ? AND school_cd = ?");
        st.setString(1, id);
        st.setString(2, school.getCd());
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            subject = new Subject();
            subject.setCd(rs.getString("subject_cd"));
            subject.setName(rs.getString("subject_name"));
        }

        st.close();
        con.close();

        return subject;
    }

    // 指定されたschoolに対応する全てのSubjectのリストを取得する

    public List<Subject> filter(School school) throws Exception {
        List<Subject> subjectList = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM subject WHERE school_name = ?");
        st.setString(1, school.getCd());
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Subject subject = new Subject();
            subject.setCd(rs.getString("subject_cd"));
            subject.setName(rs.getString("subject_name"));
            subjectList.add(subject);
        }

        st.close();
        con.close();

        return subjectList;
    }

    // 新しいSubjectを保存し、成功した場合にtrueを返す

    public boolean save(Subject subject) throws Exception {
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "INSERT INTO subject (subject_cd, subject_name, school_name) VALUES (?, ?, ?)");
        st.setString(1, subject.getCd());
        st.setString(2, subject.getName());
        st.setString(3, subject.getSchool().getCd());

        int result = st.executeUpdate();

        st.close();
        con.close();

        return result > 0;
    }

    // 指定されたSubjectを削除し、成功した場合にtrueを返す
    public boolean delete(Subject subject) throws Exception {
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "DELETE FROM subject WHERE subject_cd = ? AND school_name = ?");
        st.setString(1, subject.getCd());
        st.setString(2, subject.getSchool().getCd());

        int result = st.executeUpdate();

        st.close();
        con.close();

        return result > 0;
    }
    }
