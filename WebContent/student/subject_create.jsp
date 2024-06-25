<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<title>科目情報登録</title>
<link rel="stylesheet" href="../css/menu-styles.css">
<link rel="stylesheet" href="../css/sub_create.css">

<body>
<div class="container">
<div class="content">
<%@include file="sideber.jsp" %>

<div class="main-content">
    <div class="form-container">
        <div class="subject-title-style">科目情報登録</div>
        <form action="${pageContext.request.contextPath}/subject/create" method="post">
            <div class="form-group">
                <label for="subject-code">科目コード</label>
                <input type="text" id="subject-code" name="cd" placeholder="科目コードを入力してください" value="${param.cd}" required>
                <c:if test="${not empty errorMessage}">
                    <div style="color:red">${errorMessage}</div>
                </c:if>
            </div>
            <div class="form-group">
                <label for="subject-name">科目名</label>
                <input type="text" id="subject-name" name="name" placeholder="科目名を入力してください" value="${param.name}" required>
            </div>
            <div class="form-buttons">
                <button type="submit">登録</button>
                <button type="button" onclick="history.back();">戻る</button>
            </div>
        </form>
    </div>
</div>
</div>
</div>
</body>

<%@include file="../footer.jsp" %>
