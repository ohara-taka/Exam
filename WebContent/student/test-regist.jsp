<title>科目情報登録</title>
<link rel="stylesheet" href="../css/menu-styles.css">
 <link rel="stylesheet" href="../css/test-regist.css">

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="../header.jsp" %>

<body>

<div class="container">


<div class="content">

<%@include file="sideber.jsp" %>

<div class="main-content">



    <div class="container">

        <header>
            <h1>成績管理</h1>
        </header>

        <form class="search-form">
            <div class="form-group">
                <label for="year">入学年度</label>
                <select id="year">
                    <option value="">------</option>
                </select>
            </div>
            <div class="form-group">
                <label for="class">クラス</label>
                <select id="class">
                    <option value="">------</option>
                </select>
            </div>
            <div class="form-group">
                <label for="subject">科目</label>
                <select id="subject">
                    <option value="">------</option>
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

</body>

<%@include file="../footer.jsp" %>s
