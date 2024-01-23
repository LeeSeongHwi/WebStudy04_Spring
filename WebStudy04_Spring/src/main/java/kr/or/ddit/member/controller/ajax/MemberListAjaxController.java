package kr.or.ddit.member.controller.ajax;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.paging.BootstrapFormBasePaginationRenderer;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.common.paging.PaginationRenderer;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;

@Controller
@RequestMapping("/member/ajax")
public class MemberListAjaxController{
	@Inject
	private MemberService service;
	
	@GetMapping("memberList.do")
	public String listUI() {
		return "member/ajax/memberList";
	}
	
	@GetMapping(value="memberList.do", produces = MediaType.APPLICATION_JSON_VALUE)
	public void listData(
		@ModelAttribute("paging") PaginationInfo paging
//		, @ModelAttribute("condition") SearchCondition simpleCondition
		, @RequestParam(name="page", required = false, defaultValue = "1") int currentPage
		, Model model
	){
//		PaginationInfo paging = new PaginationInfo(3,3);
		paging.setCurrentPage(currentPage);
//		paging.setSimpleCondition(simpleCondition);
		List<MemberVO> memberList = service.retrieveMemberList(paging);
		
		PaginationRenderer renderer = new BootstrapFormBasePaginationRenderer("#submitForm");
		
		String pagingHTML = renderer.renderPagination(paging);
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("pagingHTML", pagingHTML);
		
//		1. 클라이언트 get 주소 요청
//		2. ajax 폴더 안에 있는 jsp jquery -> tiles 데프니션?
//		3. jsp 백그라운도 코드는 존재하지 않아야함, 검색버튼 누르면 이벤트 핸들러처리필요 submtiForm 이벤트핸들러구조, 동기요청중단 - 비동기 요청발생 검색결과 1페이지 
//		 , jsonObj 프로퍼티 3개, memberList pr -> pagingArea
	}
	
	@GetMapping(value="memberListResponseBody.do", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<MemberVO> listData2(
		@ModelAttribute("paging") PaginationInfo paging
//		, @ModelAttribute("condition") SearchCondition simpleCondition
		, @RequestParam(name="page", required = false, defaultValue = "1") int currentPage
		, Model model
	){
//		PaginationInfo paging = new PaginationInfo(3,3);
		paging.setCurrentPage(currentPage);
//		paging.setSimpleCondition(simpleCondition);
		List<MemberVO> memberList = service.retrieveMemberList(paging);
		
		PaginationRenderer renderer = new BootstrapFormBasePaginationRenderer("#submitForm");
		
		String pagingHTML = renderer.renderPagination(paging);
		
		return memberList;
	
	}
}



















