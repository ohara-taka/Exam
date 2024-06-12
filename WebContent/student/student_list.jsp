
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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

table {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between; /* 列の間隔を均等に配置 */
}

th, td {
	flex: 1; /* 列の幅を均等にする */
	padding: 8px; /* セルの余白を調整 */
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
							<option value="">----</option>
							<!-- Add options here -->
						</select>
					</div>

					<div id="class" class="form-group">
						<label>クラス</label> <select name="f2">
							<option value="">----</option>
							<!-- Add options here -->
						</select>
					</div>

					<div class="checkbox-group">
						<input type="checkbox" id="in-school" name="f3"> <label
							for="in-school">在学中</label>
					</div>

					<button type=submit>絞り込み</button>

				</div>

			</form>







			<c:set var="condition" value="${true}" />
			<!-- この値を条件に応じて設定 -->

			<!-- 条件が true の場合に表示 -->
			<c:if test="${condition}">

				<div>
					<p>検索結果件数 :</p>
				</div>

				<table>
					<tr>
						<th>入学年度</th>
						<th>学生番号</th>
						<th>氏名</th>
						<th>クラス</th>
						<th>在学中</th>
					</tr>

					<!-- StudentAll.javaからstudentが送られてきているので、
				１件ずつstudentに取り出して表示している。 -->
					<c:forEach var="student" items="${studentList}">
						<tr>
							<td>${student.student_year}</td>
							<td>${student.student_id}</td>
							<td>${student.student_name}</td>
							<td>${student.student_class}</td>
							<td>${student.student_in_school}</td>
							<td><a href="StudentUpdate.action">変更</a></td>

						</tr>
					</c:forEach>
				</table>

			</c:if>

			<!-- 条件が false の場合に表示 -->
			<c:if test="${not condition}">

				<div>
					<p>学生情報が存在しませんでした</p>
				</div>

			</c:if>

		</div>
	</div>
</div>


<%@include file="../footer.jsp"%>

