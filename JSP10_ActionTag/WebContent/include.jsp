<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>include</title>
</head>
<body>
<!-- Action Tag -->
<p> 지금 현재 페이지는 include 페이지 입니다...</p>  <!--  과연 표시 될까? -->
<jsp:include page = "sub.jsp"/>
<p> 위 라인의 내용은 sub 페이지 의 내용입니다 </p> <!--  과연 표시 될까? -->

</body>
</html>