<!DOCTYPE html>
<html>
<head>
 	<%@ page language="java" contentType="text/html; charset=UTF-8"
    	pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <title>得点管理システム</title>
    <link rel="stylesheet" href="../css/menu-styles.css">
    <%@ include file="../header.jsp" %>

    <!-- CSS -->
    <style>
        /* ラベルと入力欄を左寄せにするためのスタイル */
        label {
            display: inline-block;
            text-align: left; /* ラベルを左寄せにする */
            margin-bottom: 5px; /* ラベルと入力欄の間に少しの間隔を開ける */
        }

        /* 入力欄を幅いっぱいにするためのスタイル */
        input[type="text"],
        select {
            width: calc(100% - 12px); /* 入力欄の幅を調整し、ボーダーとパディングを考慮 */
            box-sizing: border-box; /* ボーダーとパディングを含めて計算 */
            padding: 5px; /* パディングを設定 */
            margin-bottom: 10px; /* 下に間隔を開ける */
            border-radius: 5px; /* 角を丸くする */
        }

        /* フォーム全体のスタイル */
        form {
            max-width: 100%;
        }

        .student-info-registration{
        	background-color:blue
        }

    	.checkbox {
            display: flex;
            align-items: center;
    	}

    	 button {
        	border-radius: 5px; /* 角を丸くする */
        	background-color: blue;
        	color: white;
        	margin-bottom: 10px; /* ボタンの下に間隔を開ける */
        }

    	.student-info-registration{
        	background-color:lightgray;
        }

    </style>
</head>
<body>

    <div class="container">
        <div class="content">
            <%@ include file="sideber.jsp" %>

            <div class="main-content">
                <h2 class="student-info-registration">&nbsp;&nbsp;&nbsp;学生情報変更</h2>
                <form action="StudentUpdateExecute.action" method="get">
                    <label for="ent_year">入学年度</label><br>
                    <input type="text" id="ent_year" name="ent_year" value="${ent_year}" readonly><br>

                    <label for="no">学生番号</label><br>
                    <input type="text" id="no" name="no" value="${no}" readonly><br>

                    <label for="name">氏名</label><br>
                    <input type="text" id="name" name="name" maxlength="30" value="${name}">${name}<br>

                    <label for="class_num">クラス</label><br>
                    <select id="class_num" name="class_num">
                        <c:forEach var="classNum" items="${classNumList}">
                            <option value="${classNum}" <c:if test="${classNum == class_num}">selected</c:if>>${classNum}</option>
                        </c:forEach>
                    </select><br>

					<div class="checkbox">
	                    <label for="si_attend">在学中</label>
	                    <input type="checkbox" id="si_attend" name="si_attend"><br>
                    </div>

                    <!-- java作成次第画面遷移 -->
                    <button type="submit" name="end">変更</button><br>
                </form>

                <!-- java作成次第画面遷移 -->
                <a href="javascript:history.back();">戻る</a>
            </div>
        </div>
    </div>

    <%@ include file="../footer.jsp" %>
</body>
</html>
