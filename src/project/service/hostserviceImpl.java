package project.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import project.prersistence.PTDAOImpl;
import project.prersistence.hostDAO;
import project.prersistence.hostDAOImpl;
import project.vo.orderVO;
import project.vo.ordernoVO;
import project.vo.orderokVO;
import project.vo.stockVO;

public class hostserviceImpl implements hostservice {
	// 로그인
	@Override
	public void loginPro(HttpServletRequest req, HttpServletResponse res) {
		String strId = req.getParameter("id");
		String strPwd = req.getParameter("pwd");

		hostDAO dao = hostDAOImpl.getInstance();

		int selectCnt = dao.idpwdcheck(strId, strPwd);

		if (selectCnt == 1) {

			req.getSession().setAttribute("memid", strId);
		} else {
			req.getSession().setAttribute("memid", null);
		}
		req.setAttribute("cnt", selectCnt);
		System.out.println("strId : " + strId);
		System.out.println("strpwd : " + strPwd);
	}

	@Override
	public void stocklist(HttpServletRequest req, HttpServletResponse res) {

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
		hostDAOImpl dao = hostDAOImpl.getInstance();

		// 5단계. 글갯수 구하기
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
			// 게시글 목록 조회
			ArrayList<stockVO> dtos = dao.getArticleList(start, end);
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
	
	//재고추가
	@Override
		public void stockadd(HttpServletRequest req, HttpServletResponse res) {
			 MultipartRequest mr = null;
			        int maxSize = 10 * 1024 * 1024; // 업로드할 파일의 최대 사이즈(10MB로 설정함)
			        String saveDir = req.getRealPath("/images/top/"); // 업로드할 파일이 위치하게될 실제 경로
			        String realDir = "C:\\DEV\\workspace2\\JSP_PROJECT\\WebContent\\images\\top\\";
			        String encType = "UTF-8";
			        
			        try {
			         mr = new MultipartRequest(req, saveDir, maxSize, encType, new DefaultFileRenamePolicy());
			         
			         FileInputStream fis = new FileInputStream(saveDir + mr.getFilesystemName("kimg"));
			         FileOutputStream fos = new FileOutputStream(realDir + mr.getFilesystemName("kimg"));
			         
			         int data = 0;
			         while((data = fis.read()) != -1) {
			             fos.write(data);
			         }
			         
			         fis.close();
			         fos.close();
			         
			         stockVO dto = new stockVO();
			         dto.setNum(Integer.parseInt(mr.getParameter("ktitle")));
			         dto.setImg(mr.getFilesystemName("kimg"));
			         dto.setSubject(mr.getParameter("subject"));
			         dto.setAge(Integer.parseInt(mr.getParameter("age")));
			         dto.setColor(mr.getParameter("kauthor"));
			         dto.setPrice(mr.getParameter("kpublisher"));
			         dto.setKind(mr.getParameter("kind"));
			         dto.setReg_date(new Timestamp(System.currentTimeMillis()));
			         
			         
			        
			         hostDAOImpl dao = hostDAOImpl.getInstance();
			         int result = dao.stockadd(dto);
			         req.setAttribute("result", result);
			        } catch (Exception e) {
			         e.printStackTrace();
			        }
			 }
	
	//재고 수정상세 페이지
	@Override
	public void stockview(HttpServletRequest req, HttpServletResponse res) {
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		
		hostDAOImpl dao = hostDAOImpl.getInstance();
		
		
		
		stockVO dto = dao.getArticle(num);
		req.setAttribute("dto", dto);


		
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("num", num);
		
		
	}
	
	
	//재고 수정
	@Override
	public void stockupdate(HttpServletRequest req, HttpServletResponse res) {
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		System.out.println("num : " + num);
		System.out.println("pageNum : " + pageNum);
		stockVO vo = new stockVO();
				
		vo.setNum(num);
		vo.setSubject(req.getParameter("subject"));
		vo.setImg(req.getParameter("img"));
		vo.setColor(req.getParameter("color"));
		vo.setAge(Integer.parseInt(req.getParameter("age")));
		vo.setPrice(req.getParameter("price"));
				
				
		hostDAOImpl dao = hostDAOImpl.getInstance();
				
				
		int updateCnt = dao.update(vo);
		
		System.out.println("num : " + num);
		System.out.println("pageNum : " + pageNum);
		System.out.println("updateCnt : " + updateCnt);
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("updateCnt", updateCnt);
		
	}
	
	//재고삭제
	@Override
	public void stockdelete(HttpServletRequest req, HttpServletResponse res) {

		String[] num = req.getParameterValues("num");
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		hostDAOImpl dao = hostDAOImpl.getInstance();
		

		int deleteCnt = dao.stockdelete(num);
		req.setAttribute("deleteCnt", deleteCnt);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("num", num);
		
	}

	@Override
	public void guestorderlist(HttpServletRequest req, HttpServletResponse res) {
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

		
		hostDAOImpl dao = hostDAOImpl.getInstance();

		
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

		
		number = cnt - (currentPage - 1) * pageSize; 

		System.out.println("number : " + number);
		System.out.println("pageSize : " + pageSize);

		if (cnt > 0) {
			
			ArrayList<orderVO> dtos = dao.getorderlist(startPage, endPage);
			req.setAttribute("dtos", dtos);
			System.out.println("dtos : " + dtos);
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
	
	//주문승인
	@Override
	public void guestorderadd(HttpServletRequest req, HttpServletResponse res) {
		String[] num = req.getParameterValues("num");
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int age = Integer.parseInt(req.getParameter("age"));
		String color = req.getParameter("color");
		String img = req.getParameter("img");
		String price = req.getParameter("price");
		String subject = req.getParameter("subject");
		String cnum="";
		
		hostDAOImpl dao = hostDAOImpl.getInstance();
		
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
		vo.setAge(age);
		vo.setColor(color);
		vo.setImg(img);
		vo.setPrice(price);
		vo.setSubject(subject);
		
		int insertCnt = dao.guestorderadd(num, vo);
		
		
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("insertCnt", insertCnt);
	}

	@Override
	public void guestcancellist(HttpServletRequest req, HttpServletResponse res) {
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

		
		hostDAOImpl dao = hostDAOImpl.getInstance();

		
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

		
		number = cnt - (currentPage - 1) * pageSize; 

		System.out.println("number : " + number);
		System.out.println("pageSize : " + pageSize);

		if (cnt > 0) {
			
			ArrayList<ordernoVO> dtos = dao.getcancellist(startPage, endPage);
			req.setAttribute("dtos", dtos);
			System.out.println("dtos : " + dtos);
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

	@Override
	public void guestcancel(HttpServletRequest req, HttpServletResponse res) {
		String[] num = req.getParameterValues("num");
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		hostDAOImpl dao = hostDAOImpl.getInstance();
		System.out.println("num : " + num);
		System.out.println("pageNum : " + pageNum);
		

		int deleteCnt = dao.ordercancel(num);
		
		System.out.println("deleteCnt : " + deleteCnt);
		req.setAttribute("deleteCnt", deleteCnt);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("num", num);
		
	}
	
	//계산합계
	@Override  @SuppressWarnings("unchecked")
	public void sum(HttpServletRequest req, HttpServletResponse res) {
		
		hostDAO dao = hostDAOImpl.getInstance();
		
		int totalSale = dao.getTotalSale(); //충 판매액을 가져옴
		req.setAttribute("totalSale", totalSale);
		System.out.println("totalSale" + totalSale);
		Map<String, Integer> map = (Map<String,Integer>)dao.getFirstChat(); //종류별 구매수
		req.setAttribute("firstChat", map);
			
	}
	
}