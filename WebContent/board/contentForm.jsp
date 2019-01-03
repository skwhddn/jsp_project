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
				<li><a href="../guest/cart.html">장바구니</a></li>
				<li><a href="../guest/order.html">주문목록</a></li>
				<li><input type='text' class='input_text'>
					<button type='submit' class='sch_smit'>검색</button></li>
			</ul>
		</div>
	</header>
	<h2><center> 상세페이지 </center></h2>
	<table align="center">
		<tr>
			<th style="width:150px">글번호</th>
			<td style="Width:150px">${number}</td>
			<th style="Width:150px"> 조회수 </th>
			<td style="Width:150px">${dto.readCnt}</td>
		</tr>
		<tr>
			<th style="width:150px"> 작성자 </th>
			<td style="width:150px">${dto.m_id}</td>
			<th style="width:150px">작성일</th>
			<td style="width:150px" align="center">
				<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${dto.reg_date}"/>
				</td>
		</tr>
		<tr>
			<th>글제목</th>
			<td colspan="3">${dto.subject}</td> 
		</tr>
		<tr>
			<th>글내용</th>
			<td colspan="3" style="height:150px">${dto.content}</td>
		</tr>	
		<tr>
			<th> ip </th>
			<td colspan="3">${dto.ip}</td>
		</tr>
		<tr>
			<th colspan="4">
				<input class="inputButton" type="button" value="글수정"
				onclick="window.location='modifyForm.cu?num=${dto.num}&pageNum=${pageNum}'">
				<input class="inputButton" type="button" value="글삭제"
				onclick="window.location='deleteForm.cu?num=${dto.num}&pageNum=${pageNum}'">
				<input class="inputButton" type="button" value="답글쓰기"
				onclick="window.location='writeForm.cu?num=${dto.num}&ref=${dto.ref}&ref_step=${dto.ref_step }&ref_level=${dto.ref_level}&pageNum=${pageNum}'">
				<input class="inputButton" type="button" value="목록보기"
				onclick="window.location='board.cu?pageNum=${pageNum}'">
			</th>
		</tr>
	</table>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
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
</body>
</html>