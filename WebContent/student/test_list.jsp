<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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

								<option value="0">----</option>

								<c:forEach var="year" items="${ent_year_set}">
									<option value="${year}"
										<c:if test="${year==f1}">selected</c:if>>${year}</option>
								</c:forEach>

							</select>
						</div>

						<div class="form-group">
							<label>クラス</label> <select name="f2">

								<option value="0">----</option>

								<c:forEach var="num" items="${class_num_set}">
									<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
								</c:forEach>

							</select>
						</div>

						<div class="form-group">

							<label for="subject">科目</label> <select id="subject" name="f3">

								<option value="0">----</option>

								<c:forEach var="subject" items="${subject_list_set}">
									<option value="${subject.name}"
										<c:if test="${subject.name==f3}">selected</c:if>>${subject.name}</option>
								</c:forEach>

							</select>

						</div>

						<button type="submit" name="action" value="sj">検索</button>

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

						<button type="submit" name="action" value="st">検索</button>

						<input type="hidden" value="st" name="f">

					</div>

				</form>

			</div>


			<p id="annai">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>




			<c:choose>
				<c:when test="${not empty test_list_subjects}">
					<div>科目 : ${subjectName}</div>
					<table>
						<tr>
							<th>入学年度</th>
							<th>クラス</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>1回</th>
							<th>2回</th>
						</tr>
						<c:forEach var="test_list_subject" items="${test_list_subjects}">
							<tr>
								<td>${test_list_subject.entYear}</td>
								<td>${test_list_subject.classNum}</td>
								<td>${test_list_subject.studentNo}</td>
								<td>${test_list_subject.studentName}</td>
								<td>${test_list_subject.getPoint(1)}</td>
								<td>${test_list_subject.getPoint(2)}</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
			</c:choose>

			<c:choose>
				<c:when test="${not empty test_list_students}">
					<div>氏名 : ${studentName}</div>
					<table>
						<tr>
							<th>科目名</th>
							<th>科目コード</th>
							<th>回数</th>
							<th>点数</th>
						</tr>
						<c:forEach var="test_list_student" items="${test_list_students}">
							<tr>
								<td>${test_list_student.subjectName}</td>
								<td>${test_list_student.subjectCd}</td>
								<td>${test_list_student.num}</td>
								<td>${test_list_student.point}</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
			</c:choose>

			<c:if test="${empty test_list_subjects && empty test_list_students}">
				<c:if test="${not empty param.f}">
					<div>学生情報が存在しませんでした</div>
				</c:if>
			</c:if>





















		</div>
	</div>
</div>


<%@include file="../footer.jsp"%>




