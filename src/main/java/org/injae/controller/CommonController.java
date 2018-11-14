package org.injae.controller;

import org.injae.domain.MemberVO;
import org.injae.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonController {
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder pwEncoder;
	
	@RequestMapping("/accessError")
	public void accessError(Authentication auth, Model model) {
		
		log.info(auth);
		
		model.addAttribute("msg", "Access Error");
		
	}
	
	@GetMapping({"/customLogin","/customLogout"})
	public void customLogin() {
		
		log.info("custom Login Logout Page");
		
	}
	
	@GetMapping("/signUp")
	public void signUpGet( ) {
		
		log.info("Sign Up Page");
		
	}
	
	@PostMapping("/signUp")
	public String signUpPost(MemberVO member, RedirectAttributes rttr) {
		
		member.setUserpw(pwEncoder.encode(member.getUserpw()));
		
		log.info("member : " + member);
		
		rttr.addFlashAttribute("result", mapper.insert(member)==1?"SUCCESS":"FAIL");
		
		mapper.insertAuth(member);
		
		return "redirect:/customLogin";
		
	}

}
