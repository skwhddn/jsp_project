<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="project.vo.projectVO" %>
<%@ include file="../setting.jsp"%>
<link href="${css}mafage.css" rel="stylesheet" type="text/css">
<script src="${project}script.js">
</script>
<html>
<title>회원수정처리 페이지</title>
<body>
	<h2>회원수정 처리 페이지</h2>
	<c:if test="${updateCnt == 0}">
	<script type="text/javascript">
		errorAlert(updateError);
	</script>
	</c:if>
	<c:if test="${updateCnt != 0}">
		<script type="text/javascript">
		setTimeout(function() {
			alert("수정처리되었습니다.");
			window.location = 'main.cu';
		}, 1000);
	</script>
	</c:if>
</body>
</html>