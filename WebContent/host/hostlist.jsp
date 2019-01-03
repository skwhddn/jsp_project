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
				<li><a href="hostlist.ad">재고목록</a></li>
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
<span class="name">SKOCK LIST</span>
</div>
</div>
<form action="delete.ad" method="post" name="update">
<input type="hidden" name="pageNum" value="${pageNum}"> 
<table style="margin-left: auto; margin-right: auto;">
		<tr>
			<th colspan="6" align="left" style="height:25px; width:50px;">
			상품목록(상품갯수 : ${cnt}) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</th>
		</tr>
	<tr style="background: #CCCCCC">
		<th><input class="ok" type ="checkbox" id="ck" name="Check" value="all" onclick="checkAll();"></th>
		<th>번호</th>
		<th>상품</th>
		<th>상품명</th>
		<th>색상</th>
 		<th>수량</th>
		<th>가격</th>
		<th>종류</th>
		<th>등록일</th>

	</tr>
	<c:forEach var="dto" items="${dtos}">
	<tr>
		<th><input class="ok" type ="checkbox" name="num" value="${dto.num}"></th>
		<td>${dto.num}</td>
		<td><a href="update.ad?pageNum=${pageNum}&num=${dto.num}"><img src="${images}top/${dto.img}"></a></td>
		<td>${dto.subject}</td>
		<td>${dto.color}</td>
		<td>${dto.age}</td>
		<td>${dto.price}</td>
		<td>${dto.kind}</td>
		<td>
		<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${dto.reg_date}"/>
		</td>
	</tr>
	</c:forEach>
	<tr style="background: #CCCCCC">
		<td colspan="9" >
		<input class="button" type="button" value="재고추가" onclick="window.location='stockadd.ad'">
		<input class="button" type="submit" value="선택삭제">
		</td>
	</tr>
</table>
</form>
	<img src="${images}oo.PNG" class="oo"
		style="margin-left: auto; margin-right: auto; display: block;">
	<div class="sidebar">
		<ul>
			<li><a href="hostmain.cu"><img src="${images}logo.PNG"
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