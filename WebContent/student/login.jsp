<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="dao.TeacherDao" %>
<%@ page import="bean.Teacher" %>
<%@ page import="javax.servlet.http.*, javax.servlet.*" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

<%
    String id = (String) request.getAttribute("id"); // リクエスト属性から取得する
    String password = "*****"; // パスワードの初期値

    // メッセージはすでにリクエスト属性にセットされている前提で
    String message = (String) request.getAttribute("message");

    // ID の初期値を空文字列に設定する
    if (id == null) {
        id = "";
    }

    // メッセージが空の場合は空文字列にする
    if (message == null) {
        message = "";
    }
%>

<%@include file="../header.jsp" %>
<div class="container">
    <main>
        <div class="login-box">
            <h2>ログイン</h2>
            <form method="post">
                <div class="input-box">
                    <label for="id">ID</label>
                    <input type="text" id="id" name="id" value="<%= id %>">
                </div>
                <div class="input-box">
                    <label for="password">パスワード</label>
                    <input type="password" id="password" name="password" value="<%= password %>">
                </div>
                <div class="checkbox-box">
                    <input type="checkbox" id="show-password" name="show-password">
                    <label for="show-password">パスワードを表示</label>
                </div>
                <div class="button-box">
                    <button type="submit">ログイン</button>
                </div>
                <div>
                    <% if (!message.isEmpty()) { %>
                        <p><%= message %></p>
                    <% } %>
                </div>
            </form>
        </div>
    </main>
</div>

<script>
    document.getElementById('show-password').addEventListener('change', function() {
        var passwordField = document.getElementById('password');
        if (this.checked) {
            passwordField.type = 'text';
        } else {
            passwordField.type = 'password';
        }
    });
</script>

<%@include file="../footer.jsp" %>