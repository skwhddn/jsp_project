<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="project.vo.projectVO" %>
<%@ include file="../setting.jsp"%>
<link href="${css}mafage.css" rel="stylesheet" type="text/css">
<script src="${project}script.js">
</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<html>
<title>회원수정</title>
<body>
	<c:if test="${sessionScope.memid == null}">
	<script type="text/javascript">
			alert("로그인해주시길바랍니다.");
			window.location="login.cu";
	</script>
	</c:if>
<c:if test="${sessionScope.memid != null}">
	<header>
			<div class="gnb">
			<ul>
				<li><span>${sessionScope.memid}</span>님.</li>
				<li><a href="logout.cu">로그아웃</a>
				<li><a href="mafage.cu">마이페이지</a></li>
				<li><a href="cartlist.cu">장바구니</a></li>
				<li><a href="orderlist.cu">주문목록</a></li>
				<li><input type='text' class='input_text'>
					<button type='submit' class='sch_smit'>검색</button></li>
			</ul>
		</div>
	</header>
<img src="${images}mafaege.PNG" width="400" height="120">
	<section>
		<div id="main">
			<div class="titlebox"></div>
			<div class="guestMenu">
				<div class="subMenu">
					<a href="mafage.cu"> <span class="tit">
							정보변경 </span></a>
				</div>
				<div class="subMenu">
					<a href="board.cu"> <span class="tit"> 문의내역
					</span></a>
				</div>
				<div class="subMenu">
					<a href="cartlist.cu"> <span class="tit"> 장바구니 </span></a>
				</div>
				<div class="subMenu">
					<a href="guestdelete.cu"> <span class="tit">
							회원탈퇴 </span></a>
				</div>
				<div class="subMenu">
					<a href="../guest/codydinator.html"> <span class="tit">
							코디스타일 </span></a>
				</div>
				<div class="subMenu">
					<a href="orderlist.cu"> <span class="tit"> 주문내역 </span></a>
				</div>
			</div>
		</div>
	</section>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
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