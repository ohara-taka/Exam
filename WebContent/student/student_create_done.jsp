<title>得点管理システム</title>
<link rel="stylesheet" href="../css/menu-styles.css">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="../header.jsp" %>


<div class="container">


<div class="content">

<%@include file="sideber.jsp" %>

<main class="main-content">
<head>
    <style>
        .student-info-registration {
            background-color: lightgray;
        }
        .registration-complete {
            text-align: center;
            background-color: green;
            color: black; /* 文字色を黒に設定 */
        }
        .student-links {
            display: inline; /* インライン要素として表示 */
        }
        .student-links a:first-child {
            margin-right: 6ch; /* 最初のリンクに6文字分のスペースを追加 */
        }
    </style>
</head>
<body>
    <h2 class="student-info-registration">&nbsp;&nbsp;&nbsp;学生情報登録</h2>
    <p class="registration-complete">登録が完了しました</p>

    <!-- 4行のスペースを追加 -->
    <br><br><br><br>

    <!-- Topページへ戻る為のリンクを下に表示 -->
    <div class="student-links">
        <a href="/Exam/student/student_create.jsp">戻る</a>
        <a href="/Exam/student/student_list.jsp">学生一覧</a>
    </div>
</body>
</html>










</main>
</div>
</div>

<%@include file="../footer.jsp" %>