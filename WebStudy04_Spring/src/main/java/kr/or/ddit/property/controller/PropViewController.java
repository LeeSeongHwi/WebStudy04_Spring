package kr.or.ddit.property.controller;

import javax.servlet.annotation.WebServlet;

import org.springframework.web.bind.annotation.GetMapping;

@WebServlet("/09/propView.do")
public class PropViewController{
	@GetMapping
	public String handleGet() {
		return "09/propView";
	}
}
