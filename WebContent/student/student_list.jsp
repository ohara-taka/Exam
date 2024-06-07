<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--%@include file="../header.html" %-->

<head>
    <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
    }

    .container {
      display: flex;
      align-items: center; /* 中央に揃える */
      gap: 20px; /* 要素間のスペース */
      width: 95%;
      margin: auto;
      padding: 20px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }

    h2 {
      text-align: left;
    }

    .form-group {
        margin-bottom: 0px;
        display: flex;
        flex-direction: column;
    }

     .form-group label {
        margin-bottom: 5px; /* ラベルとセレクトボックス間のスペース */
    }

    .checkbox-group {
        display: flex;
        align-items: center; /* 中央に揃える */
        gap: 5px;
    }

     button {
         padding: 8px 16px;
         background-color: #6c757d;
         color: white;
         border: none;
         border-radius: 5px;
         cursor: pointer;
     }

     .new-registration {
         text-align: right;
         margin-bottom: 10px;
     }



  	</style>

  </head>

<body>


    <h2>学生管理</h2>

    <div class="new-registration">
        <a href="#">新規登録</a>
    </div>

	<div class="container">

        <div class="form-group">
            <label for="academic-year">入学年度</label>
            <select id="academic-year" name="f1">
                <option value="">----</option>
                <!-- Add options here -->
            </select>
        </div>


		<div id="class" class="form-group">
			<label>クラス</label>
            <select name="f2">
                <option value="">----</option>
                <!-- Add options here -->
            </select>
		</div>

		<div class="checkbox-group">
			<input type="checkbox"id="in-school" name="f3">
			<label for="in-school">在学中</label>
		</div>

		<button>絞り込み</button>

	</div>

</body>
   <!--           <c:forEach var="student" items="${studentList}">
                    <tr>
                        <td>${student.student_id}</td>
                        <td>${student.student_name}</td>
       -->
                        <!-- getCourse()でコースビーンを取得し、コースビーンのgetCourse_name()でコース名を取得する -->
     <!--                     <td>${student.getCourse().getCourse_name()}</td>
                    </tr>
                </c:forEach>
   -->

<!-- %@include file="../footer.html" % -->