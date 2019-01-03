<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp"%>
<link href="${css}order.css" rel="stylesheet" type="text/css">
<html>
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
<br><br><br>
<div class="titbox">
<div class="title">    
<span class="name">SHOPPING CART</span>
</div>
</div>
<table style="margin-left: auto; margin-right: auto;">
	<tr style="background: #CCCCCC">
		<th><input class="ok" type ="checkbox" name="Check" value="all"></th>
		<th>상품</th>
		<th>상품명</th>
 		<th>수량</th>
		<th>가격</th>
		<th>구매/삭제</th>

	</tr>
	<tr>
		<th><input class="ok" type ="checkbox" name="Check" value="all"></th>
		<td><img src="${images}top/TOP10.PNG" width="150" height="150"></td>
		<td>포이븐 오픈 카라 셔츠</td>
		<td><input class="button" type="number" id="member" value="1" min="0" max="50" step="1"></td>
		<td>100000</td>
		<th colspan="2">
		<input class="button2" type="button" value="구매">
		<input class="button2" type="button" value="삭제">
		</th>
	</tr>
		<tr>
		<th><input class="ok" type ="checkbox" name="Check" value="all"></th>
		<td><img src="${images}top/TOP1.PNG" width="150" height="150"></td>
		<td>파자마 카라 셔츠</td>
		<td><input class="button" type="number" id="member" value="1" min="0" max="50" step="1"></td>
		<td>100000</td>
		<th colspan="2">
		<input class="button2" type="button" value="구매">
		<input class="button2" type="button" value="삭제">
		</th>
	</tr>
	<tr style="background: #CCCCCC">
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td>
		<input class="button" type="button" value="전체구매">
		<input class="button" type="button" value="전체삭제">
		</td>
	</tr>
</table>
	<img src="${images}ooo.PNG" class="oo"
		style="margin-left: auto; margin-right: auto; display: block;">
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