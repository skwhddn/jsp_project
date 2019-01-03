package project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface hostservice {
	//로그인
	public void loginPro(HttpServletRequest req, HttpServletResponse res);
	
	//재고목록
	public void stocklist(HttpServletRequest req, HttpServletResponse res);
	
	//재고추가
	public void stockadd(HttpServletRequest req, HttpServletResponse res);
	
	//재고수정
	public void stockupdate(HttpServletRequest req, HttpServletResponse res);
	
	//재고수정 상세페이지
	public void stockview(HttpServletRequest req, HttpServletResponse res);
	
	//재고삭제
	public void stockdelete(HttpServletRequest req, HttpServletResponse res);
	
	//주문목록뿌리기
	public void guestorderlist(HttpServletRequest req, HttpServletResponse res);
	
	//주문승인
	public void guestorderadd(HttpServletRequest req, HttpServletResponse res);
	
	//환불목록뿌리기
	public void guestcancellist(HttpServletRequest req, HttpServletResponse res);
	
	//환불처리
	public void guestcancel(HttpServletRequest req, HttpServletResponse res);
	
	//결산
	public void sum(HttpServletRequest req, HttpServletResponse res);
}	
