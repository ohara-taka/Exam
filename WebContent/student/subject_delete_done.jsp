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
    </style>
</head>
<body>
    <div class="subject-title-style">科目情報変更</div>
    <p class="registration-complete">変更が完了しました</p>

    <!-- 4行のスペースを追加 -->
    <br><br><br><br>

    <!-- Topページへ戻る為のリンクを下に表示 -->
    <div class="student-links">
        <a href="SubjectList.action">科目一覧</a>
    </div>
</body>
</html>










</main>
</div>
</div>

<%@include file="../footer.jsp" %>