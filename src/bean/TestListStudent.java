package bean;

// Serializableインターフェースを実装してBeanを作成する
public class TestListStudent implements java.io.Serializable {




    // DBの項目名と同じのprivateなフィールドを定義
    private String subjectName;
    private String subjectCd;
    private int num;
    private int point;







    // ゲッターメソッド
    public String getSubjectName() {
        return subjectName;
    }
    public String getSubjectCd() {
        return subjectCd;
    }
    public int getNum() {
        return num;
    }
    public int getPoint() {
        return point;
    }





    // セッターメソッド
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }
    public void setNum(int num) {
        this.num= num;
    }
    public void setPoint(int point) {
        this.point= point;
    }

}






