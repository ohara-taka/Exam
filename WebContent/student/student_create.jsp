<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>得点管理システム</title>
<link rel="stylesheet" href="../css/menu-styles.css">

<!-- CSS -->
<style>
    /* スタイルの定義はここに */
    label {
        display: inline-block;
        width: 100%;
        text-align: left;
        margin-bottom: 5px;
    }

    input[type="text"],
    select {
        width: calc(100% - 12px);
        box-sizing: border-box;
        padding: 5px;
        margin-bottom: 10px;
        border-radius: 5px;
    }

    form {
        max-width: 100%;
    }

    button {
        border-radius: 5px;
        background-color: gray;
        color: white;
        margin-bottom: 10px;
    }

    .student-info-registration {
        background-color: lightgray;
    }

    .error-message {
        color: red;
        margin-bottom: 10px;
    }
</style>

<%@ include file="../header.jsp" %>

<div class="container">
    <div class="content">
        <%@ include file="sideber.jsp" %>
        <div class="main-content">
            <h2 class="student-info-registration">&nbsp;&nbsp;&nbsp;学生情報登録</h2>

            <form id="studentForm" action="StudentCreateExecute.action" method="post" onsubmit="return validateForm()">
                <label for="ent_year">入学年度</label><br>
                <select id="ent_year" name="ent_year">
                    <option value="" disabled selected>--------</option>
                    <%
                        int currentYear = java.time.Year.now().getValue();
                        for (int i = currentYear - 10; i <= currentYear + 10; i++) {
                            out.println("<option value=\"" + i + "\"" + (i == Integer.parseInt(request.getAttribute("entYear") != null ? request.getAttribute("entYear").toString() : "0") ? " selected" : "") + ">" + i + "</option>");
                        }
                    %>
                </select><br>
                <div id="ent_year_error" class="error-message" style="display:none;">入学年度を選択してください</div>

                <label for="no">学生番号</label><br>
                <input type="text" id="no" name="no" maxlength="10" required placeholder="学生番号を入力してください" value="${requestScope.studentNo != null ? requestScope.studentNo : ''}"><br>
                <!-- エラーメッセージの表示 -->
                <c:if test="${not empty errorMessage}">
                    <div class="error-message">${errorMessage}</div>
                </c:if>

                <label for="name">氏名</label><br>
                <input type="text" id="name" name="name" maxlength="30" required placeholder="氏名を入力してください" value="${requestScope.studentName != null ? requestScope.studentName : ''}"><br>

                <label for="class_num">クラス</label><br>
                <select id="class_num" name="class_num">
                    <c:forEach var="classNum" items="${classNumList}">
                        <option value="${classNum}" ${classNum == requestScope.classNum ? 'selected' : ''}>${classNum}</option>
                    </c:forEach>
                </select><br>

                <button type="submit" name="end">登録して終了</button><br>
            </form>

            <a href="javascript:history.back();">戻る</a>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>

<script>
    function validateForm() {
        var entYear = document.getElementById("ent_year").value;
        var entYearError = document.getElementById("ent_year_error");

        if (entYear === "") {
            entYearError.style.display = "block";
            return false;
        } else {
            entYearError.style.display = "none";
        }
        return true;
    }
</script>
