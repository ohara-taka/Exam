<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.Teacher" %> <!-- Teacherクラスをインポート -->
<meta charset="UTF-8">
<meta http-equiv="Content-Language" content="ja">
<title>Servlet/JSP Samples</title>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr bgcolor="#CEE3F6">
        <td width="80%"><h1>　得点管理システム</h1></td>
        <td width="20%" valign="bottom">
            <%
                // セッションが存在し、teacherオブジェクトがnullでない場合
                if (session != null && session.getAttribute("teacher") != null) {
                    // teacherオブジェクトを取得
                    Teacher teacher = (Teacher) session.getAttribute("teacher");
            %>
            <a><%= teacher.getName() %>様</a>　
            <a href="Logout.action" style="color:#007bff">ログアウト</a>
            <%
                }
            %>
        </td>
    </tr>
</table>


