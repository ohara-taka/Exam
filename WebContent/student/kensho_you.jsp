<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="bean.Teacher" %>
<%@ page import="bean.School" %>
<%@ page import="javax.servlet.http.*, javax.servlet.*" %>
<%@include file="../header.jsp" %>

	<div class="container">


		<div class="content">

			<%@include file="sideber.jsp"%>



			<div class="main-content">
        <%-- ログインしていない場合にはログインフォームを表示 --%>
        <% if (session.getAttribute("teacher") == null) { %>
            <h2>ログインしていません！</h2>

        <% } else { %>


            <%-- ログインしている場合にはユーザー情報を表示 --%>
            <div class="info-box">
                <h2>Teacher Information</h2>
                <%
                    Teacher teacher = (Teacher) session.getAttribute("teacher");
                    if (teacher != null) {
                %>
                    <p>ID: <%= teacher.getId() %></p>
                    <p>Name: <%= teacher.getName() %></p>
                    <p>School: <%= teacher.getSchool() != null ? teacher.getSchool().getCd() : "N/A" %></p>
                    <p>School: <%= teacher.getSchool() != null ? teacher.getSchool().getName() : "N/A" %></p>
                <% } %>
            </div>
        <% } %>
			</div>
		</div>
	</div>

</body>

<%@include file="../footer.jsp"%>