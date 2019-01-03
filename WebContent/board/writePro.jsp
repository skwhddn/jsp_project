<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp" %>
<html>
<body>
	<h2 align="center">글쓰기 처리 페이지</h2>
	<c:if test="${insertCnt == 0}">
		<script type="text/javascript">
		alert("오류");
		</script>
	</c:if>
	<c:if test="${insertCnt != 0}">
		<c:redirect url="boardList.cu"/>
	</c:if>
</body>
</html>