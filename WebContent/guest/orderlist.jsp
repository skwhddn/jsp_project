<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp"%>
<link href="${css}order.css" rel="stylesheet" type="text/css">
<html>
<body>
 	<c:if test="${sessionScope.memid == null}">
		<script type="text/javascript">
		alert("로그인해주세요");
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
<br><br><br><br>
<div class="titbox">
<div class="title">    
<span class="name">ORDER LIST</span>
</div>
</div>
<form action="cartdelete.cu" method="post" name="update">
<input type="hidden" name="pageNum" value="${pageNum}">
<table style="margin-left: auto; margin-right: auto;">
	<tr style="background: #CCCCCC">
		<th><input class="ok" type ="checkbox" id="ck" name="Check" value="all" onclick="checkAll();"></th>
		<th>번호</th>
		<th>상품</th>
		<th>상품명</th>
		<th>수량</th>
		<th>색상</th>
		<th>가격</th>
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
		<td>
		<input class="button" type="button" value="주문취소" 
		onclick="window.location='orderno.cu?pageNum=${pageNum}&num=${dto.num}&subject=${dto.subject}&age=${dto.age}&color=${dto.color}&price=${dto.price}&img=${dto.img}'">
		</td>
	</tr>
	</c:forEach>
</table>
<div class="titbox">
<div class="title">    
<span class="name">ORDER OK LIST</span>
</div>
</div>
<table style="margin-left: auto; margin-right: auto;">
	<tr style="background: #CCCCCC">
		<th><input class="ok" type ="checkbox" id="ck" name="Check" value="all" onclick="checkAll();"></th>
		<th>번호</th>
		<th>상품</th>
		<th>상품명</th>
		<th>수량</th>
		<th>색상</th>
		<th>가격</th>
		<th>선택</th>
	</tr>
	<c:forEach var="dto" items="${vo}">
	<tr>
		<td><input class="ok" type ="checkbox" name="num" value="${dto.num}"></td>
		<td>${dto.num}</td>
		<td><a href="detailpage.cu?pageNum=${pageNum}&num=${dto.num}"><img src="${images}top/${dto.img}"></a></td>
		<td>${dto.subject}</td>
		<td>${dto.age}</td>
		<td>${dto.color}</td>
		<td>${dto.price}</td>
		<td>
		<input class="button" type="button" value="환불요청"
		onclick="window.location='ordercancel.cu?pageNum=${pageNum}&num=${dto.num}&subject=${dto.subject}&age=${dto.age}&color=${dto.color}&price=${dto.price}&img=${dto.img}'">
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