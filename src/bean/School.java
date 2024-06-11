package bean;

// Serializableインターフェースを実装してBeanを作成する
public class School implements java.io.Serializable {

    // DBの項目名と同じのprivateなフィールドを定義
    private String cd;
    private String name; // コースIDを追加


    // ゲッターメソッド
    public String getCd() {
        return cd;
    }
    public String getName() {
        return name;
    }


    // セッターメソッド
    public void setCd(String cd) {
        this.cd = cd;
    }
    public void setName(String name) {
        this.name = name;
    }
}
