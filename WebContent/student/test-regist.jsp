<!DOCTYPE html>
<html>
<head>
<title>科目情報登録</title>
<link rel="stylesheet" href="../css/menu-styles.css">
<link rel="stylesheet" href="../css/test-regist.css">
</head>
<body>

	<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
	<%@ page import="java.util.List"%>
	<%@ page import="dao.TestDao"%>
	<%@ page import="dao.SubjectDao"%>
	<%@ page import="bean.Test"%>
	<%@ page import="bean.Subject"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<%@ include file="../header.jsp"%>

	<div class="container">
		<div class="content">
			<%@ include file="sideber.jsp"%>
			<div class="main-content">
				<div class="container">
					<header>
						<h1>成績管理</h1>
					</header>

					<form class="search-form" action="TestRegist.action" method="post">
						<div class="form-group">
							<label for="year">入学年度</label> <select id="ent_year" name="f1">
								<option value="f1" disabled selected>--------</option>
								<!-- 入学年度今年から前後10年間を表示 -->
								<%
		                    int currentYear = java.time.Year.now().getValue();
		                	for (int i = currentYear; i >= currentYear - 10; i--) {
		                        out.println("<option value=\"" + i + "\">" + i + "</option>");
		                    }%>
							</select>
						</div>
						<div class="form-group">
							<label for="class">クラス</label> <select id="class" name="f2">
								<option value="f2">------</option>
								<c:forEach var="num" items="${class_num_set}">
									<option value="${num}">${num}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="subject">科目</label> <select name="f3" required>
								<option value="f3">------</option>
								<c:forEach var="subject" items="${subjectList}">
									<option value="${subject.cd}">${subject.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="times">回数</label> <select id="times" name="f4">
								<option value="f4">------</option>
								<%
                            int TestNumber=10;
		                	for (int i =TestNumber - 9; i <=TestNumber; i++) {
		                        out.println("<option value=\"" + i + "\">" + i + "</option>");
		                    }
		                 %>
							</select>
						</div>
						<button type="submit">検索</button>
						<!-- student_list.jspより参照 -->
						<c:choose>
							<c:when test="${testList.size()>0}">

								<div>科目 :${subjectName} （${f4}）回</div>

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
											<td>${test.classNum}</td>
											<td>${test.student.no}</td>
											<td>${test.student.name}</td>
											<td>${test.point}</td>


											<td><a href="StudentUpdate.action">変更</a></td>

										</tr>
									</c:forEach>
								</table>

							</c:when>
							<c:otherwise>
								<div>学生情報が存在しませんでした</div>
							</c:otherwise>
						</c:choose>
					</form>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../footer.jsp"%>

</body>
</html>
