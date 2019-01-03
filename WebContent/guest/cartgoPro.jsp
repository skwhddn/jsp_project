<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp"%>
<html>
<body>
	<h2>즉시구매 페이지</h2>
	<c:if test="${insertCnt == 0}">
		<script type="text/javascript">
			alert("즉시 구매 실패 하였습니다.");
		</script>
	</c:if>
	<c:if test="${insertCnt != 0}">
		<script type="text/javascript">		
				alert("즉시 구매 했습니다..");
				window.location = "orderlist.cu";
		</script>
	</c:if>
</body>
</html>