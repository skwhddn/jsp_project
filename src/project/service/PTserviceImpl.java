package project.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.prersistence.PTDAO;
import project.prersistence.PTDAOImpl;
import project.prersistence.hostDAOImpl;
import project.vo.BoardVO;
import project.vo.cartlistVO;
import project.vo.orderVO;
import project.vo.orderokVO;
import project.vo.projectVO;
import project.vo.stockVO;

public class PTserviceImpl implements PTservice {
	// 중복확인
	@Override
	public void confirmId(HttpServletRequest req, HttpServletResponse res) {

		String strid = req.getParameter("id");

		PTDAO dao = PTDAOImpl.getInstance();

		int selectCnt = dao.idCheck(strid);

		System.out.println("selectCnt:" + selectCnt);

		req.setAttribute("selectCnt", selectCnt);
		req.setAttribute("id", strid);

	}

	// 회원가입
	@Override
	public void userjoin(HttpServletRequest req, HttpServletResponse res) {
		projectVO vo = new projectVO();

		vo.setId(req.getParameter("id"));
		vo.setPwd(req.getParameter("pwd"));
		vo.setName(req.getParameter("name"));

		String address = "";
		String postnm = req.getParameter("postnum");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		address = postnm + addr1 + addr2;
		vo.setAddress(address);

		String hp = "";
		String hp1 = req.getParameter("hp1");
		String hp2 = req.getParameter("hp2");
		String hp3 = req.getParameter("hp3");

		if (!hp1.equals("") && !hp2.equals("") && !hp3.equals("")) {
			hp = hp1 + "-" + hp2 + "-" + hp3;
		}
		vo.setHp(hp);

		String email = "";
		String email1 = req.getParameter("email1");
		String email2 = req.getParameter("email2");
		email = email1 + "@" + email2;
		vo.setEmail(email);

		vo.setReg_date(new Timestamp(System.currentTimeMillis()));

		PTDAO dao = PTDAOImpl.getInstance();

		int insertCnt = dao.insertMember(vo);

		req.setAttribute("insertCnt", insertCnt);
		System.out.println("insertCnt" + insertCnt);
	}

	// 로그인
		@Override
		public void loginPro(HttpServletRequest req, HttpServletResponse res) {
			String strId = req.getParameter("id");
			String strPwd = req.getParameter("pwd");

			PTDAO dao = PTDAOImpl.getInstance();

			int selectCnt = dao.idpwdcheck(strId, strPwd);

			if (selectCnt == 1) {

				req.getSession().setAttribute("memid", strId);
			} else {
				req.getSession().setAttribute("memid", null);
			}
			req.setAttribute("cnt", selectCnt);
			System.out.println("cnt : " + selectCnt);
		}
	
	// 회원탈퇴 처리
	@Override
	public void deletePro(HttpServletRequest req, HttpServletResponse res) {

		String strId = (String) req.getSession().getAttribute("memid");
		String strPwd = req.getParameter("pwd");
		System.out.println("strid : " + strId);
		System.out.println("strpwd : " + strPwd);
		PTDAO dao = PTDAOImpl.getInstance();

		int selectCnt = dao.idpwdcheck(strId, strPwd);
		System.out.println("selectCnt : " + selectCnt);

		int deleteCnt = 0;
		if(selectCnt == 1) {
			deleteCnt = dao.deleteMember(strId);
		}
		System.out.println("deleteCnt : " + deleteCnt);
		req.setAttribute("deleteCnt", deleteCnt);
		req.setAttribute("selectCnt", selectCnt);
	}
	//회원정보
	@Override
		public void modifyView(HttpServletRequest req, HttpServletResponse res) {
			String strid = (String)req.getSession().getAttribute("memid");
			String strpwd = req.getParameter("pwd");
			PTDAO dao = PTDAOImpl.getInstance();
			int selectCnt = dao.idpwdcheck(strid, strpwd);
			
			if(selectCnt == 1) {
				projectVO vo = dao.getMemberInfo(strid);
				req.setAttribute("vo", vo);
			}
			req.setAttribute("selectCnt", selectCnt);
		}
	//회원수정처리
	@Override
	public void guestPro(HttpServletRequest req, HttpServletResponse res) {
		
		projectVO vo = new projectVO();
		
		vo.setId((String)req.getSession().getAttribute("memid"));
		vo.setPwd(req.getParameter("pwd"));
		String hp="";
		String hp1 = req.getParameter("hp1");
		String hp2 = req.getParameter("hp2");
		String hp3 = req.getParameter("hp3");
		
		if (!hp1.equals("") && !hp2.equals("") && !hp3.equals("")) {
			hp = hp1 + "-" + hp2 + "-" + hp3;
		}
		vo.setHp(hp);
		String email = "";
		String email1 = req.getParameter("email1");
		String email2 = req.getParameter("email2");
		email = email1 + "@" + email2;
		vo.setEmail(email);
		
		
		PTDAO dao = PTDAOImpl.getInstance();
		

		
		int updateCnt = dao.updateMember(vo);
		
		req.setAttribute("updateCnt", updateCnt);
	}
	
