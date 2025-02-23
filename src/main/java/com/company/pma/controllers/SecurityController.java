package com.company.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.company.pma.entities.UserAccount;
import com.company.pma.services.UserAccountService;

@Controller
public class SecurityController {
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserAccountService userAccountService;

	@GetMapping("/register")
	public String register(Model model) {
		
		UserAccount userAccount=new UserAccount();
		model.addAttribute("userAccount", userAccount);
		return "security/register";
	}
	
	@PostMapping("/register/save")
	public String saveUser(Model model,UserAccount account) {
		
		account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
		userAccountService.save(account);
		return "redirect:/";
	}
}
