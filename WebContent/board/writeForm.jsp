<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp"%>
<link href="${css}join.css" rel="stylesheet" type="text/css">
<script src="${project}script.js">
</script>
<html>
<title>글쓰기</title>

<body onload="writeFocus();">

	<%!String strId;%>

	<c:if test="${sessionScope.memid != null}">
		<header>
			<div class="gnb">
				<ul>
					<li><span>${sessionScope.memid}</span>님.</li>
					<li><a href="join.cu">회원가입</a>
					<li><a href="login.cu">마이페이지</a></li>
					<li><a href="../guest/cart.html">장바구니</a></li>
					<li><a href="../guest/order.html">주문목록</a></li>
					<li><input type='text' class='input_text'>
						<button type='submit' class='sch_smit'>검색</button></li>
				</ul>
			</div>
		</header>
		<br>
		<br>
		<h2 align="center">글쓰기</h2>
		<form action="writePro.cu" method="post" name="writeform"
			onsubmit="return writeCheck();">
			<input type="hidden" name="num" value="${num}"> <input
				type="hidden" name="ref" value="${ref}"> <input
				type="hidden" name="ref_step" value="${ref_step}"> <input
				type="hidden" name="ref_level" value="${ref_level}">

			<table align="center">
				<tr>
					<th>작성자</th>
					<td><input class="input" type="text" name="writer"
						maxlength="20" style="width:500"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input class="input" type="password" name="pwd"
						maxlength="10"style="width:500"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input class="input" type="text" name="subject"
						maxlength="20" style="width: 500px"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea class="input" rows="10" cols="40" name="content"
							style="resize:none; width:400;"></textarea></td>
				</tr>
				<tr>
					<th colspan="2"><input class="inputbutton" type="submit"
						value="글쓰기"> <input class="inputbutton" type="reset"
						value="재작성"> <input class="inputbutton" type="button"
						value="목록" onclick="window.location='board.cu?pageNum=${pageNum}'">
					</th>
				</tr>
			</table>
		</form>
	</c:if>
	<br><br><br><br><br><br><br><br><br>
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
	<c:if test="${sessionScope.memid == null}">
		<header>
			<div class="gnb">
			<ul>
				<li><a href="login.cu">로그인</a></li>
				<li><a href="join.cu">회원가입</a>
				<li><a href="login.cu">마이페이지</a></li>
				<li><a href="../guest/cart.html">장바구니</a></li>
				<li><a href="../guest/order.html">주문목록</a></li>
				<li><input type='text' class='input_text'>
					<button type='submit' class='sch_smit'>검색</button></li>
			</ul>
		</div>
	</header>
	<img src="${images}join.PNG" width="400" height="120">
	<form name="joinon" method="post" action="guestlogin.cu"
		onsubmit="return joinCheck();">
	<input type="hidden" name="hiddenid" value="0">
	<table align="center">
			<tr>
				<th colspan="2">로그인 정보를 입력하세요</th>
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
					<input class="inputButton" type="button" value="회원가입" onclick="window.location='join.cu'">
					<input class="inputButton" type="button" value="관리자페이지" onclick="window.location='hostlogin.ad'">
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