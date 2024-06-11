<title>得点管理システム</title>
<link rel="stylesheet" href="../css/menu-styles.css">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../header.jsp"%>

<body>

	<div class="container">


		<div class="content">

			<%@include file="sideber.jsp"%>



			<div class="main-content">
				<h2>メニュー</h2>
				<div class="cards">
					<div class="card student-management">
						<a href="#">学生管理</a>
					</div>
					<div class="card grade-management">
						<p>成績管理</p>
						<ul>
							<li><a href="#">成績登録</a></li>
							<li><a href="#">成績参照</a></li>
						</ul>
					</div>
					<div class="card subject-management">
						<a href="#">科目管理</a>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

<%@include file="../footer.jsp"%>