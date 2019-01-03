package project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.service.hostserviceImpl;

@WebServlet("*.ad")
public class hostController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public hostController() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		ActionDo(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		ActionDo(req, res);
	}

	public void ActionDo(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		hostserviceImpl service = new hostserviceImpl();
		req.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String url = uri.substring(contextPath.length()); 
		
		
		//메인페이지
		if(url.equals("/hostmain.ad") || url.equals("/*.ad")) {
			System.out.println("/hostmain.ad");
			
			viewPage = "/host/hostmain.jsp";
		//로그인 페이지
		}else if(url.equals("/hostlogin.ad")) {
			System.out.println("/hostlogin.ad");
			
			viewPage = "/hostaccount/hostlogin.jsp";
		//로그인 처리
		}else if(url.equals("/hostmaingo.ad")) {
			System.out.println("/hostmaingo.ad");
			
			service.loginPro(req, res);
			viewPage = "/host/hostmain.jsp";
		}else if(url.equals("/logout.ad")) {
			System.out.println("/logout.ad");
	
			req.getSession().setAttribute("strId", null);
			req.setAttribute("cnt", 2);
			viewPage = "/host/main.jsp";
			
		
		//해당 상품카테고리로 이동
		}else if(url.equals("/pants.cu")) {
			System.out.println("/pants.cu");
			
			viewPage = "/host/PANTS.jsp";
		}else if(url.equals("/shoes.cu")) {
			System.out.println("/shoes.cu");
			
			viewPage = "/host/SHOES.jsp";
		}else if(url.equals("/top.cu")) {
			System.out.println("/top.cu");
			
			viewPage = "/host/TOP.jsp";
			
		//재고목록
		}else if(url.equals("/hostlist.ad")) {
			System.out.println("/hostlist.ad");
			
			service.stocklist(req, res);
			viewPage = "/host/hostlist.jsp";
		//재고추가페이지
		}else if(url.equals("/stockadd.ad")) {
			System.out.println("/stockadd.ad");
			
			viewPage = "/host/stockadd.jsp"; 
		//재고추가처리
		}else if(url.equals("/add.ad")) {
			System.out.println("/add.ad");
			
			service.stockadd(req, res);
			viewPage = "/host/stockPro.jsp";
			
		//재고수정
		}else if(url.equals("/update.ad")) {
			System.out.println("/update.ad");
			service.stockview(req, res);
			
			viewPage = "/host/update.jsp";
			
		//재고수정처리 
		}else if(url.equals("/updatee.ad")) {
			System.out.println("/updatee.ad");
			
			service.stockupdate(req, res);
			viewPage = "/host/stockPro.jsp";
			
		//재고삭제
		}else if(url.equals("/delete.ad")) {
			System.out.println("/delete.ad");
			
			service.stockdelete(req, res);
			viewPage = "/host/deletePro.jsp";
		
		//주문목록
		}else if(url.equals("/hostorderlist.ad")) {
			System.out.println("/hostorderlist.ad");
			
			service.guestorderlist(req, res);
			viewPage = "/host/hostorderlist.jsp";
			
		//주문승인
		}else if(url.equals("/guestorderadd.ad")) {
			System.out.println("/guestorderadd.ad");
			
			service.guestorderadd(req, res);
			viewPage = "/host/orderok.jsp";
			
		//환불목록
		}else if(url.equals("/hostcancel.ad")) {
			System.out.println("/hostcancel.ad");
			
			service.guestcancellist(req, res);
			viewPage = "/host/hostcancellist.jsp";
			
		//환불처리
		}else if(url.equals("/orderdelete.ad")) {
			System.out.println("/orderdelete.ad");
			
			service.guestcancel(req, res);
			viewPage = "/host/orderPro.jsp";
		//결산페이지
		}else if(url.equals("/sum.ad")) {
			System.out.println("/sum.ad");
			try {
				service.sum(req, res);
			}catch (Exception e) {
				e.printStackTrace();
			}
			viewPage = "/host/sum.jsp";
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
		dispatcher.forward(req, res);
	}
}
	
