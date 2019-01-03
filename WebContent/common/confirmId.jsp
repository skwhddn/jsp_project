<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../setting.jsp"%>
<link type="text/css" rel="stylesheet" href="${project}conid.css">
<script src="${project}script.js">
</script>
<html>
<body onload="confirmIdFocus();">
	<h2 align="center">중복환인 페이지</h2>

	<form action="confirmId.cu" method="post" name="confirmform"
		onsubmit="return confirmIdCheck();">
		<c:if test="${selectCnt == 1}">
			<table>
				<tr> 
				<th colspan="2"><span>${id}</span>는 사용할수 없습니다.
				</tr>
				<tr>
					<th>아이디 :</th>
					<td><input class="inputo" type="text" name="id" maxlength="20"
						></td>
				</tr>
				<tr>
					<td colspan="2"><input class="inputbutton" type="submit"
						value="확인"> <input class="inputbutton" type="reset"
						value="취소" onclick="self.close();"></td>
				</tr>
			</table>
		</c:if>
		<c:if test="${selectCnt != 1}">
			<table>
				<tr>
					<td align="center"><span>${id}</span>는 사용할수 있습니다.</td>
				</tr>
				<tr>
					<th>
					<input class="inputButton" type="button"
						value="확인" onclick="setId('${id}');">
					</th>
				</tr>
			</table>
		</c:if>
	</form>

</body>
</html>