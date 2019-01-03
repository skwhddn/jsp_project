<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp"%>
<html>
<body>
	<h2>재고수정처리 페이지</h2>
	<c:if test="${result == 0}">
		<script type="text/javascript">
			alert("수정처리 실패하였습니다.");
		</script>
	</c:if>
	<c:if test="${result != 0}">
		<script type="text/javascript">		
				alert("수정처리되었습니다.");
				window.location = "hostlist.ad";
		</script>
	</c:if>
</body>
</html>