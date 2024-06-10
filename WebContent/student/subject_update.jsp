<title>科目情報変更</title>
<link rel="stylesheet" href="../css/menu-styles.css">
<link rel="stylesheet" href="../css/sub_create.css">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="../header.jsp" %>

<body>

<div class="container">


<div class="content">

<%@include file="sideber.jsp" %>

<div class="main-content">





    <div class="form-container">
        <h1 class="form-title">科目情報変更</h1>
        <form>
            <div class="form-group">
                <label for="subject-code">科目コード</label>
                <input type="text" id="subject-code" placeholder="科目コードを入力してください">
            </div>
            <div class="form-group">
                <label for="subject-name">科目名</label>
                <input type="text" id="subject-name" placeholder="科目名を入力してください">
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