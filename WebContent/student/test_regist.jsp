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
				<div class="container2">
					<header>
						<h1>成績管理</h1>
					</header>

					<form class="search-form" action="TestRegist.action" method="post">
						<div class="form-group">
							<label for="year">入学年度</label> <select id="ent_year" name="f1" required>
								<option value="" disabled selected>--------</option>
								<%
                                    int currentYear = java.time.Year.now().getValue();
                                    String entYearStr = (String)request.getAttribute("f1");
                                    Integer entYear = null;
                                    if (entYearStr != null) {
                                        try {
                                            entYear = Integer.parseInt(entYearStr);
                                        } catch (NumberFormatException e) {
                                            entYear = null;
                                        }
                                    }

                                    for (int i = currentYear; i >= currentYear - 10; i--) {
                                        out.println("<option value=\"" + i + "\"" +
                                            (entYear != null && i == entYear ? " selected" : "") + ">" + i + "</option>");
                                    }
                                %>
							</select>
						</div>
						<div class="form-group">
							<label for="class">クラス</label> <select id="class" name="f2"  required>
								<option value="">------</option>
								<c:forEach var="num" items="${class_num_set}">
									<option value="${num}"
										<c:if test="${num eq f2}">selected</c:if>>${num}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="subject">科目</label> <select name="f3" required>
								<option value="">------</option>
								<c:forEach var="subject" items="${subjectList}">
									<option value="${subject.cd}"
										<c:if test="${subject.cd eq f3}">selected</c:if>>${subject.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="times">回数</label> <select id="times" name="f4" required>
								<option value="">------</option>
								<%
                                    int TestNumber = 2;
                                    String numberStr = (String)request.getAttribute("f4");
                                    Integer number = null;
                                    if (numberStr != null) {
                                        try {
                                            number = Integer.parseInt(numberStr);
                                        } catch (NumberFormatException e) {
                                            number = null;
                                        }
                                    }

                                    for (int i = TestNumber - 1; i <= TestNumber; i++) {
                                        out.println("<option value=\"" + i + "\"" +
                                            (number != null && i == number ? " selected" : "") + ">" + i + "</option>");
                                    }
                                %>
							</select>
						</div>
						<button type="submit">検索</button>
					</form>

					<form action="TestRegistExecute.action" method="post">
						<c:choose>
							<c:when test="${testList.size() > 0}">
								<div>科目 : ${subjectName} ${f4}回</div>
								<!-- 科目名を隠しフィールドとして追加 -->
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
												value="${test.no}"> <input type="number" name="points[]" placeholder="未登録" value="${test.point == null ? '' : test.point}" min="0" max="100"></td>
										</tr>
									</c:forEach>
								</table>
								<button type="submit">登録して終了</button>
							</c:when>
							<c:otherwise>
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
