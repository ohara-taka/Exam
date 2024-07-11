<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="dao.TestDao"%>
<%@ page import="dao.SubjectDao"%>
<%@ page import="bean.Test"%>
<%@ page import="bean.Subject"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<title>科目情報登録</title>
<link rel="stylesheet" href="../css/menu-styles.css">
<link rel="stylesheet" href="../css/test-regist.css">
<!-- JavaScriptの追加 -->
<script>
	function validateForm() {
		var entYear = document.getElementById("ent_year").value;
		var classNum = document.getElementById("class").value;
		var subject = document.getElementsByName("f3")[0].value;
		var times = document.getElementById("times").value;

		if (entYear === "" || classNum === "" || subject === "" || times === "") {
			document.getElementById("error").style.display = "block";
			document.getElementById("search-results").style.display = "none";
			return false;
		}
		return true;
	}
</script>
<style>

main {
    flex: 1; /* 残りの空間を全て使う */
    overflow-y: auto; /* スクロール可能にする */
}


button {
	padding: 8px 16px;
	background-color: #6c757d;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	margin-top: 10px;
}

button:hover {
	background-color: #5a6268;
}

.container2 {
	display: flex;
	align-items: center;
	gap: 10px;
	width: 95%;
	margin: auto;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	flex-wrap: wrap; /* 要素を折り返す */
	margin-top: 20px;
	border-radius: 5px; /* 追加: 上部のスペースを詰める */
}

.form-group {
	display: flex;
	flex-direction: column; /* 縦方向に配置 */
	margin-right: 10px; /* 横方向に並べるためのスペース */
}

.form-group2 {
	display: flex;
	flex-direction: column; /* 縦方向に配置 */
	margin-right: 10px; /* 横方向に並べるためのスペース */
	width: 60px;
	margin-left: 10px;
}

.search-form {
	display: flex; /* フォーム全体を横並びにする */
	flex-wrap: wrap; /* 要素が画面幅に合わせて折り返すように */
	align-items: flex-end; /* フォームの下部を揃える */
}

.search-form button {
	margin-left: 10px; /* ボタンとフォームの間のスペース */
}

.form-group label {
	margin-bottom: 5px; /* ラベルとフォームの間のスペース */
}

.form-group select {
	margin-right: 10px;
}

.form-group2 label {
	margin-bottom: 5px; /* ラベルとフォームの間のスペース */
}

.form-group2 select {
	margin-right: 10px;
}

.error-message {
	color: red;
	margin-top: 10px;
}

#ent_year, #class {
	width: 150px;
}

#search-results {
	margin-top: 20px;
}

#end-botton {
	margin-top: 20px;
}
</style>
</head>

<%@ include file="../header.jsp"%>

<div class="container">
	<div class="content">
		<%@ include file="sideber.jsp"%>
		<div class="main-content">
				<h2>成績管理</h2>
			<div class="container2">


				<!-- フォームタグに onsubmit 属性を追加 -->
				<form class="search-form" action="TestRegist.action" method="post"
					onsubmit="return validateForm()">
					<div class="form-group">
						<label for="year">入学年度</label> <select id="ent_year" name="f1">
							<option value="" selected>--------</option>
							<%
									int currentYear = java.time.Year.now().getValue();
									String entYearStr = (String) request.getAttribute("f1");
									Integer entYear = null;
									if (entYearStr != null) {
										try {
											entYear = Integer.parseInt(entYearStr);
										} catch (NumberFormatException e) {
											entYear = null;
										}
									}

									for (int i = currentYear - 10; i <= currentYear; i++) {
										out.println("<option value=\"" + i + "\"" + (entYear != null && i == entYear ? " selected" : "") + ">"
												+ i + "</option>");
									}
								%>
						</select>

					</div>
					<div class="form-group">
						<label for="class">クラス</label> <select id="class" name="f2">
							<option value="">------</option>
							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}" <c:if test="${num eq f2}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="subject">科目</label> <select name="f3">
							<option value="">------</option>
							<c:forEach var="subject" items="${subjectList}">
								<option value="${subject.cd}"
									<c:if test="${subject.cd eq f3}">selected</c:if>>${subject.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group2">
						<label for="times">回数</label> <select id="times" name="f4">
							<option value="">------</option>
							<%
									int TestNumber = 2;
									String numberStr = (String) request.getAttribute("f4");
									Integer number = null;
									if (numberStr != null) {
										try {
											number = Integer.parseInt(numberStr);
										} catch (NumberFormatException e) {
											number = null;
										}
									}

									for (int i = TestNumber - 1; i <= TestNumber; i++) {
										out.println("<option value=\"" + i + "\"" + (number != null && i == number ? " selected" : "") + ">" + i
												+ "</option>");
									}
								%>
						</select>
					</div>
					<!-- 検索ボタンを追加 -->
					<button type="submit">検索</button>
					<!-- エラーメッセージの追加 -->
					<div id="error" class="error-message" style="display: none;">入学年度とクラスと科目と回数を選択してください</div>
				</form>
			</div>

			<div id="search-results"
				style="display: ${testList != null && testList.size() > 0 ? 'block' : 'none'};">

				<form action="TestRegistExecute.action" method="post">
					<c:choose>
						<c:when test="${testList != null && testList.size() > 0}">
							<!-- 検索結果が存在する場合の処理 -->
							<div>科目 : ${subjectName}（${f4}回）</div>
							<input type="hidden" name="subjectName" value="${subjectName}">
							<input type="hidden" name="subjectCd" value="${subjectCd}">
							<table>
								<tr>
									<th>入学年度</th>
									<th>クラス</th>
									<th>学生番号</th>
									<th>氏名</th>
									<th>点数</th>
								</tr>
								<c:forEach var="test" items="${testList}">
									<tr>
										<td>${test.student.entYear}</td>
										<td><input type="hidden" name="classNum[]"
											value="${test.classNum}">${test.classNum}</td>
										<td><input type="hidden" name="studentNo[]"
											value="${test.student.no}">${test.student.no}</td>
										<td>${test.student.name}</td>
										<td><input type="hidden" name="testNo[]"
											value="${test.no}"> <input type="number"
											name="points[]" placeholder="未登録"
											value="${test.point == null ? '' : test.point}" min="0"
											max="100"></td>
									</tr>
								</c:forEach>
							</table>
							<button type="submit" id="end-button">登録して終了</button>
						</c:when>
						<c:otherwise>
							<!-- 検索結果が存在しない場合の処理 -->
							<c:if
								test="${not empty f1 or not empty f2 or not empty f3 or not empty f4}">
								<div>学生情報が存在しませんでした</div>
							</c:if>
						</c:otherwise>
					</c:choose>

				</form>
			</div>
		</div>
	</div>
</div>

<%@ include file="../footer.jsp"%>
