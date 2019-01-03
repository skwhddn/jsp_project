<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp"%>
<html>
<body>
	<h2>회원수정 처리 페이지</h2>
	<c:if test="${updateCnt == 0}">
		<script type="text/javascript">
			errorAlert(updateError);
		</script>
	</c:if>
	<c:if test="${updateCnt != 0}">
		<script type="text/javascript">		
				alert("수정처리되었습니다.");
				window.location = "board.cu?pageNum=${pageNum}";
		</script>
	</c:if>
</body>
</html>