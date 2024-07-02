<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<title>科目情報変更</title>
<link rel="stylesheet" href="../css/menu-styles.css">
<link rel="stylesheet" type="text/css" href="../css/subject.css">
<%@ include file="../header.jsp" %>

<div class="container">
    <div class="content">
        <%@ include file="sideber.jsp" %>
        <div class="main-content">
        	<section class="subject-management-sub">

                <div class="subject-title-style">科目情報変更</div>
                <form action="${pageContext.request.contextPath}/subject/update" method="post">
                    <div class="form-group">
                        <label for="subject-code">科目コード</label>
                        <input type="text" id="subject-code" name="cd" value="${subject.cd}" placeholder="科目コードを入力してください">
                    </div>
                    <div class="form-group">
                        <label for="subject-name">科目名</label>
                        <input type="text" id="subject-name" name="name" value="${subject.name}" placeholder="科目名を入力してください">
                    </div>
                    <div class="form-buttons">
                        <button type="submit">変更</button>
                    </div>
                    	<br>
                        <a href="SubjectList.action">戻る</a>

                </form>
            </section>
            </div>
            </div>
    </div>


<%@include file="../footer.jsp" %>
