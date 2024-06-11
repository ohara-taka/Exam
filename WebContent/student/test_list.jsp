<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>

<title>得点管理システム</title>

<link rel="stylesheet" href="../css/menu-styles.css">

<style>
body {
	font-family: Arial, sans-serif;
	margin: 20px;
}

.form {
	border: 1px solid #ccc;
	border-radius: 5px;
	width: 95%;
	margin-left: auto;
	margin-right: auto;
}

.container2 {
	display: flex;
	align-items: center; /* 中央に揃える */
	width: 95%;
	margin: auto;
	padding: 10px;
}

.container2>* {
	margin-right: 20px;
}

.container2>*:last-child {
	margin-right: 0;
}

h2 {
	text-align: left;
}

.form-group {
	display: flex;
	flex-direction: column;
}

.form-title {
	margin-right: 60px;
	margin-left: 10px;
}

button {
	padding: 8px 16px;
	background-color: #6c757d;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	margin-left: 40px;
}

button:hover {
	background-color: #5a6268;
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
	width: 130px;
}

input {
	border: 1px solid #ccc; /* 薄い枠の色 */
	border-radius: 5px; /* 角を丸くする */
	padding: 5px; /* 内側の余白を追加 */
	appearance: none; /* ブラウザのデフォルトのスタイルを無効にする */
	width: 280px;
}

#subject {
	width: 200px;
}

#form1 {
	border-bottom: 1px solid #ccc; /* 薄い枠の色 */
}

#form2 {
	height: 25px;
}

#annai {
	color: #00CCFF;
}
</style>

</head>



<%@include file="../header.jsp"%>

<div class="container">


	<div class="content">

		<%@include file="sideber.jsp"%>

		<div class="main-content">


			<h2 class="student-info-registration">&nbsp;&nbsp;&nbsp;学生成績参照</h2>

			<div class="form">

				<form action="TestListSubjectExecute.action" method="post">


					<div id="form1" class="container2">

						<div class="form-title">科目情報</div>

						<div class="form-group">
							<label for="academic-year">入学年度</label> <select
								id="academic-year" name="f1">
								<option value="">----</option>
								<!-- Add options here -->
							</select>
						</div>

						<div class="form-group">
							<label for="class">クラス</label> <select id="class" name="f2">
								<option value="">----</option>
								<!-- Add options here -->
							</select>
						</div>

						<div class="form-group">
							<label for="subject">科目</label> <select id="subject" name="f3">
								<option value="">----</option>
								<!-- Add options here -->
							</select>
						</div>

						<button type=submit>検索</button>

						<input type="hidden" value="sj" name="f">

					</div>

				</form>


				<form action="TestListStudentExecute.action" method="post">


					<div id="form2" class="container2">

						<div class="form-title">学生情報</div>

						<div class="form-group">
							<label for="sutudent-num">学生番号</label> <input id="student-num"
								name="f4" placeholder="学生番号を入力してください" value="${f4}"
								maxlength="10" required>
						</div>

						<button type=submit>検索</button>

						<input type="hidden" value="st" name="f">

					</div>

				</form>

			</div>


			<p id="annai">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>

		</div>
	</div>
</div>


<%@include file="../footer.jsp"%>




