package bean;

import java.util.HashMap;
import java.util.Map;



// Serializableインターフェースを実装してBeanを作成する
public class TestListSubject implements java.io.Serializable {

    // DBの項目名と同じのprivateなフィールドを定義
    private int entYear;
    private String studentNo;
    private String studentName;
    private String classNum;
    private Map<Integer, Integer> points = new HashMap<>();


    // コンストラクタ
    public TestListSubject(){
    }


    // ゲッターメソッド
    public int getEntYear() {
        return entYear;
    }
    public String getStudentNo() {
        return studentNo;
    }
    public String getStudentName() {
        return studentName;
    }
    public String getClassNum() {
        return classNum;
    }
    public Map<Integer, Integer> getPoints() {
        return points;
    }
    public String getPoint(int key) {
        Integer value = this.points.get(key);
        return value != null ? value.toString() : null;
    }



    // セッターメソッド
    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }
    public void setStudentNo(String student_no) {
        this.studentNo = student_no;
    }
    public void setStudentName(String student_name) {
        this.studentName = student_name;
    }
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
    public void setPoints(Map<Integer, Integer> points) {
        this.points = points;
    }
    public void putPoint(int key,int value) {
    	this.points.put(key,value);
	}

}