	//게시글 목록
	@Override
	public void boardList(HttpServletRequest req, HttpServletResponse res) {
		//3단계. 화면으로 부터 입력받은 값을 받아온다.
		//게시판 관련
		int pageSize = 5; 		//한 페이지당 출력할 글 갯수
		int pageBlock = 3; 		//한 블럭당 페이지 갯수
		
		int cnt = 0; 			// 글갯수
		int start = 0; 			// 현재페이지 시작 글번호
		int end = 0; 			// 현재 페이지 마지막 글번호
		int number = 0; 		// 출력용 글번호
		String pageNum = null; 	// 페이지 번호
		int currentPage = 0;   	// 현재 페이지
		
		int pageCount = 0;		// 페이지 갯수
		int	startPage = 0;		// 시작 페이지
		int endPage = 0;		// 마지막 페이지
		
		//4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		PTDAOImpl dao = PTDAOImpl.getInstance();
		
		//5단계. 글갯수 구하기
		cnt = dao.getArticleCnt();
		System.out.println("cnt : " + cnt); // 테이블에 30건을 insert 할것
		
		pageNum = req.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1"; // 첫페이지를 1페이지로 설정
		}
		// 글 30건 기준
		currentPage = Integer.parseInt(pageNum); //현재 페이지: 1
		System.out.println("currentPage" + currentPage);
		
