<title>得点管理システム</title>
<link rel="stylesheet" href="../css/menu-styles.css">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="../header.jsp" %>


<div class="container">


<div class="content">

<%@include file="sideber.jsp" %>

<main class="main-content">

<div class="main-subject-updatedone-box">
    <div class="subject-title-style">&nbsp;&nbsp;&nbsp;科目情報登録</div>
    <p class="registration-complete">登録が完了しました</p>

    <!-- 4行のスペースを追加 -->
    <br><br><br><br>

    <!-- Topページへ戻る為のリンクを下に表示 -->
    <div class="student-links">
        <a href="SubjectList.action">科目一覧</a>
    </div>
    </div>

</main>
</div>
</div>

<%@include file="../footer.jsp" %>

