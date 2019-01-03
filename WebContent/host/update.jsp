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
<span class="name">SKOCK UPDATE</span>
</div>
</div>
    <form action="updatee.ad" method="post" name="update">
     <input type="hidden" name="pageNum" value="${pageNum}"> 
     <input	type="hidden" name="num" value="${num}">
     <table align="center">
     <tr>
       <th>번호</th>
       <td>${dto.num}</td>
      </tr>
      <tr>
       <th>상품명</th>
       <td><input type="text" name="subject" value="${dto.subject}"></td>
      </tr>
      <tr>
       <th>상품 이미지</th>
       <td><input type="file" name="img" value="${dto.img}"></td>
      </tr>
      <tr>
       <th>색상</th>
       <td><input type="text" name="color" value="${dto.color}"></td>
      </tr>
      <tr>
       <th>수량</th>
       <td><input type="text" name="age" value="${dto.age}"></td>
      </tr>
      <tr>
       <th>가격</th>
       <td><input type="text" name="price" value="${dto.price}"></td>
      </tr>
      <tr>
      	<td colspan="8" >
      		<input class="button" type="submit" value="상품수정">
      		<input class="button" type="reset" value="다시작성">
      		<input class="button" type="button" value="뒤로가기" onclick="window.history.back()">
      	</td>
      </tr>
      </table>
      </form>
	<img src="${images}oo.PNG" class="oo"
		style="margin-left: auto; margin-right: auto; display: block;">
	<div class="sidebar">
		<ul>
			<li><a href="hostmain.ad"><img src="${images}logo.PNG"
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