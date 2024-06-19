package bean;

// Serializableインターフェースを実装してBeanを作成する
public class ClassNum implements java.io.Serializable {

    // DBの項目名と同じのprivateなフィールドを定義
    private School school;
    private String num; // コースIDを追加

    // ゲッターメソッド
    public School getSchool() {
        return school;
    }
    public String getNum() {
        return num;
    }


    // セッターメソッド
    public void setSchool(School school) {
        this.school = school;
    }
    public void setNum(String num) {
        this.num = num;
    }
}
