package bean;

import java.util.HashMap;
import java.util.Map;

// Serializableインターフェースを実装してBeanを作成する
public class TestListStudent implements java.io.Serializable {

    // DBの項目名と同じのprivateなフィールドを定義
    private String subjectName;
    private String subjectCd;
    private int num;
    private Integer point; // Integer型に修正

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

    public Integer getPoint() { // Integer型に修正
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
        this.num = num;
    }

    public void setPoint(Integer point) { // Integer型に修正
        this.point = point;
    }

    // 新たに追加したメソッド
    private Map<Integer, Integer> points = new HashMap<>();

    public Integer getPoint(int key) {
        return points.get(key);
    }

    public void putPoint(int key, Integer value) {
        points.put(key, value);
    }
}
