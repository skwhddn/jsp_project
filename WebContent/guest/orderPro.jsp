<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp"%>
<html>
<body>
	<h2>주문추가처리 페이지</h2>
	<c:if test="${insertCnt == 0}">
		<script type="text/javascript">
			alert("구매요청 실패하였습니다.");
		</script>
	</c:if>
	<c:if test="${insertCnt != 0}">
		<script type="text/javascript">		
				alert("구매요청 처리 되었습니다.");
				window.location = "orderlist.cu";
		</script>
	</c:if>
</body>
</html>