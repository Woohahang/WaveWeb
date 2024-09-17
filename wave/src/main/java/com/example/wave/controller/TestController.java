package com.example.wave.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class TestController {

	@GetMapping("/test")
	public String test() {
		log.info("테스트 요청");
		
		return "/pages/test";
	}
	
}