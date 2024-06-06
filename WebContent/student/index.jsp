<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- CSS ファイルのリンク -->
<link rel="stylesheet" type="text/css" href="../css/style.css">
<%@include file="../header.jsp" %>
<div id="container">

<%@include file="menu.jsp" %>

<div id="main">

<table>
<tr><td bgcolor="#dddddd" colspan="3">メニュー</td></tr>
<tr><td colspan="3"></td></tr>
<tr><td bgcolor="#ffcccc">学生管理</td><td bgcolor="#ccffcc">成績管理<br>成績登録<br>成績参照</td><td bgcolor="#ccccff">科目管理</td></tr>
</table>



</div>

</div>

<%@include file="../footer.jsp" %>