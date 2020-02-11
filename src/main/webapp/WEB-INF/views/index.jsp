<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
DB에 들어가는지 확인하기<br>
<c:forEach var="company" items="${companys}" >
 회사 : ${company.title}<br>
링크 : <a href="${company.url}">${company.url}</a><br> 
<br><br><br><hr>
</c:forEach>
</body>
<script>

</script>
</html>