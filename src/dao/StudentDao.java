package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;



public class StudentDao extends Dao {

	private String baseSql = "SELECT * FROM STUDENT WHERE SCHOOL_CD = ?";

    // StudentのIDを取得するsearchAllメソッド
    public Student get(String no) throws Exception {

    	Student student = new Student();

        Connection con = getConnection();

        PreparedStatement st = null;

try{

        //STUDENTテーブルからそれぞれ取得
        st = con.prepareStatement(
            "SELECT * FROM STUDENT WHERE NO = ?");

        st.setString(1,no);

        ResultSet rs = st.executeQuery();

        SchoolDao schoolDao = new SchoolDao();

        if (rs.next()) {

	    	//学生ビーンをインスタンス化して情報をセット
	        student.setNo(rs.getString("NO"));
	        student.setName(rs.getString("NAME"));
	        student.setEntYear(rs.getInt("ENT_YEAR"));
	        student.setClassNum(rs.getString("CLASS_NUM"));
	        student.setAttend(rs.getBoolean("IS_ATTEND"));

        	student.setSchool(schoolDao.get(rs.getString("SCHOOL_CD")));

        } else {

        	student = null;
        }

	} catch (Exception e) {
    	throw e;

    }finally{

    	if (st != null) {
    		try {
    			st.close();
    		}	catch (SQLException sqle) {
    				throw sqle;
    		}

    	}

    	if (con != null) {
    		try {
    			con.close();
    		}	catch (SQLException sqle) {
    				throw sqle;
    		}
    	}
    }
	return student;
}



    private List<Student> postFilter(ResultSet rs, School school) throws Exception {

   	 	List<Student> studentList = new ArrayList<>();

   	 	try {

   	 		while (rs.next()) {
   	 			Student student = new Student();

   		        student.setNo(rs.getString("NO"));
   		        student.setName(rs.getString("NAME"));
   		        student.setEntYear(rs.getInt("ENT_YEAR"));
   		        student.setClassNum(rs.getString("CLASS_NUM"));
   		        student.setAttend(rs.getBoolean("IS_ATTEND"));
   		        student.setSchool(school);

   		        studentList.add(student);
   	 		}
   	 	} catch (SQLException | NullPointerException e) {
   	 		e.printStackTrace();
   	 	}
   	 	return studentList;
    }


    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        // リストを初期化
        List<Student> studentList = new ArrayList<>();
        // データベース接続を取得
        Connection con = getConnection();
        // プリペアドステートメント
        PreparedStatement st = null;
        // リザルトセット
        ResultSet rs = null;
        // SQLの条件
        String condition = "and ent_year=? and class_num=?";
        // SQLのソート
        String order = " order by no asc";

        // SQLの在学フラグを作成
        String conditionIsAttend = "";
        // フラグがtrueの場合
        if (isAttend) {
            conditionIsAttend = " and is_attend=true";
        }

