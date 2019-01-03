<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp"%>
<link href="${css}join.css" rel="stylesheet" type="text/css">
<script src="${project}script.js">
</script>
<html>
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
				<li><a href="login.cu">마이페이지</a></li>
				<li><a href="../guest/cart.html">장바구니</a></li>
				<li><a href="../guest/order.html">주문목록</a></li>
				<li><input type='text' class='input_text'>
					<button type='submit' class='sch_smit'>검색</button></li>
			</ul>
		</div>
	</header>
	<br><br><br><br>
	<h2><center> Q&A 게시판 </center></h2>
	<table style="width:1000px" align="center">
		<tr>
			<th colspan="6" align="center" style="height:25px">
			글목록(글갯수 : ${cnt}) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="writeForm.cu"> 글쓰기 </a>
			</th>
		</tr>
		<tr>
			<th style="width:15%"> 글번호 </th>
			<th style="width:25%"> 글제목 </th>
			<th style="width:10%"> 작성자 </th>
			<th style="width:15%"> 작성일 </th>
			<th style="width:5%"> 조회수 </th>
			<th style="width:5%"> IP </th>
		</tr>
		<!-- 게시글이 있으면 -->
		<c:if test="${cnt > 0}">
		<!-- 큰바구니에서 작은 바구니를 꺼내서 아래에서 출력 -->
		<c:forEach var="dto" items="${dtos}">
			<tr>
				<td align="center">
					${number}
					<c:set var="number" value="${number-1}" />
					(${dto.ref} /${dto.ref_step} /${dto.ref_level })
					</td>
				<td>
					<!-- 추가Start --><!-- 답글인 경우 : 들여쓰기 >1 -->
					<c:if test="${dto.ref_level > 1 }"> 
						<c:set var="id" value="${(dto.ref_level -1) * 10}" />
						<img src="${imagess}level.gif" border="0" width="${id}" height="20">
					</c:if>
					
					<!-- 답글인 경우 : 들여쓰기 >0 -->
					<c:if test="${dto.ref_level > 0 }"> 
						<img src="${imagess}re.gif" border="0" width="40" height="20">
					</c:if>
					
					<!-- hot 이미지 -->
					<c:if test="${dto.readCnt > 10 }"> 
						<img src="${imagess}hot.gif" border="0" width="40" height="20">
					</c:if>
					
					<!-- 추가 -->	
				
					<a href="contentForm.cu?num=${dto.num}&pageNum=${pageNum}&number=${number+1}">${dto.subject}</a>	
				</td>
				<td align="center">
					${dto.m_id}
				</td>
				<td>
					<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${dto.reg_date}"/>
				</td>
				<td align="center">
					${dto.readCnt}
				</td>
				<td align="center">
					${dto.ip}
				</td>
			</tr>
		</c:forEach>
		</c:if>
		<!-- 게시글이 없으면 -->
		<c:if test="${cnt == 0}">
			<tr>
				<td colspan="6" align="center">
				게시글이 없습니다. 글을 작성해주세요.
			</td>
		</c:if>
		</table>
		
		<!-- 페이지 컨트롤 -->
		<table style="width:1000px" align="center">
			<tr>
			<th align="center">
				<c:if test="${cnt>0}">
				<!-- 처음 [◀◀ ]/ 이전블록[◀ ] -->
					<c:if test="${startPage > pageBlock}">
						<a href="board.cu">[◀◀ ]</a>
						<a href="board.cu?pageNum=${startPage - pageBlock}">[◀]</a>	
					</c:if>
					
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:if test="${i == currentPage}">
							<span><b>[${i}]</b></span>
						</c:if>
						<c:if test="${i != currentPage}">
							<a href="board.cu?pageNum=${i}">[${i}]</a>
						</c:if>
					</c:forEach>
				<!-- 다음 블록[▶▶] / 끝[▶] -->
					<c:if test="${pageCount > endPage}">
						<a href="board.cu?pageNum=${startPage + pageBlock}">[▶]</a>
						<a href="board.cu?pageNum=${pageCount}">[▶▶]</a>
					</c:if>
				</c:if>
			</th>
			</tr>
		</table>
		<br><br><br><br><br><br><br><br><br><br><br><br>
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
	</c:if>
	<c:if test="${sessionScope.memid != null}">
	<header>
			<div class="gnb">
			<ul>
				<li><span>${sessionScope.memid}</span>님.</li>
				<li><a href="logout.cu">로그아웃</a>
				<li><a href="guestmafage.cu">마이페이지</a></li>
				<li><a href="../guest/cart.html">장바구니</a></li>
				<li><a href="../guest/order.html">주문목록</a></li>
				<li><input type='text' class='input_text'>
					<button type='submit' class='sch_smit'>검색</button></li>
			</ul>
		</div>
	</header>
	<br><br><br><br>
	<h2><center> Q&A 게시판 </center></h2>
	<table style="width:1000px" align="center">
		<tr>
			<th colspan="6" align="center" style="height:25px">
			글목록(글갯수 : ${cnt}) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="writeForm.cu?pageNum=${pageNum}"> 글쓰기 </a>
			</th>
		</tr>
		<tr>
			<th style="width:15%"> 글번호 </th>
			<th style="width:25%"> 글제목 </th>
			<th style="width:10%"> 작성자 </th>
			<th style="width:15%"> 작성일 </th>
			<th style="width:5%"> 조회수 </th>
			<th style="width:5%"> IP </th>
		</tr>
		<!-- 게시글이 있으면 -->
		<c:if test="${cnt > 0}">
		<!-- 큰바구니에서 작은 바구니를 꺼내서 아래에서 출력 -->
		<c:forEach var="dto" items="${dtos}">
			<tr>
				<td align="center">
					${number}
					<c:set var="number" value="${number-1}" />
					(${dto.ref} /${dto.ref_step} /${dto.ref_level })
					</td>
				<td>
					<!-- 추가Start --><!-- 답글인 경우 : 들여쓰기 >1 -->
					<c:if test="${dto.ref_level > 1 }"> 
						<c:set var="wid" value="${(dto.ref_level -1) * 10}" />
						<img src="${imagess}level.gif" border="0" width="${id}" height="15">
					</c:if>
					
					<!-- 답글인 경우 : 들여쓰기 >0 -->
					<c:if test="${dto.ref_level > 0 }"> 
						<img src="${imagess}re.gif" border="0" width="40" height="20">
					</c:if>
					
					<!-- hot 이미지 -->
					<c:if test="${dto.readCnt > 10 }"> 
						<img src="${imagess}hot.gif" border="0" width="40" height="20">
					</c:if>
					
					<!-- 추가 -->	
				
					<a href="contentForm.cu?num=${dto.num}&pageNum=${pageNum}&number=${number+1}">${dto.subject}</a>	
				</td>
				<td align="center">
					${dto.m_id}
				</td>
				<td>
					<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${dto.reg_date}"/>
				</td>
				<td align="center">
					${dto.readCnt}
				</td>
				<td align="center">
					${dto.ip}
				</td>
			</tr>
		</c:forEach>
		</c:if>
		<!-- 게시글이 없으면 -->
		<c:if test="${cnt == 0}">
			<tr>
				<td colspan="6" align="center">
				게시글이 없습니다. 글을 작성해주세요.
			</td>
		</c:if>
		</table>
		
		<!-- 페이지 컨트롤 -->
		<table style="width:1000px" align="center">
			<tr>
			<th align="center">
				<c:if test="${cnt>0}">
				<!-- 처음 [◀◀ ]/ 이전블록[◀ ] -->
					<c:if test="${startPage > pageBlock}">
						<a href="board.cu">[◀◀ ]</a>
						<a href="board.cu?pageNum=${startPage - pageBlock}">[◀]</a>	
					</c:if>
					
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:if test="${i == currentPage}">
							<span><b>[${i}]</b></span>
						</c:if>
						<c:if test="${i != currentPage}">
							<a href="board.cu?pageNum=${i}">[${i}]</a>
						</c:if>
					</c:forEach>
				<!-- 다음 블록[▶▶] / 끝[▶] -->
					<c:if test="${pageCount > endPage}">
						<a href="board.cu?pageNum=${startPage + pageBlock}">[▶]</a>
						<a href="board.cu?pageNum=${pageCount}">[▶▶]</a>
					</c:if>
				</c:if>
			</th>
			</tr>
		</table>
		<br><br><br><br><br><br><br><br><br><br><br><br>
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
	</c:if>
</body>
</html>