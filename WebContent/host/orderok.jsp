<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp"%>
<html>
<body>
	<h2>주문 승인 페이지</h2>
	<c:if test="${insertCnt == 0}">
		<script type="text/javascript">
			alert("승인 실패하였습니다.");
		</script>
	</c:if>
	<c:if test="${insertCnt != 0}">
		<script type="text/javascript">		
				alert("승인처리되었습니다.");
				window.location = "hostorderlist.ad";
		</script>
	</c:if>
</body>
</html>