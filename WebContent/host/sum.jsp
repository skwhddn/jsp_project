<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp"%>
<link href="${css}sum.css" rel="stylesheet" type="text/css">
<html>
<head>
<script type="text/javascript" scr="https://www.gstatic.com/charts/loader.js"></script>
<script>
	goole.charts.load('current', {packages:['corechart']});
</script>
</head>
<body>	
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
		<br><br><br><br><br>
		<form action="delete.ad" method="post" name="update">
			<input type="hidden" name="pageNum" value="${pageNum}">
			<table style="margin-left: auto; margin-right: auto;">
				<tr style="background: #000000; color:#ffffff;">
					<th>결산 총 판매액 ! : ${totalSale}원</th>
				</tr>
			</table>
		</form>
		<c:set var="price" value="${firstChat['price']}"></c:set>
		<c:set var="kind" value="${firstChat['kind']}"></c:set>
		<div id="firstChat" 
		style="margin-left: auto; margin-right: auto; display: block;
		width: 2000px; height: 600px; font-size:20px;"></div>
		<script type="text/javascript">
		google.charts.setOnLoadCallback(drawChartFirst);
		var firstChart_options = {
				title : '구매수량',
				width : 600,
				height : 400,
				bar : {
					groupWidth : '50%'
				},
				legend : {
					position : 'bottom'
				}
		};
		function drawChartFirst() {
			var date = google.visualization.arrayToDateTable([
				['Element', '옷 종류별'],
				['TOP', ${price}],
				['PANTS', ${price}],
				['SHOSE', ${price}]
			]);
			var firstChart = new goole.visualization.ColumnChart(document,getElementById('firstChat'));
			firstChart.draw(date,firstChart_options);
		}
		</script>
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