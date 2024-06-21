<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.Teacher" %> <!-- Teacherクラスをインポート -->
<title>Servlet/JSP Samples</title>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr bgcolor="#aaaaff">
        <td width="80%"><h1>　得点管理システム</h1></td>
        <td width="20%" valign="bottom">
            <%
                // セッションが存在し、teacherオブジェクトがnullでない場合
                if (session != null && session.getAttribute("teacher") != null) {
                    // teacherオブジェクトを取得
                    Teacher teacher = (Teacher) session.getAttribute("teacher");
            %>
            <a href="kensho_you.jsp"><%= teacher.getName() %></a>　
            <a href="Logout.action">ログアウト</a>
            <%
                }
            %>
        </td>
    </tr>
</table>


