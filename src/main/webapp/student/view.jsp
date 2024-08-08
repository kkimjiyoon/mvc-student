<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cfmt" uri="http://nhnacademy.com/cfmt" %>
<html>
<head>
    <title>학생-조회</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<h1>학생-조회</h1>
<table style="width: 600px">
    <tbody>
    <!-- todo view 구현 -->
    <tr>
        <td>아이디</td>
        <td>${student.id}</td>
    </tr>
    <tr>
        <td>이름</td>
        <td>${student.name}</td>
    </tr>
    <tr>
        <td>성별</td>
        <td>${student.gender}</td>
    </tr>
    <tr>
        <td>나이</td>
        <td>${student.age}</td>
    </tr>
    <tr>
        <td>등록일</td>
        <td>${cfmt:formatDate(student.createdAt, 'yyyy-MM-dd HH:mm:ss')}</td>
    </tr>
    </tbody>
</table>
<ul>
    <li><a href="/student/list">리스트</a></li>
    <li>
        <!-- todo ${update_link} 설정 c:url -->
        <a href="/student/update?id=${student.id}">수정</a>
    </li>
    <li>
        <!-- todo 삭제버튼 구현, method=post-->
        <form method="post" action="/student/delete?id=${student.id}">
            <button type="submit">삭제</button>
        </form>
     </li>

 </ul>

</body>
</html>