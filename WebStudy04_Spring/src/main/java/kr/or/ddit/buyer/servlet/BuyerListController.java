package kr.or.ddit.buyer.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.common.paging.BootstrapFormBasePaginationRenderer;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.common.paging.PaginationRenderer;
import kr.or.ddit.others.dao.OthersDAO;
import kr.or.ddit.vo.BuyerVO;

@Controller
@RequestMapping("/buyer/buyerList.do")
public class BuyerListController{
	@Inject
	private BuyerService service;
	
	private OthersDAO dao;
	public void addLprodList(HttpServletRequest req) {
		req.setAttribute("lprodList", dao.selectLprodList());
	}
	
	@GetMapping
	public String list(){
		addLprodList(req);
		
		Enumeration<String> paramterNames = req.getParameterNames();
		Map<String, Object> detailCondition = new LinkedHashMap<>();
		while (paramterNames.hasMoreElements()) {
			String name = (String) paramterNames.nextElement();
			detailCondition.put(name, req.getParameter(name));
		}
		
		req.setAttribute("condition", detailCondition);
		
		String pageStr = req.getParameter("page");
		int currentPage = 1; 
		if(StringUtils.isNumeric(pageStr)) {
			currentPage = Integer.parseInt(pageStr);
		}
		PaginationInfo paging = new PaginationInfo(3, 2);
		paging.setCurrentPage(currentPage);
		paging.setDetailCondition(detailCondition);
		
		List<BuyerVO> buyerList = service.retrieveBuyerList(paging);
		
		
		PaginationRenderer renderer = new BootstrapFormBasePaginationRenderer("#searchForm");
		
		String pagingHTML = renderer.renderPagination(paging);
		
		req.setAttribute("buyerList", buyerList);
		req.setAttribute("pagingHTML", pagingHTML);
		
		String logicalViewName = "buyer/buyerList";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
	}
}
