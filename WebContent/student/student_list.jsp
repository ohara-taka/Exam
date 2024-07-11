<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
    <title>得点管理システム</title>
    <link rel="stylesheet" href="../css/menu-styles.css">
    <style>
        .container2 {
            display: flex;
            justify-content: space-around;
            align-items: center;
            gap: 20px;
            width: 95%;
            margin: auto;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            flex-wrap: wrap; /* 要素を折り返す */
        }
        .student-info-registration {
            text-align: left;
            background-color: lightgray;
        }
        .form-group {
            display: flex;
            flex-direction: column;
            width: 250px;
        }
        .form-group label {
            margin-bottom: 5px;
        }
        .checkbox-group {
            display: flex;
            align-items: center;
            gap: 5px;
        }
        button {
            padding: 8px 16px;
            background-color: #6c757d;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #5a6268;
        }
        .new-registration {
            text-align: right;
            margin-bottom: 10px;
        }
        select {
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 5px;
            appearance: none;
        }
        .error-message {
            color: #ffcc00;
            font-weight: bold;
            width: 100%; /* エラーメッセージを次の行に表示 */
            text-align: left;
            margin-top: 10px;
            margin-left: 10px;
        }
        .table-container {
            margin-top: 20px;
        }
        .student-custom-table {
            width: 100%;
            border-collapse: collapse;
        }
        .student-custom-table th, .student-custom-table td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: center;
        }
    </style>
</head>

<%@include file="../header.jsp"%>

<div class="container">
    <div class="content">
        <%@include file="sideber.jsp"%>
        <div class="main-content">
            <h2 class="student-info-registration">&nbsp;&nbsp;&nbsp;学生管理</h2>
            <div class="new-registration">
                <a href="StudentCreate.action">新規登録</a>
            </div>
            <form action="StudentList.action" method="post">
                <div class="container2">
                    <!-- <div class="field-container"> -->
                    <div class="form-group">
                        入学年度:
                        <select name="f1">
                            <option value="0">選択してください</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${f1 == year}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <!-- <div class="field-container"> -->
                    <div class="form-group">
                        クラス:
                        <select name="f2">
                            <option value="0">選択してください</option>
                            <c:forEach var="classNum" items="${class_num_set}">
                                <option value="${classNum}" <c:if test="${f2 == classNum}">selected</c:if>>${classNum}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="field-container">
                        在学:
                        <input type="checkbox" name="f3" value="1" <c:if test="${f3 != null}">checked</c:if> />
                    </div>
                    <div class="field-container">
                        <button type="submit">絞り込み</button>
                    </div>
                    <c:if test="${not empty errors}">
                        <div class="error-message">${errors["f1"]}</div>
                    </c:if>
                </div>
            </form>
            <div class="table-container">
                <c:choose>
                    <c:when test="${students != null}">
                        <c:if test="${students.size() > 0}">
                            <div>検索結果件数: ${students.size()}件</div>
                            <table class="student-custom-table">
                                <tr>
                                    <th>入学年度</th>
                                    <th>学生番号</th>
                                    <th>氏名</th>
                                    <th>クラス</th>
                                    <th>在学中</th>
                                    <th>変更</th>
                                </tr>
                                <c:forEach var="student" items="${students}">
                                    <tr>
                                        <td>${student.entYear}</td>
                                        <td>${student.no}</td>
                                        <td>${student.name}</td>
                                        <td>${student.classNum}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${student.isAttend()}">○</c:when>
                                                <c:otherwise>×</c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td><a href="StudentUpdate.action?no=${student.no}">変更</a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <div>学生情報が存在しませんでした</div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<%@include file="../footer.jsp"%>