        try {
            // プリペアードステートメントにSQL文をセット
            st = con.prepareStatement( baseSql + condition + conditionIsAttend + order);
            // プリペアードステートメントに学校コードをバインド
            st.setString(1, school.getCd());
            // プリペアードステートメントに入学年度をバインド
            st.setInt(2, entYear);
            // プリペアードステートメントにクラス番号をバインド
            st.setString(3, classNum);
            // クライアントに戻すデータを格納する
            rs = st.executeQuery();
         // リストへの格納処理を実行
            studentList = postFilter(rs, school);
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを開放する
            if (st != null) {
                try {
                	st.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // コネクションを開放する
            if (con != null) {
                try {
                	con.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return studentList;
    }





    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
        // リストを初期化
        List<Student> studentList = new ArrayList<>();
        // データベース接続を取得
        Connection con = getConnection();
        // プリペアドステートメント
        PreparedStatement st = null;
        // リザルトセット
        ResultSet rs = null;
        // SQLの条件
        String condition = "and ent_year=? ";
        // SQLのソート
        String order = " order by no asc";

        // SQLの在学フラグ
        String conditionIsAttend = "";
        // フラグがtrueだった場合
        if (isAttend) {
            conditionIsAttend = " and is_attend=true";
        }

        try {
            // プリペアードステートメントにSQL文をセット
            st = con.prepareStatement( baseSql + condition + conditionIsAttend + order);
            // プリペアードステートメントに学校コードをバインド
            st.setString(1, school.getCd());
            // プリペアードステートメントに入学年度をバインド
            st.setInt(2, entYear);
            // クライアントに戻すデータを格納する
            rs = st.executeQuery();

         // リストへの格納処理を実行
            studentList = postFilter(rs, school);
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを開放する
            if (st != null) {
                try {
                	st.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // コネクションを開放する
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return studentList;
    }



    public List<Student> filter(School school, boolean isAttend) throws Exception {
        // リストを初期化
        List<Student> studentList = new ArrayList<>();
        // データベース接続を取得
        Connection con = getConnection();
        // プリペアドステートメント
        PreparedStatement st = null;
        // リザルトセット
        ResultSet rs = null;
        // SQLのソート
        String order = " order by no asc";

        // SQLの在学フラグ
        String conditionIsAttend = "";
        // フラグがtrueの場合
        if (isAttend) {
            conditionIsAttend = " and is_attend=true";
        }

        try {
            // プリペアードステートメントにSQL文をセット
            st = con.prepareStatement( baseSql + conditionIsAttend + order);
            // プリペアードステートメントに学校コードをバインド
            st.setString(1, school.getCd());
            // プリペアードステートメントを実行
            rs = st.executeQuery();
            // リストへの格納処理を実行
            studentList = postFilter(rs, school);

        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを開放する
            if (st != null) {
                try {
                	st.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // コネクションを開放する
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return studentList;

    }




public boolean save(Student student) throws Exception {
    // コネクションを確立
    Connection connection = getConnection();
    // プリペアードステートメント
    PreparedStatement statement = null;
    // 実行件数
    int count = 0;

    try {
        // データベースから学生を取得
        Student old = get(student.getNo());
        if (old == null) {
            // 学生が存在しなかった場合
            // プリペアードステートメントにINSERT文をセット
            statement = connection.prepareStatement(
                "insert into student (no, name, ent_year, class_num, is_attend, school_cd) values (?, ?, ?, ?, ?, ?)"
            );
            // プリペアードステートメントに値をバインド
            statement.setString(1, student.getNo());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getEntYear());
            statement.setString(4, student.getClassNum());
            statement.setBoolean(5, student.isAttend());
            statement.setString(6, student.getSchool().getCd());
        } else {
            // 学生が存在した場合
            // プリペアードステートメントにUPDATE文をセット
            statement = connection.prepareStatement(
                "update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?"
            );
            // プリペアードステートメントに値をバインド
            statement.setString(1, student.getName());
            statement.setInt(2, student.getEntYear());
            statement.setString(3, student.getClassNum());
            statement.setBoolean(4, student.isAttend());
            statement.setString(5, student.getNo());
        }

        // プリペアードステートメントを実行
        count = statement.executeUpdate();

    } catch (Exception e) {
        throw e;
    } finally {
        // プリペアードステートメントを閉じる
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException sqle) {
                throw sqle;
            }
        }
        // コネクションを閉じる
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException sqle) {
                throw sqle;
            }
        }
    }

    if (count > 0) {
        // 実行件数が1件以上ある場合
        return true;
    } else {
        // 実行件数が0件の場合
        return false;
    }
}


public boolean update(Student student) throws Exception {
    // コネクションを確立
    Connection connection = getConnection();
    // プリペアードステートメント
    PreparedStatement statement = null;
    // 実行件数
    int count = 0;

    try {
        // プリペアードステートメントにUPDATE文をセット
        statement = connection.prepareStatement(
            "UPDATE student SET name=?, class_num=?, is_attend=? WHERE no=?"
        );
        // プリペアードステートメントに値をバインド
        statement.setString(1, student.getName());
        statement.setString(2, student.getClassNum());
        statement.setBoolean(3, student.isAttend());
        statement.setString(4, student.getNo());

        // プリペアードステートメントを実行
        count = statement.executeUpdate();

    } catch (Exception e) {
        throw e;
    } finally {
        // プリペアードステートメントを閉じる
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException sqle) {
                throw sqle;
            }
        }
        // コネクションを閉じる
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException sqle) {
                throw sqle;
            }
        }
    }

    return count > 0;
}


}