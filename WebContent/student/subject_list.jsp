<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.SubjectDao" %>
<%@ page import="bean.Subject" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html>
<head>
    <title>得点管理システム</title>
    <link rel="stylesheet" href="../css/menu-styles.css">
    <link rel="stylesheet" type="text/css" href="../css/subject.css">
</head>
<body>

<%@ include file="../header.jsp" %>

<div class="container">

    <div class="content">

        <%@ include file="sideber.jsp" %>

        <div class="main-content">

            <main>
                <section class="subject-management-sub">
                    <h2>科目管理</h2>
                    <a href="SubjectCreate.action" class="add-new">科目新規追加</a>
                    <table>
                        <thead>
                            <tr>
                                <th>科目コード</th>
                                <th>科目名</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="subject" items="${subjectList}">
                                <tr>
                                    <td>${subject.cd}</td>
                                    <td>${subject.name}</td>
                                    <td><a href="SubjectUpdate.action">変更</a> <a href="#">削除</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </section>
    </main>








</div>
</div>
</div>


<%@include file="../footer.jsp" %>