<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp"%>
<script src="${project}script.js">
</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<html>
<title>회원가입</title>
<body>
	<h2>회원탈퇴 처리 페이지</h2>
	<c:if test="${selectCnt == 1}">
		<c:if test="${deleteCnt == 0}">
			<script type="text/javascript">
				errorAlert(deleteError);
			</script>
		</c:if>

		<c:if test="${deleteCnt != 0}">
			<c:remove var="memid" scope="session"/>
			<c:set var="cnt" value = "2" scope="request"/>
			<script type="text/javascript">
					alert("탈퇴처리되었습니다.");
					window.location = 'main.cu';
			</script>
		</c:if>
		</c:if>
		<c:if test="${selectCnt != 1}">
		<script type="text/javascript">
			errorAlert(passwdError);
		</script>
		</c:if>
</body>
</html>