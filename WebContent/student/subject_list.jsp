<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<title>科目管理</title>
    <link rel="stylesheet" href="../css/menu-styles.css">
    <link rel="stylesheet" type="text/css" href="../css/subject.css">


<%@ include file="../header.jsp" %> <!-- ヘッダーをインクルード -->

<div class="container">
    <div class="content">
        <%@ include file="sideber.jsp" %> <!-- サイドバーをインクルード -->
        <div class="main-content">
                <section class="subject-management-sub">
                    <div class="subject-title-style">科目管理</div>
                    <a href="SubjectCreate.action" class="add-new">科目新規追加</a> <!-- 科目新規追加ボタン -->
                    <p>School Code: ${schoolCd}</p> <!-- School Codeを表示 -->
                    <table>
                        <thead>
                            <tr>
                                <th>科目コード</th>
                                <th>科目名</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="subject" items="${subjectList}"> <!-- 科目リストをループして表示 -->
                                <tr>
                                    <td>${subject.cd}</td>
                                    <td>${subject.name}</td>
                                    <td><a href="SubjectUpdate.action?cd=${subject.cd}">変更</a> <a href="SubjectDelete.action?cd=${subject.cd}">削除</a></td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
               </section>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %> <!-- フッターをインクルード -->
