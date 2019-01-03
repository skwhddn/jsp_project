<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp"%>
<link href="${css}join.css" rel="stylesheet" type="text/css">
<script src="${project}script.js">
</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<html>
<title>로그인</title>
<body>
	<header>
			<div class="gnb">
			<ul>
			<li><a href="login.cu">로그인</a></li>
				<li><a href="join.cu">회원가입</a>
				<li><a href="login.cu">마이페이지</a></li>
				<li><a href="cartlist.cu">장바구니</a></li>
				<li><a href="orderlist.cu">주문목록</a></li>
				<li><input type='text' class='input_text'>
					<button type='submit' class='sch_smit'>검색</button></li>
			</ul>
		</div>
	</header>
	<br><br><br><br>
	
	<form name="joinon" method="post" action="hostmain.ad"
		onsubmit="return joinCheck();">
	<input type="hidden" name="hiddenid" value="0">
	<table align="center">
			<tr>
				<th colspan="2">관리자 로그인 정보를 입력하세요</th>
			</tr>
			<tr>
				<th> * 아이디</th>
				<td><input class="input" type="text" name="id" maxlength="20" style="width:300px">
				</td>
			</tr>
			
			<tr>
				<th> * 비밀번호</th>
				<td>
				<input class="input" type="password" name="pwd" maxlength="10" style="width:300px">
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input class="inputButton" type="submit" value="로그인">
					<input class="inputButton" type="button" value="게스트로그인" onclick="window.location='login.cu'">
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
				<li><a href="board.cu">Q&A</a></li>
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
</body>
</html>