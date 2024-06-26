<!DOCTYPE html>
<html>
<head>
    <title>科目情報登録</title>
    <link rel="stylesheet" href="../css/menu-styles.css">
    <link rel="stylesheet" href="../css/test-regist.css">
</head>
<body>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.TestDao" %>
<%@ page import="dao.SubjectDao" %>
<%@ page import="bean.Test" %>
<%@ page import="bean.Subject" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="../header.jsp" %>

<div class="container">
    <div class="content">
        <%@ include file="sideber.jsp" %>
        <div class="main-content">
            <div class="container">
                <header>
                    <h1>成績管理</h1>
                </header>

                <form class="search-form">
                    <div class="form-group">
                        <label for="year">入学年度</label>
                            <select id="ent_year" name="ent_year">
                            <option value="ent_year"disabled selected>--------</option>
                    	<!-- 入学年度今年から前後10年間を表示 -->
<%
		                    int currentYear = java.time.Year.now().getValue();
		                	for (int i = currentYear - 10; i <= currentYear + 10; i++) {
		                        out.println("<option value=\"" + i + "\">" + i + "</option>");
		                    }
		                 %>



                        </select>
                    </div>
                    <div class="form-group">
                        <label for="class">クラス</label>
                        <select id="class">
                            <option value="">------</option>
                            <c:forEach var="test" items="${testList}">
                                <option value="${test.classNum}">${test.classNum}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="subject">科目</label>
                        <select name="subject" required>
                            <option value="">------</option>
                            <c:forEach var="subject" items="${subjectList}">
                                <option value="${subject.cd}">${subject.cd}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="times">回数</label>
                        <select id="times">
                            <option value="">------</option>
<%
                            int TestNumber=11;
		                	for (int i = TestNumber - 10; i <= TestNumber + 89; i++) {
		                        out.println("<option value=\"" + i + "\">" + i + "</option>");
		                    }
		                 %>
                        </select>
                    </div>
                    <button type="submit">検索</button>
                    <!-- student_list.jspより参照 -->
                    <c:choose>
				<c:when test="${students.size()>0}">

					<div>検索結果件数 :${students.size()}件</div>

					<table>
						<tr>
							<th>入学年度</th>
							<th>クラス</th>
							<th>科目</th>
							<th>回数</th>
						</tr>

						
						<c:forEach var="student" items="${students}">
							<tr>
								<td>${student.entYear}</td>
								<td>${student.class}</td>
								<td>${student.subject}</td>
								<td>${student.no}</td>

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
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>

</body>
</html>
