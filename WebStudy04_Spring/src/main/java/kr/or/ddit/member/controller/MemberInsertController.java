package kr.or.ddit.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 회원 가입 처리
 * 1. 가입 양시 제공(GET)
 * 2. 양식을 통해 이볅 및 전송된 데이터 처리(POST)
 * 
 */
@Slf4j
@Controller
@RequestMapping("/member/memberInsert.do")
public class MemberInsertController extends HttpServlet{
   
   @Inject
   private MemberService service;
   
   @ModelAttribute("member")
   public MemberVO member() {
      return new MemberVO();
   }
   
   @GetMapping
   public String insertForm() {
      return "member/memberForm";
   }
   
   @PostMapping
   public String insertProcess(
         @Validated(InsertGroup.class) @ModelAttribute("member") MemberVO member
         , BindingResult errors
         , Model model
         , RedirectAttributes redirect)  {
      
      
      String logicalViewName = null;
      if(!errors.hasErrors()) {
         
         ServiceResult result = service.createMember(member);
         
         String message = null;
         switch(result) {
         case OK :
            logicalViewName = "redirect:/"; //Post-Redirect-Get
            redirect.addFlashAttribute("message","회원가입 성공");
            break;
         case FAIL : //잠깐 서버에 문제가 생김
            logicalViewName = "member/memberForm";
            break;
         default : //아이디 중복 
            logicalViewName = "member/memberForm";
            message = "아이디 중복, 바꾸셈";
            break;
         }
         
         model.addAttribute("message", message);
         
      }else {
         logicalViewName = "member/memberForm";
      }
      
      return logicalViewName;
   }
}