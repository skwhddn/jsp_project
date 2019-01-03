<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp"%>
<link href="${css}join.css" rel="stylesheet" type="text/css">
<script src="${project}script.js">
</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<html>
<title>로그인</title>
<body onload="pwdFocus();">
	<%!
		String strId;
	%>
	<c:if test="${sessionScope.memid != null}">
	<header>
		<div class="gnb">
			<ul>
				<li><span>${sessionScope.memid}</span>님.</li>
				<li><a href="logout.cu">로그아웃</a>
				<li><a href="guestmafage.cu">마이페이지</a></li>
				<li><a href="../guest/cart.html">장바구니</a></li>
				<li><a href="../guest/order.html">주문목록</a></li>
				<li><input type='text' class='input_text'>
					<button type='submit' class='sch_smit'>검색</button></li>
			</ul>
		</div>
	</header>
	<br><br>
	<h2 align="center">글 수정</h2>
	<form action="modifyView.cu" method="post" name="pwdform"
						onsubmit="return pwdCheck();">
	
	<!-- hidden : submit일 경우 input 태그에 보이지 않는 값을 넘길 때 -->
	<input type="hidden" name="num" value="${num}">
	<input type="hidden" name="pageNum" value="${pageNum}">
	
		<table align="center">
			<tr>
				<th colspan="2">
					비밀번호를 입력하지마
				</th>
			</tr>
			
			<tr>
				<th> 비밀번호 </th>
				<td><input class="input" type="password" name="pwd"
									maxlength="10" style="width:500"></td>
			</tr>
			<tr>
				<th colspan="2">
					<input class="inputButton" type="submit" value="확인">
					<input class="inputButton" type="reset" value="취소"
									onclick="window.history.back();">
				</th>
			</tr>
		</table>
	</form>
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<img src="${images}oo.PNG" class="oo"
		style="margin-left: auto; margin-right: auto; display: block;">
	<div class="sidebar">
		<ul>
			<li><a href="main.cu"><img src="${images}logo.PNG"
					width="221px"></a></li>
			<li><a href="top.cu">TOP</a></li>
			<li><a href="pants.cu">PANTS</a></li>
			<li><a href="shoes.cu">SHOES</a></li>
			<li></li>
			<br>
			<br>
			<div id="qn">
				<li><a href="">Q&A</a></li>
				<li><a href="">CodyDinator</a></li>
			</div>
			<br>
			<br>
			<li>010-5586-7824</li>
			<li>MON-FRI 12AM ~ 6PM</li>
			<li>LUNCH 12AM ~ 6PM</li>
			<li>SAT.SUN.HOLIDATY OFF</li>
			<br>
			<li>국민 121-5187-1166</li>
			<li>예금주 : 나종우</li>
		</ul>
	</div>
	</c:if>
</body>
</html>