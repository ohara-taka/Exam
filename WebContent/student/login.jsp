<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- CSS ファイルのリンク -->
<link rel="stylesheet" type="text/css" href="../css/style.css">
<%@include file="../header.jsp" %>
<div class="container">

<main>
<div class="login-box">
<h2>ログイン</h2>
<form>
<div class="input-box">
<label for="id">ID</label>
<input type="text" id="id" name="id" value="admin">
</div>
<div class="input-box">
<label for="password">パスワード</label>
<input type="password" id="password" name="password" value="*****">
</div>
<div class="checkbox-box">
<input type="checkbox" id="show-password" name="show-password">
<label for="show-password">パスワードを表示</label>
</div>
<div class="button-box">
<button type="submit">ログイン</button>
</div>
</form>
</div>
</main>
</div>

<%@include file="../footer.jsp" %>