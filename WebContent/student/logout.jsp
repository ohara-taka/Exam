<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- CSS ファイルのリンク -->
<link rel="stylesheet" type="text/css" href="../css/style.css">

<style>
.logout-container {
	margin: 20px auto; /* 自動マージンを使用して中央揃え */
	display: flex;
	justify-content: center; /* 水平方向に中央揃え */
	width: 70%;
	text-align: center;
}

.login-box h2 {
	background-color: #f0f0f0; /* メニューの背景色 */
	margin-bottom: 20px; /* 上下のマージンを0に設定 */
	padding: 10px 0; /* 必要に応じて上下のパディングを調整 */
}

</style>


<%@include file="../header.jsp" %>
<div class="logout-container">

<main>
<div class="login-box">
<h2 style="text-align: left; background-color: #dddddd;">ログアウト</h2>

<p style="background-color: #77aa77; margin-bottom:50px;">ログアウトしました</p>
<a href="./" style="text-align: left; color:#007bff;"><p>ログイン</p></a>

</div>
</main>
</div>

<%@include file="../footer.jsp" %>