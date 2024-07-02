<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<title>科目削除確認</title>
<link rel="stylesheet" href="../css/menu-styles.css">
<link rel="stylesheet" type="text/css" href="../css/subject.css">
<%@ include file="../header.jsp" %> <!-- ヘッダーをインクルード -->

<div class="container">
    <div class="content">
        <%@ include file="sideber.jsp" %> <!-- サイドバーをインクルード -->
        <div class="main-content">
            <section class="subject-management-sub">

                <div class="subject-title-style">科目削除確認</div>
                <p>${subjectName}（${subjectCd}）を削除してもよろしいですか？</p>
                <form action="SubjectDeleteExecute.action" method="post">
                    <input type="hidden" name="cd" value="${subjectCd}">
                    <div class="form-buttonsr">
                    	<button type="submit">削除</button>
                    </div>
                    	<br>
                    	<a href="SubjectList.action">戻る</a>

                </form>
            </section>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %> <!-- フッターをインクルード -->


