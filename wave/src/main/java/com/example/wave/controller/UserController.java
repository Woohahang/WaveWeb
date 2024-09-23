package com.example.wave.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.wave.dto.GameNicknameDTO;
import com.example.wave.dto.UserDTO;
import com.example.wave.enums.Games;
import com.example.wave.service.GameService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class UserController {

	@Autowired
	private GameService gameService;

	@GetMapping("/logout/oauth2")
	public String logoutFromOAuth2(HttpSession httpSession) {
		httpSession.invalidate();
		return "redirect:/";
	}

	@GetMapping("/account")
	public String displayAddNicknameForm(Model model) {
		
		model.addAttribute("gameNames", Arrays.asList(Games.values()));
		model.addAttribute("userGameNicknameDTO", new GameNicknameDTO());

		return "pages/account";
	}

	@PostMapping("/addnickname")
	public String submitNickname(@ModelAttribute GameNicknameDTO gameNicknameDTO, HttpSession session) {
		log.info("addNickname 메서드 동작");

		String userId = getCurrentUserId(session);
		gameNicknameDTO.setUserDiscordId(userId);

		gameService.saveUserGameNickname(gameNicknameDTO);

		return "redirect:/";
	}

	// 헬퍼 메서드
	protected String getCurrentUserId(HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
		return (userDTO != null) ? userDTO.getDiscordId() : null;
	}

}
