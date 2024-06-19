<!DOCTYPE html>
<html>
<head>
    <title>科目情報登録</title>
    <link rel="stylesheet" href="../css/menu-styles.css">
    <link rel="stylesheet" href="../css/test-regist.css">
</head>
<body>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.TestDao" %>
<%@ page import="bean.Test" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="../header.jsp" %>

<div class="container">
    <div class="content">
        <%@ include file="sideber.jsp" %>
        <div class="main-content">
            <div class="container">
                <header>
                    <h1>成績管理</h1>
                </header>

                <form class="search-form">
                    <div class="form-group">
                        <label for="year">入学年度</label>
                            <select id="ent_year" name="ent_year">
                            <option value="ent_year"disabled selected>--------</option>
                    	<!-- 入学年度今年から前後10年間を表示 -->
<%
		                    int currentYear = java.time.Year.now().getValue();
		                	for (int i = currentYear - 10; i <= currentYear + 10; i++) {
		                        out.println("<option value=\"" + i + "\">" + i + "</option>");
		                    }
		                 %>


                        </select>
                    </div>
                    <div class="form-group">
                        <label for="class">クラス</label>
                        <select id="class">
                            <option value="">------</option>
                            <c:forEach var="student" items="${testList}">
                                <option value="${test.class_num}">${test.class_num}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="subject">科目</label>
                        <select name="subject" required>
                            <option value="">------</option>
                            <c:forEach var="subject" items="${testList}">
                                <option value="${test.subject}">${test.subject}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="times">回数</label>
                        <select id="times">

                            <option value="">------</option>
                        </select>
                    </div>
                    <button type="submit">検索</button>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>

</body>
</html>
