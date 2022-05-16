package com.springboot.study.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/board")
	public String boardList() {
		return "board/board-list";
	}
	
	
}
