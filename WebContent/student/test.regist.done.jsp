<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<title>成績管理</title>
    <link rel="stylesheet" href="../css/menu-styles.css">
    <link rel="stylesheet" type="text/css" href="../css/subject.css">
    <%@include file="../header.jsp" %>


<div class="container">


<div class="content">

<%@include file="sideber.jsp" %>

<div class="main-content">

<section class="subject-management-sub">
    <h2 class="subject-title-style">成績管理</h2>
    <p class="registration-complete">登録が完了しました</p>

    <!-- 4行のスペースを追加 -->
    <br><br><br><br>

    <!-- Topページへ戻る為のリンクを下に表示 -->
    <div class="student-links">
    	        <a href="/Exam/student/#">成績参照</a>
        <a href="/Exam/student/student_create.jsp">戻る</a>

    </div>
    </section>

</div>
</div>
</div>

<%@include file="../footer.jsp" %>