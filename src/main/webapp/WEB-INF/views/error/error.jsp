<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="KOR">
<head>
<meta charset="UTF-8">
</head>
<body>
	<h1>
		에러가 발생하였습니다
	</h1>
	<h4>
	${exception.getMessage()}
	</h4>
	
	<ul>
		<c:forEach items="${exception.getStackTrace()}" var="stack">	
			<li>${stack.toString()}</li>
		</c:forEach>
	</ul>
	
</body>
</html>
