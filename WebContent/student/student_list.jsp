
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<head>

<title>得点管理システム</title>

<link rel="stylesheet" href="../css/menu-styles.css">

<style>
.container2 {
	display: flex;
	justify-content: space-around;
	align-items: center; /* 中央に揃える */
	gap: 20px; /* 要素間のスペース */
	width: 95%;
	margin: auto;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.student-info-registration {
	text-align: left;
	background-color: lightgray;
}

.form-group {
	display: flex;
	flex-direction: column;
	width: 250px;
}

.form-group label {
	margin-bottom: 5px; /* ラベルとセレクトボックス間のスペース */
}

.checkbox-group {
	display: flex;
	align-items: center; /* 中央に揃える */
	gap: 5px;
}

button {
	padding: 8px 16px;
	background-color: #6c757d;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

button:hover {
	background-color: #5a6268;
}

.new-registration {
	text-align: right;
	margin-bottom: 10px;
}

select {
	border: 1px solid #ccc; /* 薄い枠の色 */
	border-radius: 5px; /* 角を丸くする */
	padding: 5px; /* 内側の余白を追加 */
	appearance: none; /* ブラウザのデフォルトのスタイルを無効にする */
}
</style>

</head>

<%@include file="../header.jsp"%>

<div class="container">

	<div class="content">

		<%@include file="sideber.jsp"%>

		<div class="main-content">

			<h2 class="student-info-registration">&nbsp;&nbsp;&nbsp;学生管理</h2>

			<div class="new-registration">
				<a href="StudentCreate.action">新規登録</a>
			</div>

			<form action="StudentList.action" method="post">

				<div class="container2">

					<div class="form-group">
						<label for="academic-year">入学年度</label> <select id="academic-year"
							name="f1">

							<option value="0">----</option>

							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
							</c:forEach>

						</select>
					</div>

					<div id="class" class="form-group">
						<label>クラス</label> <select name="f2">

							<option value="0">----</option>

							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
							</c:forEach>

						</select>
					</div>

					<div class="checkbox-group">
						<input type="checkbox" id="in-school" name="f3" <c:if test="${!empty f3}">checked</c:if>>
						<label for="in-school">在学中</label>
					</div>

					<button type=submit>絞り込み</button>

				</div>

			</form>


			<c:choose>
				<c:when test="${students.size()>0}">

					<div>検索結果件数 :${students.size()}件</div>

					<table class="student-custom-table">
						<tr>
							<th>入学年度</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>クラス</th>
							<th>在学中</th>
						</tr>

						<!--StudentAll.javaからstudentが送られてきているので、
				１件ずつstudentに取り出して表示している。 -->
						<c:forEach var="student" items="${students}">
							<tr>
								<td>${student.entYear}</td>
								<td>${student.no}</td>
								<td>${student.name}</td>
								<td>${student.classNum}</td>

								<td><c:choose>
										<c:when test="${student.isAttend()}">
									○
								</c:when>
										<c:otherwise>
									×
								</c:otherwise>
									</c:choose></td>

								<td><a href="StudentUpdate.action">変更</a></td>

							</tr>
						</c:forEach>
					</table>

				</c:when>
				<c:otherwise>
					<div>学生情報が存在しませんでした</div>
				</c:otherwise>
			</c:choose>

		</div>
	</div>
</div>


<%@include file="../footer.jsp"%>

