<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp"%>
<link href="${css}order.css" rel="stylesheet" type="text/css">
<html>
<body>
	<c:if test="${sessionScope.memid != null}">
		<header>
		<div class="gnb">
			<ul>
				<li><span>${sessionScope.memid}</span>님.</li>
				<li><a href="hostlogin.ad">로그아웃</a></li>
				<li><a href="hostlist.ad">재고목록</a>
				<li><a href="hostorderlist.ad">주문목록</a></li>
				<li><a href="hostcancel.ad">환불목록</a></li>
				<li><a href="sum.ad">결산</a></li>
				<li><a href="board.cu">관리자 게시판</a></li>
				<li><input type='text' class='input_text'>
					<button type='submit' class='sch_smit'>검색</button></li>
			</ul>
		</div>
	</header>
<br><br><br><br>
<div class="titbox">
<div class="title">    
<span class="name">HOST ORDER LIST</span>
</div>
</div>
<form action="guestorderadd.ad" method="post" name="update">
<table style="margin-left: auto; margin-right: auto;">
	<tr style="background: #CCCCCC">
		<th><input class="ok" type ="checkbox" id="ck" name="Check" value="all" onclick="checkAll();"></th>
		<th>번호</th>
		<th>상품</th>
		<th>상품명</th>
		<th>수량</th>
		<th>색상</th>
		<th>가격</th>
		<th>주문고객</th>
		<th>선택</th>
		
	</tr>
	<c:forEach var="dto" items="${dtos}">
	<tr>
		<td><input class="ok" type ="checkbox" name="num" value="${dto.num}"></td>
		<td>${dto.num}</td>
		<td><a href="detailpage.cu?pageNum=${pageNum}&num=${dto.num}"><img src="${images}top/${dto.img}"></a></td>
		<td>${dto.subject}</td>
		<td>${dto.age}</td>
		<td>${dto.color}</td>
		<td>${dto.price}</td>
		<td>${dto.m_id}</td>
		<td><input class="button" type="button" value="주문승인" 
		onclick="window.location='guestorderadd.ad?pageNum=${pageNum}&num=${dto.num}&img=${dto.img}&age=${dto.age}&color=${dto.color}&price=${dto.price}&subject=${dto.subject}&memid=${sessionScope.memid}'">
		</td>
	</tr>
	</c:forEach>
</table>
</form>
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