package project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.service.PTserviceImpl;

@WebServlet("*.cu")
public class PTController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PTController() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ActionDo(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ActionDo(req, res);
	}

	public void ActionDo(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PTserviceImpl service = new PTserviceImpl();
		req.setCharacterEncoding("UTF-8");

		String viewPage = null;
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String url = uri.substring(contextPath.length());

		// 메인페이지
		if (url.equals("/main.cu") || url.equals("/*.cu")) {
			System.out.println("/main.cu");

			viewPage = "/guest/main.jsp";
			// 중복처리
		} else if (url.equals("/join.cu")) {
			System.out.println("/join.cu");

			viewPage = "/common/guestjoin.jsp";
		} else if (url.equals("/confirmId.cu")) {
			System.out.println("/confirmId.cu");

			service.confirmId(req, res);
			viewPage = "/common/confirmId.jsp";
			// 회원가입
		} else if (url.equals("/uesrjoin.cu")) {
			System.out.println("/uesrjoin.cu");

			service.userjoin(req, res);
			viewPage = "/common/userjoin.jsp";

			// 회원가입 성공
		} else if (url.equals("/joinok.cu")) {
			System.out.println("/joinok.cu");

			int cnt = Integer.parseInt(req.getParameter("insertCnt"));
			req.setAttribute("cnt", cnt);

			viewPage = "/guest/main.jsp";
			// 로그인 페이지
		} else if (url.equals("/login.cu")) {
			System.out.println("/login.cu");

			viewPage = "/common/login.jsp";
			// 로그인 처리
		} else if (url.equals("/guestlogin.cu")) {
			System.out.println("/guestlogin.cu");

			service.loginPro(req, res);

			viewPage = "/guest/main.jsp";
			
			// 로그아웃 처리
		} else if (url.equals("/logout.cu")) {
			System.out.println("/logout.cu");

			req.getSession().invalidate();

			viewPage = "/common/login.jsp";
			// 마이페이지 이동
		} else if (url.equals("/guestmafage.cu")) {
			System.out.println("/guestmafage.cu");

			viewPage = "/guest/guestmyfage.jsp";
			// 회원수정페이지 이동
		} else if (url.equals("/mafage.cu")) {
			System.out.println("/mafage.cu");

			viewPage = "/guest/mafage.jsp";
			
			// 회원수정 상세 페이지
		} else if (url.equals("/mafagemain.cu")) {
			System.out.println("/mafagemain.cu");

			service.modifyView(req, res);
			viewPage = "/guest/mafagemain.jsp";
			
			//회원수정 처리 페이지
		} else if (url.equals("/userre.cu")) {
			System.out.println("/userre.cu");
			
			service.guestPro(req, res);
			viewPage = "/guest/userre.jsp";
			// 회원 탈퇴 페이지 이동
		} else if (url.equals("/guestdelete.cu")) {
			System.out.println("/guestdelete.cu");

			viewPage = "/guest/geustdelete.jsp";
		} else if (url.equals("/guestdeleteok.cu")) {
			System.out.println("/guestdeleteok.cu");

			service.deletePro(req, res);
			viewPage = "/guest/guestdeletok.jsp";

			// 해당 상품카테고리로 이동
		} else if (url.equals("/pants.cu")) {
			System.out.println("/pants.cu");
			
			service.pantslist(req, res);
			
			viewPage = "/host/PANTS.jsp";
		} else if (url.equals("/shoes.cu")) {
			System.out.println("/shoes.cu");
			
			service.shoselist(req, res);
			
			viewPage = "/host/SHOES.jsp";
		} else if (url.equals("/top.cu")) {
			System.out.println("/top.cu");
			
			service.toplist(req, res);
			
			viewPage = "/host/TOP.jsp";
			// 글목록
		} else if (url.equals("/board.cu")) {
			System.out.println("/board.cu");

			service.boardList(req, res);

			viewPage = "/board/boardList.jsp";

			// 글 상세페이지
		} else if (url.equals("/contentForm.cu")) {
			System.out.println("/contentForm.cu");

			service.contentForm(req, res);
			viewPage = "board/contentForm.jsp";
			// 글 수정페이지
		} else if (url.equals("/modifyForm.cu")) {
			System.out.println("/modifyForm.cu");
			int num = Integer.parseInt(req.getParameter("num"));
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));
			req.setAttribute("num", num);
			req.setAttribute("pageNum", pageNum);

			viewPage = "/board/modifyForm.jsp";

			// 글수정 상세페이지
		} else if (url.equals("/modifyView.cu")) {
			System.out.println("/modifyView.cu");

			service.modify(req, res);

			viewPage = "/board/modify.jsp";

			// 글 수정 처리 페이지
		} else if (url.equals("/modifyPro.cu")) {
			System.out.println("/modifyPro.cu");

			service.modifyPro(req, res);
			viewPage = "/board/modifyPro.jsp";

			// 글 쓰기 처리 페이지
		} else if (url.equals("/writeForm.cu")) {
			System.out.println("/writeForm.cu");
			
			// 제목글(답변글이 아닌 경우)
			int num = 0;
			int ref = 1; // 그룹화 아이디
			int ref_step = 0; // 글순서(행)
			int ref_level = 0; // 글레벨(들여쓰기)
			int pageNum = 0;
			
			
			// 답변글
			// contentForm.jsp에서 get방식으로 넘긴 값 num, ref, ref_step, ref_level
			if(req.getParameter("num") != null) {

				num = Integer.parseInt(req.getParameter("num"));
				ref = Integer.parseInt(req.getParameter("ref"));
				ref_step = Integer.parseInt(req.getParameter("ref_step"));
				ref_level = Integer.parseInt(req.getParameter("ref_level"));

			}
			pageNum = Integer.parseInt(req.getParameter("pageNum"));

			req.setAttribute("num", num);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("ref", ref);
			req.setAttribute("ref_step", ref_step);
			req.setAttribute("ref_level", ref_level);
			
			viewPage = "/board/writeForm.jsp";
		} else if (url.equals("/writePro.cu")) {
			System.out.println("/writePro.cu");

			service.writePro(req, res);

			viewPage = "/board/writePro.jsp";
			// 글삭제
		} else if (url.equals("/deleteForm.cu")) {
			System.out.println("/deleteForm.cu");
			int num = Integer.parseInt(req.getParameter("num"));
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));

			req.setAttribute("num", num);
			req.setAttribute("pageNum", pageNum);

			viewPage = "/board/deleteForm.jsp";

			// 글삭제 처리 페이지
		} else if (url.equals("/deletePro.cu")) {
			System.out.println("/deletePro.cu");

			service.delete(req, res);

			viewPage = "/board/deletePro.jsp";
			
			//제품 상세페이지 이동
		} else if (url.equals("/detailpage.cu")) {
			System.out.println("/detailpage.cu");
			
			service.detailpage(req, res);
			viewPage = "/guest/detailpage.jsp";
			//장바구니 담기
		} else if (url.equals("/cartadd.cu")) {
			System.out.println("/cartadd.cu");
			
			service.cartadd(req, res);
			viewPage="/guest/cartPro.jsp";
			//장바구니목록
		} else if (url.equals("/cartlist.cu")) {
			System.out.println("/cartlist.cu");
			
			service.cartlist(req, res);
			viewPage="/guest/cartlist.jsp";
			
			//장바구니 삭제
		} else if(url.equals("/cartdelete.cu")) {
			System.out.println("/cartdelete.cu");
			
			service.cartdelete(req, res);
			viewPage="/guest/cartdeletePro.jsp";
			//장바구니로 구매
		} else if(url.equals("/order.cu")) {
			System.out.println("/order.cu");
			
			service.order(req, res);
			viewPage="/guest/orderPro.jsp";
			//주문목록이동
		} else if(url.equals("/orderlist.cu")) {
			System.out.println("/orderlist.cu");
			
			
			service.orderlist(req, res);
			viewPage="/guest/orderlist.jsp";
			
			//상품환불
		} else if(url.equals("/ordercancel.cu")) {
			System.out.println("/ordercancel.cu");
			
			service.ordercancel(req, res);
			viewPage="/guest/ordercancelPro.jsp";
			
			//상품주문취소
		} else if(url.equals("/orderno.cu")) {
			System.out.println("/orderno.cu");
			
			service.orderdelete(req, res);
			viewPage="/guest/ordernoPro.jsp";
			//즉시구매
		}  else if(url.equals("/cartgo.cu")) {
			System.out.println("/cartgo.cu");
			
			service.cartgo(req, res);
			viewPage="/guest/cartgoPro.jsp";
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
		dispatcher.forward(req, res);
	}
}
