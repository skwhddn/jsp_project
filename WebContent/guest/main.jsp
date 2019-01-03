<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp"%>
<link href="${css}CssMain.css" rel="stylesheet" type="text/css">
<html>
<title>첫시작페이지</title>
<body>
	<%!
		String strId;
	%>
	<c:if test="${sessionScope.memid == null}">
	<header>
		<div class="gnb">
			<ul>
				<li><a href="login.cu">로그인</a></li>
				<li><a href="join.cu">회원가입</a>
				<li><a href="guestmafage.cu">마이페이지</a></li>
				<li><a href="cartlist.cu">장바구니</a></li>
				<li><a href="orderlist.cu">주문목록</a></li>
				<li><input type='text' class='input_text'>
					<button type='submit' class='sch_smit'>검색</button></li>
			</ul>
		</div>
	</header>
	<br>
	<br>
	<div id="content">
	<div id="slide">
			<input type="radio" name="pos" id="pos1" checked> <input
				type="radio" name="pos" id="pos2"> <input type="radio"
				name="pos" id="pos3"> <input type="radio" name="pos"
				id="pos4">
			<ul>
				<li><img src="${images}side.PNG" width="100%"></li>
				<li><img src="${images}side1.PNG" width="100%" height="100%"></li>
				<li><img src="${images}side2.PNG" width="100%"></li>
				<li><img src="${images}side3.PNG" width="100%"></li>
			</ul>
			<p class="pos">
				<label for="pos1"></label> <label for="pos2"></label> <label
					for="pos3"></label> <label for="pos4"></label>
			</p>
		</div>
		<div class="section2">
			<div class="hit_product">
				<ul>
					<li><a href="#"><img src="${images}best/best1.PNG"
							width="465" height="600"></a></li>
					<li><a href="#"><img src="${images}best/best2.PNG"
							width="465" height="600"></a></li>
					<li><a href="#"><img src="${images}best/best3.PNG"
							width="465" height="600"></a></li>
					<li><a href="#"><img src="${images}best/best4.4.PNG"
							width="465" height="600"></a></li>
				</ul>
			</div>
			<div class="hit_product1">
				<ul>
					<li><a href="#"><img src="${images}best/best4.PNG"
							width="465" height="600"></a></li>
					<li><a href="#"><img src="${images}best/best5.PNG"
							width="465" height="600"></a></li>
					<li><a href="#"><img src="${images}best/best6.PNG"
							width="465" height="600"></a></li>
					<li><a href="#"><img src="${images}best/best7.PNG"
							width="465" height="600"></a></li>
				</ul>
			</div>
		</div>
	</div>
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
				<li><a href="login.cu">CodyDinator</a></li>
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
	<c:if test="${sessionScope.memid != null}">
		<header>
		<div class="gnb">
			<ul>
				<li><span>${sessionScope.memid}</span>님.</li>
				<li><a href="logout.cu">로그아웃</a>
				<li><a href="guestmafage.cu">마이페이지</a></li>
				<li><a href="cartlist.cu">장바구니</a></li>
				<li><a href="orderlist.cu">주문목록</a></li>
				<li><input type='text' class='input_text'>
					<button type='submit' class='sch_smit'>검색</button></li>
			</ul>
		</div>
	</header>
	<br>
	<br>
	<div id="content">
	<div id="slide">
			<input type="radio" name="pos" id="pos1" checked> <input
				type="radio" name="pos" id="pos2"> <input type="radio"
				name="pos" id="pos3"> <input type="radio" name="pos"
				id="pos4">
			<ul>
				<li><img src="${images}side.PNG" width="100%"></li>
				<li><img src="${images}side1.PNG" width="100%" height="100%"></li>
				<li><img src="${images}side2.PNG" width="100%"></li>
				<li><img src="${images}side3.PNG" width="100%"></li>
			</ul>
			<p class="pos">
				<label for="pos1"></label> <label for="pos2"></label> <label
					for="pos3"></label> <label for="pos4"></label>
			</p>
		</div>
		<div class="section2">
			<div class="hit_product">
				<ul>
					<li><a href="#"><img src="${images}best/best1.PNG"
							width="465" height="600"></a></li>
					<li><a href="#"><img src="${images}best/best2.PNG"
							width="465" height="600"></a></li>
					<li><a href="#"><img src="${images}best/best3.PNG"
							width="465" height="600"></a></li>
					<li><a href="#"><img src="${images}best/best4.4.PNG"
							width="465" height="600"></a></li>
				</ul>
			</div>
			<div class="hit_product1">
				<ul>
					<li><a href="#"><img src="${images}best/best4.PNG"
							width="465" height="600"></a></li>
					<li><a href="#"><img src="${images}best/best5.PNG"
							width="465" height="600"></a></li>
					<li><a href="#"><img src="${images}best/best6.PNG"
							width="465" height="600"></a></li>
					<li><a href="#"><img src="${images}best/best7.PNG"
							width="465" height="600"></a></li>
				</ul>
			</div>
		</div>
	</div>
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
	</c:if>
</body>
</html>