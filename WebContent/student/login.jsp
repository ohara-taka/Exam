<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">

<style>
.login-container {
	margin: 20px auto; /* 自動マージンを使用して中央揃え */
	display: flex;
	justify-content: center; /* 水平方向に中央揃え */
	width: 60%;
	text-align: center;
}

.login-box {
	border: 1px solid #cccccc;
}

.login-box h2 {
	background-color: #f0f0f0; /* メニューの背景色 */
	margin: 0; /* 上下のマージンを0に設定 */
	padding: 10px 0; /* 必要に応じて上下のパディングを調整 */
}

.input-box {
	margin: 20px auto; /* 自動マージンを使用して中央揃え */
	width: 80%;
	align-items: center;
	text-align: left;
}
</style>
<%
    String id = (String) request.getAttribute("id"); // リクエスト属性から取得する
    String password = ""; // パスワードの初期値は空文字列

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

<%@include file="../header.jsp"%>
<div class="login-container">
	<main>
	<div class="login-box">
		<h2>ログイン</h2>
		<form method="post"
			action="${pageContext.request.contextPath}/loginAction">
			<!-- 送信先URLを指定 -->
			<div class="error-message">
				<% if (!message.isEmpty()) { %>
				<p><%= message %></p>
				<% } %>
			</div>
			<div class="input-box">
				<label for="id"></label> <input type="text" id="id" name="id"
					value="<%= id %>" placeholder="ID" required>
			</div>
			<div class="input-box">
				<label for="password"></label> <input type="password" id="password"
					name="password" value="<%= password %>" placeholder="パスワード"
					required>
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

<%@include file="../footer.jsp"%>
