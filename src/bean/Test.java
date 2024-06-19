package bean;

import java.io.Serializable;

public class Test implements Serializable {

    private String studentNo;
    private String classNum;
    private String subjectCd;
    private String schoolCd;
    private int no;
    private int point;

    // ゲッターメソッド
    public String getStudentNo() {
        return studentNo;
    }
    public String getClassNum() {
        return classNum;
    }
    public String getSubjectCd() {
        return subjectCd;
    }
    public String getSchoolCd() {
        return schoolCd;
    }
    public int getNo() {
        return no;
    }
    public int getPoint() {
        return point;
    }

    // セッターメソッド
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }
    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public void setPoint(int point) {
        this.point = point;
    }
}

