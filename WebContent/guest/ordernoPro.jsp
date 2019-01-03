<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp"%>
<html>
<body>
	<h2>주문목록 삭제 페이지</h2>
	<c:if test="${deleteCnt == 0}">
		<script type="text/javascript">
			alert("주문 취소 실패 하였습니다.");
		</script>
	</c:if>
	<c:if test="${deleteCnt != 0}">
		<script type="text/javascript">		
				alert("주문 취소 처리되었습니다.");
				window.location = "orderlist.cu";
		</script>
	</c:if>
</body>
</html>