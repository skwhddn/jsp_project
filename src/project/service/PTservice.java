package project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PTservice {
	//중복확인
	public void confirmId(HttpServletRequest req, HttpServletResponse res);
	
	//회원가입
	public void userjoin(HttpServletRequest req, HttpServletResponse res);
	
	//로그인
	public void loginPro(HttpServletRequest req, HttpServletResponse res);
	
	//회원정보 수정 상세 페이지
	public void modifyView(HttpServletRequest req, HttpServletResponse res);
	
	//회원정보 수정 처리
	public void guestPro(HttpServletRequest req, HttpServletResponse res);
	
	//회원탈퇴
	public void deletePro(HttpServletRequest req, HttpServletResponse res);

	// 글목록
	public void boardList(HttpServletRequest req, HttpServletResponse res);
	
	// 상세페이지
	public void contentForm(HttpServletRequest req, HttpServletResponse res);
	
	// 글 수정 상세페이지
	public void modify(HttpServletRequest req, HttpServletResponse res);
	
	// 글 수정 처리페이지
	public void modifyPro(HttpServletRequest req, HttpServletResponse res);
	
	//글작성 /답글 처리 페이지
	public void writePro(HttpServletRequest req, HttpServletResponse res);
	
	//글삭제
	public void delete(HttpServletRequest req, HttpServletResponse res);
	
	//상의리스트
	public void toplist(HttpServletRequest req, HttpServletResponse res);
	
	//하의리스트
	public void pantslist(HttpServletRequest req, HttpServletResponse res);
	
	//신발리스트
	public void shoselist(HttpServletRequest req, HttpServletResponse res);
	
	//제품 상세 페이지 
	public void detailpage(HttpServletRequest req, HttpServletResponse res);
	
	//장바구니 담기
	public void cartadd(HttpServletRequest req, HttpServletResponse res);
	
	//장바구니목록
	public void cartlist(HttpServletRequest req, HttpServletResponse res);
	
	//장바구니삭제
	public void cartdelete(HttpServletRequest req, HttpServletResponse res);
	
	//주문목록담기
	public void order(HttpServletRequest req, HttpServletResponse res);
	
	//주문목록뿌리기
	public void orderlist(HttpServletRequest req, HttpServletResponse res);
	
	//주문취소
	public void orderdelete(HttpServletRequest req, HttpServletResponse res);
	
	//환불신청
	public void ordercancel(HttpServletRequest req, HttpServletResponse res);
	
	//즉시구매
	public void cartgo(HttpServletRequest req, HttpServletResponse res);

}
