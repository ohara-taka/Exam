package bean;


// Serializableインターフェースを実装してBeanを作成する
public class Student implements java.io.Serializable {

    // DBの項目名と同じのprivateなフィールドを定義
	// 何型かをここで定義する
    private String no;
    private String name;
    private int entYear;
    private String classNum;
    private boolean isAttend;
    private int schoolYear;


//  その学生に対応した学校を取得したい為Schoolビーンを持たせる
    private School school;



    // ゲッターメソッド
    public String getNo() {
        return no;
    }
    public String getName() {
        return name;
    }
    public int getEntYear() {
        return entYear;
    }
    public String getClassNum() {
        return classNum;
    }
    public boolean isAttend() {
        return isAttend;
    }
    public School getSchool() {
        return school;
    }
    public int getSchoolYear() {
        return schoolYear;
    }



    // セッターメソッド
    public void setNo(String no) {
        this.no = no;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
    public void setAttend(boolean isAttend) {
        this.isAttend = isAttend;
    }
    public void setSchool(School school) {
        this.school = school;
    }
    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }
}
