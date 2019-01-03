<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp"%>
<html>
<body>
	<h2>게시글 처리 페이지</h2>
	<c:if test="${selectCnt == 0}">
		<script type="text/javascript">
			errorAlert(pwdError);
		</script>
	</c:if>
<c:set var="pageNum" value="${pageNum}"/>
	<c:if test="${selectCnt != 0}">
		<c:if test="${deleteCnt == 0}">
			<script type="text/javascript">
				errorAlert(deleteError);
			</script>
		</c:if>
		<c:if test="${deleteCnt != 0}">
			<script type="text/javascript">
				alert("글삭제에 성공했습니다.");
				window.location="board.cu?pageNum="+${pageNum};
			</script>
		</c:if>
	</c:if>
</body>
</html>