		// 페이지 갯수 6 = (30/5) + (0)
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0); //페이지 갯수 + 나머지있으면 1
		
		// 1 = (1 - 1) * 5 + 1
		start = (currentPage - 1) * pageSize + 1; // 현재 페이지 글번호
		
		// 5 = 1 + 5 - 1 ;
		end = start + pageSize -1; // 현재 페이지 끝번호
		
		System.out.println("start : " + start);
		System.out.println("end : " + end);
		
		if(end > cnt) end = cnt;
		
		//30 = 30 - (
		number = cnt - (currentPage - 1) * pageSize; // 출력용 글번호
		
		System.out.println("number : " + number);
		System.out.println("pageSize : " + pageSize);
		
		if(cnt > 0) {
			// 게시글 목록 조회
			ArrayList<BoardVO> dtos = dao.getArticleList(start, end);
			req.setAttribute("dtos", dtos);
		}
		
		//현재 1페이지 = (1/3) * 3 + 1
		startPage =(currentPage / pageBlock) * pageBlock + 1; // 시작페이지
		if(currentPage % pageBlock == 0) startPage -= pageBlock; // 나머지 계산
		System.out.println("startPage : " + startPage);
		
		//끝페이지 3 = 1 + 3 - 1
		endPage = startPage + pageBlock -1; // 마지막페이지
		if(endPage > pageCount) endPage = pageCount;
		System.out.println("endPage : " + endPage);
		
		req.setAttribute("cnt", cnt); //글갯수
		req.setAttribute("number", number); // 글번호
		req.setAttribute("pageNum", pageNum); // 페이지 번호
		
		if(cnt > 0) {
			req.setAttribute("startPage", startPage); // 시작 페이지
			req.setAttribute("endPage", endPage);	// 마지막페이지
			req.setAttribute("pageBlock", pageBlock); // 출력할 페이지 갯수
			req.setAttribute("pageCount", pageCount); // 페이지 갯수
			req.setAttribute("currentPage", currentPage); // 현재 페이지
		}

		//6단계. request나 session에 처리 결과를 저장(jsp에 전달하기 위함)
		req.setAttribute("cnt", cnt);
	}

	//상세페이지
	@Override
	public void contentForm(HttpServletRequest req, HttpServletResponse res) {
		
		//3단계. 화면으로부터 입렵받은 값을 받아온다.
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int number = Integer.parseInt(req.getParameter("number"));
		
		//4단계. 다형성 적용, 싱글톤 방식 dao 객체 생성
		PTDAOImpl dao = PTDAOImpl.getInstance();
		
		//5단계. 조회수 증가
		dao.addReadCnt(num);
		
		//5단계. 상세페이지 조회
		BoardVO dto = dao.getArticle(num);
		

		
		//6단계. request나 session에 처리 겨로가를 저장(jsp에 전달하기 위함)

		req.setAttribute("dto", dto);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("number", number);
		
	}

	//글 수정상세페이지
	@Override
	public void modify(HttpServletRequest req, HttpServletResponse res) {
		//3단계 화면으로 부터 입력받은 값 받아온다.
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		String strPwd = req.getParameter("pwd");
		//4단계 다형성 적용 싱글톤 방식으로 dao 객체 생성
		System.out.println("strpwd : " + strPwd);
		System.out.println("num : " + num);
		PTDAOImpl dao = PTDAOImpl.getInstance();
		//5단계 
		int selectCnt = dao.pwdCheck(strPwd, num);
		System.out.println("selectCnt : " + selectCnt);
		//5-2단계 selectCnt가 1일때 num과 일치하는 게시글 1건을 읽어온다.
		if(selectCnt == 1) {
			BoardVO dto = dao.getArticle(num);
			req.setAttribute("dto", dto);
		}
		//6단계 request 나 session에 처리 결과를 저장
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("num", num);
		req.setAttribute("selectCnt", selectCnt);
		
	}

	//글수정 처리페이지
	@Override
	public void modifyPro(HttpServletRequest req, HttpServletResponse res) {
		//3단계 화면으로 부터 입력받은 값 받아온다.
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		//바구니생성
		BoardVO vo = new BoardVO();
		//화면에서 입력한 값(제목,내용,비밀번호)들을 바구니에 답는다.
		vo.setNum(num);
		vo.setSubject(req.getParameter("subject"));
		vo.setContent(req.getParameter("content"));
		vo.setPwd(req.getParameter("pwd"));
		
		//4단계 다형성 적용 싱글톤 방식으로 dao 객체 생성
		PTDAOImpl dao = PTDAOImpl.getInstance();
		
		//5단계 글수정처리
		int updateCnt = dao.updateBoard(vo);
		
		//6단계 request 나 session에 처리 결과를 저장
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("updateCnt", updateCnt);
	}
	
	//글작성 /답글 처리 페이지
	@Override
	public void writePro(HttpServletRequest req, HttpServletResponse res) {

		BoardVO vo = new BoardVO();
		

		vo.setNum(Integer.parseInt(req.getParameter("num")));
		vo.setRef(Integer.parseInt(req.getParameter("ref")));
		vo.setRef_step(Integer.parseInt(req.getParameter("ref_step")));
		vo.setRef_level(Integer.parseInt(req.getParameter("ref_level")));		
		
		

		vo.setM_id(req.getParameter("m_id"));
		vo.setPwd(req.getParameter("pwd"));
		vo.setSubject(req.getParameter("subject"));
		vo.setContent(req.getParameter("content"));
		vo.setReg_date(new Timestamp(System.currentTimeMillis()));
		

		vo.setIp(req.getRemoteAddr());
		

		PTDAOImpl dao = PTDAOImpl.getInstance();
		

		int insertCnt = dao.insertBoard(vo);
		

		req.setAttribute("insertCnt", insertCnt);
	}
	
	
	//글삭제
	@Override
	public void delete(HttpServletRequest req, HttpServletResponse res) {
		//3단계 화면으로 부터 입력받은 값 받아온다.
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		String strPwd = req.getParameter("pwd");
		//4단계 다형성 적용 싱글톤 방식으로 dao 객체 생성
		PTDAOImpl dao = PTDAOImpl.getInstance();
		
		//5-1단계. 비밀번호 체크
		int selectCnt = dao.pwdCheck(strPwd, num);
		//5-2단계. 글삭제 처리 페이지
		if(selectCnt == 1) {
			int deleteCnt = dao.deleteBoard(num);
			req.setAttribute("deleteCnt", deleteCnt);
		}
		
		//6단계 request 나 session에 처리 결과를 저장
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("selectCnt", selectCnt);
		
	}

	@Override
	public void toplist(HttpServletRequest req, HttpServletResponse res) {
		int pageSize = 5; // 한 페이지당 출력할 글 갯수
		int pageBlock = 3; // 한 블럭당 페이지 갯수

		int cnt = 0; // 상품갯수
		int start = 0; // 현재페이지 시작 글번호
		int end = 0; // 현재 페이지 마지막 글번호
		int number = 0; // 출력용 상품번호
		String pageNum = null; // 페이지 번호
		int currentPage = 0; // 현재 페이지

		int pageCount = 0; // 페이지 갯수
		int startPage = 0; // 시작 페이지
		int endPage = 0; // 마지막 페이지

		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		PTDAOImpl dao = PTDAOImpl.getInstance();

		// 5단계. 상품갯수 구하기
		cnt = dao.getArticleCnt();
		System.out.println("cnt : " + cnt); // 테이블에 30건을 insert 할것

		pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1"; // 첫페이지를 1페이지로 설정
		}
		// 글 30건 기준
		currentPage = Integer.parseInt(pageNum); // 현재 페이지: 1
		System.out.println("currentPage" + currentPage);

		// 페이지 갯수 6 = (30/5) + (0)
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0); // 페이지 갯수 + 나머지있으면 1

		// 1 = (1 - 1) * 5 + 1
		start = (currentPage - 1) * pageSize + 1; // 현재 페이지 글번호

		// 5 = 1 + 5 - 1 ;
		end = start + pageSize - 1; // 현재 페이지 끝번호

		System.out.println("start : " + start);
		System.out.println("end : " + end);

		if (end > cnt)
			end = cnt;

		// 30 = 30 - (
		number = cnt - (currentPage - 1) * pageSize; // 출력용 글번호

		System.out.println("number : " + number);
		System.out.println("pageSize : " + pageSize);

		if (cnt > 0) {
			// 상의 목록 조회
			ArrayList<stockVO> dtos = dao.getTopList(startPage, endPage);
			req.setAttribute("dtos", dtos);
		}

		// 현재 1페이지 = (1/3) * 3 + 1
		startPage = (currentPage / pageBlock) * pageBlock + 1; // 시작페이지
		if (currentPage % pageBlock == 0)
			startPage -= pageBlock; // 나머지 계산
		System.out.println("startPage : " + startPage);

		// 끝페이지 3 = 1 + 3 - 1
		endPage = startPage + pageBlock - 1; // 마지막페이지
		if (endPage > pageCount)
			endPage = pageCount;
		System.out.println("endPage : " + endPage);

		req.setAttribute("cnt", cnt); // 글갯수
		req.setAttribute("number", number); // 글번호
		req.setAttribute("pageNum", pageNum); // 페이지 번호

		if (cnt > 0) {
			req.setAttribute("startPage", startPage); // 시작 페이지
			req.setAttribute("endPage", endPage); // 마지막페이지
			req.setAttribute("pageBlock", pageBlock); // 출력할 페이지 갯수
			req.setAttribute("pageCount", pageCount); // 페이지 갯수
			req.setAttribute("currentPage", currentPage); // 현재 페이지
		}

		// 6단계. request나 session에 처리 결과를 저장(jsp에 전달하기 위함)
		req.setAttribute("cnt", cnt);
	}


	@Override
	public void pantslist(HttpServletRequest req, HttpServletResponse res) {
		int pageSize = 5; // 한 페이지당 출력할 글 갯수
		int pageBlock = 3; // 한 블럭당 페이지 갯수

		int cnt = 0; // 상품갯수
		int start = 0; // 현재페이지 시작 글번호
		int end = 0; // 현재 페이지 마지막 글번호
		int number = 0; // 출력용 상품번호
		String pageNum = null; // 페이지 번호
		int currentPage = 0; // 현재 페이지

		int pageCount = 0; // 페이지 갯수
		int startPage = 0; // 시작 페이지
		int endPage = 0; // 마지막 페이지

		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		PTDAOImpl dao = PTDAOImpl.getInstance();

		// 5단계. 상품갯수 구하기
		cnt = dao.getArticleCnt();
		System.out.println("cnt : " + cnt); // 테이블에 30건을 insert 할것

		pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1"; // 첫페이지를 1페이지로 설정
		}
		// 글 30건 기준
		currentPage = Integer.parseInt(pageNum); // 현재 페이지: 1
		System.out.println("currentPage" + currentPage);

		// 페이지 갯수 6 = (30/5) + (0)
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0); // 페이지 갯수 + 나머지있으면 1

		// 1 = (1 - 1) * 5 + 1
		start = (currentPage - 1) * pageSize + 1; // 현재 페이지 글번호

		// 5 = 1 + 5 - 1 ;
		end = start + pageSize - 1; // 현재 페이지 끝번호

		System.out.println("start : " + start);
		System.out.println("end : " + end);

		if (end > cnt)
			end = cnt;

		// 30 = 30 - (
		number = cnt - (currentPage - 1) * pageSize; // 출력용 글번호

		System.out.println("number : " + number);
		System.out.println("pageSize : " + pageSize);

		if (cnt > 0) {
			// 상의 목록 조회
			ArrayList<stockVO> dtos = dao.getPantsList(startPage, endPage);
			req.setAttribute("dtos", dtos);
		}

		// 현재 1페이지 = (1/3) * 3 + 1
		startPage = (currentPage / pageBlock) * pageBlock + 1; // 시작페이지
		if (currentPage % pageBlock == 0)
			startPage -= pageBlock; // 나머지 계산
		System.out.println("startPage : " + startPage);

		// 끝페이지 3 = 1 + 3 - 1
		endPage = startPage + pageBlock - 1; // 마지막페이지
		if (endPage > pageCount)
			endPage = pageCount;
		System.out.println("endPage : " + endPage);

		req.setAttribute("cnt", cnt); // 글갯수
		req.setAttribute("number", number); // 글번호
		req.setAttribute("pageNum", pageNum); // 페이지 번호

		if (cnt > 0) {
			req.setAttribute("startPage", startPage); // 시작 페이지
			req.setAttribute("endPage", endPage); // 마지막페이지
			req.setAttribute("pageBlock", pageBlock); // 출력할 페이지 갯수
			req.setAttribute("pageCount", pageCount); // 페이지 갯수
			req.setAttribute("currentPage", currentPage); // 현재 페이지
		}

		// 6단계. request나 session에 처리 결과를 저장(jsp에 전달하기 위함)
		req.setAttribute("cnt", cnt);
		
	}

	@Override
	public void shoselist(HttpServletRequest req, HttpServletResponse res) {
		int pageSize = 5; // 한 페이지당 출력할 글 갯수
		int pageBlock = 3; // 한 블럭당 페이지 갯수

		int cnt = 0; // 상품갯수
		int start = 0; // 현재페이지 시작 글번호
		int end = 0; // 현재 페이지 마지막 글번호
		int number = 0; // 출력용 상품번호
		String pageNum = null; // 페이지 번호
		int currentPage = 0; // 현재 페이지

		int pageCount = 0; // 페이지 갯수
		int startPage = 0; // 시작 페이지
		int endPage = 0; // 마지막 페이지

		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		PTDAOImpl dao = PTDAOImpl.getInstance();

		// 5단계. 상품갯수 구하기
		cnt = dao.getArticleCnt();
		System.out.println("cnt : " + cnt); // 테이블에 30건을 insert 할것

		pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1"; // 첫페이지를 1페이지로 설정
		}
		// 글 30건 기준
		currentPage = Integer.parseInt(pageNum); // 현재 페이지: 1
		System.out.println("currentPage" + currentPage);

		// 페이지 갯수 6 = (30/5) + (0)
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0); // 페이지 갯수 + 나머지있으면 1

		// 1 = (1 - 1) * 5 + 1
		start = (currentPage - 1) * pageSize + 1; // 현재 페이지 글번호

		// 5 = 1 + 5 - 1 ;
		end = start + pageSize - 1; // 현재 페이지 끝번호

		System.out.println("start : " + start);
		System.out.println("end : " + end);

		if (end > cnt)
			end = cnt;

		// 30 = 30 - (
		number = cnt - (currentPage - 1) * pageSize; // 출력용 글번호

		System.out.println("number : " + number);
		System.out.println("pageSize : " + pageSize);

		if (cnt > 0) {
			// 상의 목록 조회
			ArrayList<stockVO> dtos = dao.getShoseList(startPage, endPage);
			req.setAttribute("dtos", dtos);
			System.out.println("dtos" + dtos);
		}

		// 현재 1페이지 = (1/3) * 3 + 1
		startPage = (currentPage / pageBlock) * pageBlock + 1; // 시작페이지
		if (currentPage % pageBlock == 0)
			startPage -= pageBlock; // 나머지 계산
		System.out.println("startPage : " + startPage);

		// 끝페이지 3 = 1 + 3 - 1
		endPage = startPage + pageBlock - 1; // 마지막페이지
		if (endPage > pageCount)
			endPage = pageCount;
		System.out.println("endPage : " + endPage);

		req.setAttribute("cnt", cnt); // 글갯수
		req.setAttribute("number", number); // 글번호
		req.setAttribute("pageNum", pageNum); // 페이지 번호

		if (cnt > 0) {
			req.setAttribute("startPage", startPage); // 시작 페이지
			req.setAttribute("endPage", endPage); // 마지막페이지
			req.setAttribute("pageBlock", pageBlock); // 출력할 페이지 갯수
			req.setAttribute("pageCount", pageCount); // 페이지 갯수
			req.setAttribute("currentPage", currentPage); // 현재 페이지
		}

		// 6단계. request나 session에 처리 결과를 저장(jsp에 전달하기 위함)
		req.setAttribute("cnt", cnt);
		
	}

	@Override
	public void detailpage(HttpServletRequest req, HttpServletResponse res) {
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		

		PTDAOImpl dao = PTDAOImpl.getInstance();

		stockVO dto = dao.detailpage(num);
		
		req.setAttribute("dto", dto);
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		
	}

	@Override
	public void cartadd(HttpServletRequest req, HttpServletResponse res) {
		String strId = (String) req.getSession().getAttribute("memid");
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int number = Integer.parseInt(req.getParameter("number"));
		
		System.out.println("number : " + number);
		cartlistVO vo = new cartlistVO();
		
		vo.setM_id(strId);
		vo.setNum(num);
		vo.setC_age(Integer.parseInt(req.getParameter("number")));
		vo.setC_color(req.getParameter("color"));
		vo.setC_price(req.getParameter("price"));
		vo.setC_img(req.getParameter("img"));
		vo.setC_subject(req.getParameter("subject"));
		
		PTDAOImpl dao = PTDAOImpl.getInstance();
		
		int insertCnt = dao.cart(vo);
		
		System.out.println("num : " + num);
		System.out.println("strid : " + strId);
		System.out.println("pageNum : " + pageNum);
		System.out.println("insertCnt : " + insertCnt);
		
		//6단계 request 나 session에 처리 결과를 저장
		req.getSession().setAttribute("memid", strId);
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("insertCnt", insertCnt);
		
	}

	@Override
	public void cartlist(HttpServletRequest req, HttpServletResponse res) {
		String strid = (String)req.getSession().getAttribute("memid");
		int pageSize = 5; // 한 페이지당 출력할 글 갯수
		int pageBlock = 3; // 한 블럭당 페이지 갯수

		int cnt = 0; // 상품갯수
		int start = 0; // 현재페이지 시작 글번호
		int end = 0; // 현재 페이지 마지막 글번호
		int number = 0; // 출력용 상품번호
		String pageNum = null; // 페이지 번호
		int currentPage = 0; // 현재 페이지

		int pageCount = 0; // 페이지 갯수
		int startPage = 0; // 시작 페이지
		int endPage = 0; // 마지막 페이지

		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		PTDAOImpl dao = PTDAOImpl.getInstance();

		// 5단계. 상품갯수 구하기
		cnt = dao.getArticleCnt();
		System.out.println("cnt : " + cnt); // 테이블에 30건을 insert 할것

		pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1"; // 첫페이지를 1페이지로 설정
		}
		// 글 30건 기준
		currentPage = Integer.parseInt(pageNum); // 현재 페이지: 1
		System.out.println("currentPage" + currentPage);

		// 페이지 갯수 6 = (30/5) + (0)
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0); // 페이지 갯수 + 나머지있으면 1

		// 1 = (1 - 1) * 5 + 1
		start = (currentPage - 1) * pageSize + 1; // 현재 페이지 글번호

		// 5 = 1 + 5 - 1 ;
		end = start + pageSize - 1; // 현재 페이지 끝번호

		System.out.println("start : " + start);
		System.out.println("end : " + end);

		if (end > cnt)
			end = cnt;

		// 30 = 30 - (
		number = cnt - (currentPage - 1) * pageSize; // 출력용 글번호

		System.out.println("number : " + number);
		System.out.println("pageSize : " + pageSize);

		if (cnt > 0) {
			// 장바구니 목록 조회
			ArrayList<cartlistVO> dtos = dao.getcartlist(strid);
			req.setAttribute("dtos", dtos);
			System.out.println("dtos : " + dtos);
		}
		
		// 현재 1페이지 = (1/3) * 3 + 1
		startPage = (currentPage / pageBlock) * pageBlock + 1; // 시작페이지
		if (currentPage % pageBlock == 0)
			startPage -= pageBlock; // 나머지 계산
		System.out.println("startPage : " + startPage);

		// 끝페이지 3 = 1 + 3 - 1
		endPage = startPage + pageBlock - 1; // 마지막페이지
		if (endPage > pageCount)
			endPage = pageCount;
		System.out.println("endPage : " + endPage);

		req.setAttribute("cnt", cnt); // 글갯수
		req.setAttribute("number", number); // 글번호
		req.setAttribute("pageNum", pageNum); // 페이지 번호

		if (cnt > 0) {
			req.setAttribute("pageBlock", pageBlock);
			req.setAttribute("pageCount", pageCount);
			req.setAttribute("currentPage", currentPage); 
		}

		
		req.setAttribute("cnt", cnt);
}

	@Override
	public void cartdelete(HttpServletRequest req, HttpServletResponse res) {
		String[] num = req.getParameterValues("num");
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		PTDAOImpl dao = PTDAOImpl.getInstance();
		
		

		int deleteCnt = dao.cartdelete(num);
		System.out.println("num : " + num);
		System.out.println("deleteCnt : " + deleteCnt);
		
		req.setAttribute("deleteCnt", deleteCnt);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("num", num);
		
	}

	@Override
	public void order(HttpServletRequest req, HttpServletResponse res) {
		String strid = (String) req.getSession().getAttribute("memid");
		String[] num = req.getParameterValues("num");
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int age = Integer.parseInt(req.getParameter("age"));
		String color = req.getParameter("color");
		String img = req.getParameter("img");
		String price = req.getParameter("price");
		String subject = req.getParameter("subject");
		String cnum="";
		
		PTDAOImpl dao = PTDAOImpl.getInstance();
		
		System.out.println("strid : " + strid);
		System.out.println("num : " + num);
		System.out.println("pageNum : " + pageNum);
		System.out.println("age : " + age);
		System.out.println("color : " + color);
		System.out.println("img : " + img);
		System.out.println("price : " + price);
		System.out.println("subject : " + subject);
		
		for(int i=0;i<num.length;i++) {
			cnum+=num[i]+",";
		}
		
		orderVO vo = new orderVO();
		vo.setTest(cnum);
		vo.setM_id(strid);
		vo.setAge(age);
		vo.setColor(color);
		vo.setImg(img);
		vo.setPrice(price);
		vo.setSubject(subject);
		
		int insertCnt = dao.order(num, vo);
		
		System.out.println("insertCnt : " + insertCnt);
		
		
		req.getSession().setAttribute("memid", strid);
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("insertCnt", insertCnt);
		
	}

	@Override
	public void orderlist(HttpServletRequest req, HttpServletResponse res) {
		String strid = (String)req.getSession().getAttribute("memid");
		int pageSize = 5;
		int pageBlock = 3; 

		int cnt = 0; 
		int start = 0; 
		int end = 0; 
		int number = 0; 
		String pageNum = null; 
		int currentPage = 0;

		int pageCount = 0; 
		int startPage = 0;
		int endPage = 0;

		
		PTDAOImpl dao = PTDAOImpl.getInstance();

	
		cnt = dao.getArticleCnt();
		System.out.println("cnt : " + cnt); 

		pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		
		currentPage = Integer.parseInt(pageNum); 
		System.out.println("currentPage" + currentPage);

		
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0); 

		
		start = (currentPage - 1) * pageSize + 1; 

		
		end = start + pageSize - 1; 

		System.out.println("start : " + start);
		System.out.println("end : " + end);

		if (end > cnt)
			end = cnt;

		
		number = cnt - (currentPage - 1) * pageSize; // 

		System.out.println("number : " + number);
		System.out.println("pageSize : " + pageSize);

		if (cnt > 0) {
			
			ArrayList<orderVO> dtos = dao.getorderlist(strid);
			req.setAttribute("dtos", dtos);
			System.out.println("dtos : " + dtos);
			
			ArrayList<orderokVO> vo = dao.orderoklist(startPage, endPage);
			req.setAttribute("vo", vo);
			System.out.println("vo : " + vo);
		}
		
		
		startPage = (currentPage / pageBlock) * pageBlock + 1; 
		if (currentPage % pageBlock == 0)
			startPage -= pageBlock; 
		System.out.println("startPage : " + startPage);

		
		endPage = startPage + pageBlock - 1; 
		if (endPage > pageCount)
			endPage = pageCount;
		System.out.println("endPage : " + endPage);

		req.setAttribute("cnt", cnt); 
		req.setAttribute("number", number); 
		req.setAttribute("pageNum", pageNum); 

		if (cnt > 0) {
			req.setAttribute("pageBlock", pageBlock); 
			req.setAttribute("pageCount", pageCount); 
			req.setAttribute("currentPage", currentPage); 
		}

	
		req.setAttribute("cnt", cnt);
	}
	
	//주문취소
	@Override
	public void orderdelete(HttpServletRequest req, HttpServletResponse res) {
		String[] num = req.getParameterValues("num");
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		PTDAOImpl dao = PTDAOImpl.getInstance();

		int deleteCnt = dao.orderno(num);
		System.out.println("num : " + num);
		System.out.println("deleteCnt : " + deleteCnt);
		
		req.setAttribute("deleteCnt", deleteCnt);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("num", num);
		
	}
	
	//환불요청
	@Override
	public void ordercancel(HttpServletRequest req, HttpServletResponse res) {
		
		String strid = (String) req.getSession().getAttribute("memid");
		String[] num = req.getParameterValues("num");
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int age = Integer.parseInt(req.getParameter("age"));
		String color = req.getParameter("color");
		String img = req.getParameter("img");
		String price = req.getParameter("price");
		String subject = req.getParameter("subject");
		String cnum="";
		
		PTDAOImpl dao = PTDAOImpl.getInstance();
		
		System.out.println("strid : " + strid);
		System.out.println("num : " + num);
		System.out.println("pageNum : " + pageNum);
		System.out.println("age : " + age);
		System.out.println("color : " + color);
		System.out.println("img : " + img);
		System.out.println("price : " + price);
		System.out.println("subject : " + subject);
		
		for(int i=0;i<num.length;i++) {
			cnum+=num[i]+",";
		}
		
		orderokVO vo = new orderokVO();
		vo.setTest(cnum);
		vo.setM_id(strid);
		vo.setAge(age);
		vo.setColor(color);
		vo.setImg(img);
		vo.setPrice(price);
		vo.setSubject(subject);
		
		int insertCnt = dao.ordercancel(num, vo);
		
		System.out.println("insertCnt : " + insertCnt);
		
		//6단계 request 나 session에 처리 결과를 저장
		req.getSession().setAttribute("memid", strid);
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("insertCnt", insertCnt);
		
	}

	//즉시구매
	@Override
	public void cartgo(HttpServletRequest req, HttpServletResponse res) {
		String strId = (String) req.getSession().getAttribute("memid");
		int num = Integer.parseInt(req.getParameter("num"));
		System.out.println("num : " + num);
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		System.out.println("pageNum : " + pageNum);
		
		orderVO vo = new orderVO();
		
		vo.setM_id(strId);
		vo.setO_num(num);
		vo.setAge(Integer.parseInt(req.getParameter("number")));
		vo.setColor(req.getParameter("color"));
		vo.setPrice(req.getParameter("price"));
		vo.setImg(req.getParameter("img"));
		vo.setSubject(req.getParameter("subject"));
		
		PTDAOImpl dao = PTDAOImpl.getInstance();
		
		int insertCnt = dao.cartgo(vo);
		System.out.println("insertCnt : " + insertCnt);
		
		
		req.getSession().setAttribute("memid", strId);
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("insertCnt", insertCnt);
	}
	
}