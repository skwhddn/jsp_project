<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp"%>
<link href="${css}item.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function buy() {
	var num = document.buygo.num.value;
	var pageNum = document.buygo.pageNum.value;
	var number = document.buygo.number.value;
	var img = document.buygo.img.value;
	var subject = document.buygo.subject.value;
	var price = document.buygo.price.value;
	var color = document.buygo.color.value;
	
	window.location="cartgo.cu?num="+num+"&pageNum="+pageNum+"&number="+number
			+"&img="+img+"&subject="+subject+"&price="+price+"&color="+color;
}
</script>
<html>
<body>
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
	<br>
	<br>
	<form name="buygo" action="cartadd.cu?memid=${sessionScope.memid}" method="post">
	<input type="hidden" name="num" value="${num}">
	<input type="hidden" name="pageNum" value="${pageNum}">
	<input type="hidden" name="img" value="${dto.img}">
	<input type="hidden" name="subject" value="${dto.subject}">
	<input type="hidden" name="price" value="${dto.price}">
	
	<div id="content">
		<div class="section2">
			<div class="hit_product">
				<ul>
					<li><img src="${images}top/${dto.img}" style="width:800px; height:800px;"></li>
					<li>상품이름 : ${dto.subject}<br> <br>
						<hr> <br> 가격 : ${dto.price}<br> <br> [fitting size]<br>
						<br> 186 / 77 - FREE사이즈를 피팅<br> <br> 세미 루즈핏 느낌을 주고
						있습니다.<br> <br> size info - (cm)<br> <br>
						<table border="1">
							<tr>
								<td colspan="2">SIZE</td>
								<td>어깨</td>
								<td>가슴</td>
								<td>소매</td>
								<td>총장</td>
							</tr>
							<tr>
								<td colspan="2">FREE</td>
								<td>53</td>
								<td>60</td>
								<td>64</td>
								<td>85</td>
							</tr>
						</table> <br>
						<ul>
						<li>색상:
							<select name="color">
								<option value="--">--</option>
								<option value="red">red</option>
								<option value="blue">blue</option>
								<option value="black">black</option>
								<option value="green">green</option>
								<option value="beige">beige</option>
							</select>
						</li>
						<li>수량 : <input type="number" name="number" value="1" min="0"
								max="100" step="1" style="width:100px; height: 50px; font-size:20px; border: none;">
						</li>
						</ul>
						<br>
						<br>
						<input class="button" type="button" value="BUY"
						onclick="buy()">
						<input class="button" type="submit" value="Cate list">
				</ul>
			</div>
		</div>
	</div>
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
	<c:if test="${sessionScope.memid == null}">
	<header>
		<div class="gnb">
			<ul>
				<li><a href="login.cu">로그인</a></li>
				<li><a href="join.cu">회원가입</a>
				<li><a href="login.cu">마이페이지</a></li>
				<li><a href="login.cu">장바구니</a></li>
				<li><a href="login.cu">주문목록</a></li>
				<li><input type='text' class='input_text'>
					<button type='submit' class='sch_smit'>검색</button></li>
			</ul>
		</div>
	</header>
	<br>
	<br>
	<form action="cartlist.cu" method="post">
	<div id="content">
		<div class="section2">
			<div class="hit_product">
				<ul>
					<li><img src="${images}top/${dto.img}" style="width:800px; height:800px;"></li>
					<li>상품이름 : ${dto.subject}<br> <br>
						<hr> <br> 가격 : ${dto.price}<br> <br> [fitting size]<br>
						<br> 186 / 77 - FREE사이즈를 피팅<br> <br> 세미 루즈핏 느낌을 주고
						있습니다.<br> <br> size info - (cm)<br> <br>
						<table border="1">
							<tr>
								<td colspan="2">SIZE</td>
								<td>어깨</td>
								<td>가슴</td>
								<td>소매</td>
								<td>총장</td>
							</tr>
							<tr>
								<td colspan="2">FREE</td>
								<td>53</td>
								<td>60</td>
								<td>64</td>
								<td>85</td>
							</tr>
						</table> <br>
						<ul>
						<li>색상:
							<select name="color">
								<option value="--">--</option>
								<option value="red">red</option>
								<option value="blue">blue</option>
								<option value="black">black</option>
								<option value="green">green</option>
								<option value="beige">beige</option>
							</select>
						</li>
						<li>수량 : <input type="number" name="num" value="1" min="0"
								max="100" step="1" style="width:100px; height: 50px; font-size:20px; border: none;" onclick="number();">
						</li>
						</ul>
						<br>
						<br>
						<input class="button" type="button" value="BUY">
						<input class="button" type="button" value="Cate list" onclick="alert('로그인해주시죠'); window.location='login.cu'">
				</ul>
			</div>
		</div>
	</div>
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