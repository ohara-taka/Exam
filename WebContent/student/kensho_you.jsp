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
            <div class="login-box">
                <h2>ログイン</h2>
                <form method="post" action="login"> <!-- ログイン処理を行うURLを指定 -->
                    <div class="input-box">
                        <label for="id">ID</label>
                        <input type="text" id="id" name="id" value="">
                    </div>
                    <div class="input-box">
                        <label for="password">パスワード</label>
                        <input type="password" id="password" name="password" value="">
                    </div>
                    <div class="checkbox-box">
                        <input type="checkbox" id="show-password" name="show-password">
                        <label for="show-password">パスワードを表示</label>
                    </div>
                    <div class="button-box">
                        <button type="submit">ログイン</button>
                    </div>
                </form>
            </div>
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
                    <p>School: <%= teacher.getSchool() != null ? teacher.getSchool().getName() : "N/A" %></p>
                <% } %>
            </div>
        <% } %>
			</div>
		</div>
	</div>

</body>

<%@include file="../footer.jsp"%>