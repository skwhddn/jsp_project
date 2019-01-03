package project.prersistence;

import java.util.ArrayList;

import project.vo.BoardVO;
import project.vo.cartlistVO;
import project.vo.orderVO;
import project.vo.orderokVO;
import project.vo.projectVO;
import project.vo.stockVO;

public interface PTDAO {
	// 중복확인 체크
	public int idCheck(String strid);
	
	// 회원가입 처리
	public int insertMember(projectVO vo);
	
	// 로그인 처리(아이디 비밀번호 체크 -> 회원수정,회원탈퇴)
	public int idpwdcheck(String strId, String strPwd);

	//회원탈퇴 처리
	public int deleteMember(String strid);
	
	//회원정보 상세 페이지
	public projectVO getMemberInfo(String strid);
	
	//회원정보 수정 페이지
	public int updateMember(projectVO vo);
	
	//글 갯수 구하기
	public int getArticleCnt();
	
	//게시글 목록 조회 (큰바구니 ArrayList에 담겟다)
	public ArrayList<BoardVO> getArticleList(int start, int end);

	//조회수 증가
	public void addReadCnt(int num);
		
	//상세페이지
	public BoardVO getArticle(int num);
	
	//비밀번호 확인(게시글 수정, 게시글 삭제)
	public int pwdCheck(String strPwd, int num);
	
	//글수정 처리페이지
	public int updateBoard(BoardVO vo);
	
	//글작성 / 답글 처리 페이지
	public int insertBoard(BoardVO vo);
	
	//글삭제
	public int deleteBoard(int num);

	//상의 리스트
	public ArrayList<stockVO> getTopList(int start, int end);
	
	//하의 리스트
	public ArrayList<stockVO> getPantsList(int start, int end);
	
	//신발 리스트
	public ArrayList<stockVO> getShoseList(int start, int end);
	
	//상품 상세페이지
	public stockVO detailpage(int num);
	
	//장바구니
	public int cart(cartlistVO vo);
	
	//장바구니 목록
	public ArrayList<cartlistVO> getcartlist(String strid);
	
	//장바구니 삭제
	public int cartdelete(String[] num);
	
	//장바구니 구매
	public int order(String[] num, orderVO vo);
	
	//주문목록
	public ArrayList<orderVO> getorderlist(String strid);
	
	//주문완료목록
	public ArrayList<orderokVO> orderoklist(int start, int end);
	
	//주문삭제
	public int orderno(String[] num);
	
	//환불요청목록
	public int ordercancel(String[] num, orderokVO vo);
	
	//
	public int cartgo(orderVO vo);
	
}
