<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<title>科目情報変更</title>
<link rel="stylesheet" href="../css/menu-styles.css">
<link rel="stylesheet" href="../css/sub_create.css">
<%@include file="../header.jsp" %>

<body>
<div class="container">
    <div class="content">
        <%@include file="sideber.jsp" %>
        <div class="main-content">
            <div class="form-container">
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
                        <button type="button" onclick="history.back();">戻る</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>

<%@include file="../footer.jsp" %>
