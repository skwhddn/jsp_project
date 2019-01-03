package project.prersistence;

import java.util.ArrayList;


import project.vo.*;

public interface hostDAO {
	// 로그인 처리(아이디 비밀번호 체크 -> 회원수정,회원탈퇴)
	public int idpwdcheck(String strId, String strPwd);
	
	//글 갯수 구하기
	public int getArticleCnt();
	
	// 상품목록
	public ArrayList<stockVO> getArticleList(int start, int end);
	
	//상품추가
	public int stockadd(stockVO dto);
	
	//상품수정
	public int update(stockVO vo);
	
	//상품 수정 페이지
	public stockVO updatefage(String strid);
	
	//상세페이지
	public stockVO getArticle(int num);
	
	//상품 삭제
	public int stockdelete(String[] num);
	
	//주문목록
	public ArrayList<orderVO> getorderlist(int start, int end);
	
	//장바구니 구매
	public int guestorderadd(String[] num, orderokVO vo);
	
	//환불목록
	public ArrayList<ordernoVO> getcancellist(int start, int end);
	
	//환불처리
	public int ordercancel(String[] num);
	
	//결산
	public int getTotalSale();
	public Object getFirstChat();
}
