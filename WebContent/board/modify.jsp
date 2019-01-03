<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp"%>
<link href="${css}join.css" rel="stylesheet" type="text/css">
<script src="${project}script.js">
</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<html>
<title>로그인</title>
<body onload="modifyFocus();">
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
	<h2>
	<center>글수정</center>
	</h2>
	<c:if test="${selectCnt == 0}">
		<script type="text/javascript">
			errorAlert(pwdError);
		</script>
	</c:if>
	<c:if test="${selectCnt != 0 }">
		<form action="modifyPro.cu" method="post" name="modify"
							onsubmit="return modifyCheck();">
			<input type="hidden" name="pageNum" value="${pageNum}"> <input
								type="hidden" name="num" value="${num}">
			<table align="center">
				<tr>
					<th colspan="2">수정할 정보를 입력하세요</th>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${dto.m_id}</td>
				</tr>
				<tr>
					<th>글제목</th>
					<td><input class="input" type="text" name="subject"
										maxlength="50" value="${dto.subject}" style="width: 500px">
					</td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea class="input" rows="10" cols="40" name="content"style="resize:none; width:510;">${dto.content}</textarea></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input class="input" type="password" name="pwd"
										maxlength="10" value="${dto.pwd}" style="width:500"></td>
				</tr>
				<tr>
					<th colspan="2"><input class="inputButton" type="submit"
										value="수정"> <input class="inputButton" type="reset"
										value="재작성"> <input class="inputButton" type="button"
										value="목록보기"
										onclick="window.location='board.cu?pageNum=${pageNum}'">
					</th>
				</tr>
			</table>
		</form>
	</c:if>
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