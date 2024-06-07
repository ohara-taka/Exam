
<title>得点管理システム</title>
<link rel="stylesheet" href="../css/menu-styles.css">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="../header.jsp" %>

<div class="container">


</header>
<div class="content">
<nav class="sidebar">
<ul>
<li><a href="#">メニュー</a></li>
<li><a href="#">学生管理</a></li>
<li><a href="#">成績管理</a></li>
<li><a href="#">成績登録</a></li>
<li><a href="#">成績参照</a></li>
<li><a href="#">科目管理</a></li>
</ul>
</nav>
<main class="main-content">
<h2>メニュー</h2>
<div class="cards">
<div class="card student-management">
<a href="#">学生管理</a>
</div>
<div class="card grade-management">
<a href="#">成績管理</a>
<ul>
<li><a href="#">成績登録</a></li>
<li><a href="#">成績参照</a></li>
</ul>
</div>
<div class="card subject-management">
<a href="#">科目管理</a>
</div>
</div>
</main>
</div>

<%@include file="../footer.jsp" %>