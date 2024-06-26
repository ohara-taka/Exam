<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<title>科目削除</title>
    <link rel="stylesheet" href="../css/menu-styles.css">
    <link rel="stylesheet" type="text/css" href="../css/subject.css">
    <%@include file="../header.jsp" %>


<div class="container">


<div class="content">

<%@include file="sideber.jsp" %>

<div class="main-content">

<section class="subject-management-sub">
    <div class="subject-title-style">&nbsp;&nbsp;&nbsp;科目情報削除</div>
    <p class="registration-complete">削除が完了しました</p>

    <!-- 4行のスペースを追加 -->
    <br><br><br><br>

    <!-- Topページへ戻る為のリンクを下に表示 -->
    <div class="student-links">
        <a href="SubjectList.action">科目一覧</a>
    </div>
    </section>

</div>
</div>
</div>

<%@include file="../footer.jsp" %>