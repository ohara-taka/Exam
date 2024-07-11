<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">


<%@include file="../header.jsp"%>
<div class="container">
	<div class="content">
		<%@include file="sideber.jsp"%>

		<div id=error>
			<div class="login-box">
				<br>エラーが発生しました<br> <br>
			</div>
		</div>
	</div>
</div>


<%@include file="../footer.jsp"%>
