<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="project.vo.projectVO" %>
<%@ include file="../setting.jsp"%>
<link href="${css}join.css" rel="stylesheet" type="text/css">
<script src="${project}script.js">
</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<html>
<title>회원수정</title>
<body>
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
	<img src="${images}join.PNG" width="400" height="120">
	<form name="uesrre" method="post" action="userre.cu"
		onsubmit="return usercheck();">
	<table align="center">
			<tr>
				<th colspan="2">회원수정할 정보를 입력하세요.</th>
			</tr>
			<tr>
				<th> * 아이디</th>
				<td>${vo.getId()}</td>
			</tr>
			
			<tr>
				<th> * 비밀번호</th>
				<td>
				<input class="input" type="password" name="pwd" maxlength="10" value="${vo.getPwd()}" style="width:300px">
				</td>
			</tr>
			<tr>
				<th> * 비밀번호 확인 </th>
				<td>
				<input class="input" type="password" name="repwd" maxlength="10" value="${vo.getPwd()}"style="width:300px">
				</td>
			</tr>
			<tr>
				<th> * 이름 </th>
				<td>${vo.getName()}</td>
			</tr>
			<tr>
				<th> 핸드폰 번호 </th>
				<td>
				<c:if test="${vo.getHp() == null}">
					<input class="input" type="text" name="hp1" maxlength="3" style="width: 60px" onkeyup="nextHp1();"> - 
					<input class="input" type="text" name="hp2" maxlength="4" style="width: 90px" onkeyup="nextHp2();"> - 
					<input class="input" type="text" name="hp3" maxlength="4" style="width: 90px" onkeyup="nextHp3();"> 
					</c:if>
					
					<c:if test="${vo.getHp() != null}">
					<c:set var="hpArr" value="${fn:split(vo.getHp(), '-')}"/>
 					<input class="input" type="text" name="hp1" maxlength="3" style="width: 60px" value="${hpArr[0]}" onkeyup="nextHp1();"> -
 					<input class="input" type="text" name="hp2" maxlength="4" style="width: 90px" value="${hpArr[1]}" onkeyup="nextHp2();"> -
					<input class="input" type="text" name="hp3" maxlength="4" style="width: 90px" value="${hpArr[2]}" onkeyup="nextHp3();">
					</c:if> 
				</td>
			</tr>
			<tr>
				<th> * 주소 </th>
				<td>
 				 <input name="postnum" type="text" disabled="disabled" placeholder="우편번호" />
 				 <input value="주소" type="button" onclick="openAddr();" />
     			 <input name="addr1" type="text" disabled="disabled" placeholder="주소"/>
      	    	 <input name="addr2" type="text" placeholder="상세주소" style="width:460px">
				</td>
			</tr>
			<tr>
				<th>* 이메일</th>
				<td>
				<c:set var="emailArr" value="${fn:split(vo.getEmail(),'@')}"/>
					<input class="input" type="text" name="email1" maxlength="10" style="width: 65px" value="${emailArr[0]}"> @ 
					<input class="input" type="text" name="email2" maxlength="20" style="width: 80px" value="${emailArr[1]}"> 
					<select class="input" name="email3" onchange="selcetEmailChk();">
						<option value="0">직접입력</option>
						<option value="gmail.com">구글</option>
						<option value="daum.com">다음</option>
						<option value="naver.com">네이버</option>
					</select>
					</td>
				</tr>
			<tr>
				<th> * 가입일자 </th>
				<td><fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${vo.getReg_date()}"/></td>
			</tr>
			<tr>
				<th colspan="2">
					<input class="inputButton" type="submit" value="회원수정">
					<input class="inputButton" type="reset" value="재작성">
					<input class="inputButton" type="button" value="뒤로가기" onclick="window.history.back()">
				</th>
			</tr>		
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
</body>
</html>