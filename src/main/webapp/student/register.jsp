<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>student - list</title>
    <link rel="stylesheet" href="/style.css" />
</head>

<body>
<h1>학생-등록</h1>

<c:choose>
    <c:when test="${empty student}">
        <c:set var="action" value="/student/register" />
    </c:when>
    <c:otherwise>
        <c:set var="action" value="/student/update"/>
    </c:otherwise>
</c:choose>

<form method="post" action="${action}">
    <table>
        <tbody>
        <tr>
            <th>ID</th>
            <td><input type="text" name="id" value="${student.id}" required /></td>
        </tr>
        <!-- todo input 구현 -->
        <tr>
            <th>이름</th>
            <td><input type="text" name="name" value="${student.name}" required></td>
        </tr>
        <tr>
            <th>성별</th>
            <td><input type="radio" name="gender" value="M"
<c:if test="${student.gender =='M'}">checked </c:if> required>남
                <input type="radio" name="gender" value="F"
                <c:if test="${student.gender == 'F'}"> checked </c:if>required>여</td>
        </tr>
        <tr>
            <th>나이</th>
            <td><input type="text" name="age" value="${student.age}" required></td>
        </tr>
        </tbody>
    </table>
    <p>
        <button type="submit">
            <c:choose>
                <c:when test="${empty student}">
                    등록
                </c:when>
                <c:otherwise>
                    수정
                </c:otherwise>
            </c:choose>
        </button>
    </p>
</form>
</body>
</html>
