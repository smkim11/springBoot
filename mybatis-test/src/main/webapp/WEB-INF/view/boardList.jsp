<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 자바코드 대신 EL, JSTL -->
	<h1>BOARD LIST</h1>
	<table border="1">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>삭제</th>
	</tr>
		
	<c:forEach var="b" items="${list}"> <!-- == for(Board b : list) -->
		<tr>
			<td>${b.boardNo}</td>
			<td>${b.boardTitle}</td>
			<td><a href="/remove?boardNo=${b.boardNo}">삭제</a></td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>