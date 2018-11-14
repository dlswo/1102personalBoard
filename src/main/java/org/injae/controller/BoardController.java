package org.injae.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.Cookie;

import org.injae.domain.Board;
import org.injae.domain.BoardAttachVO;
import org.injae.domain.Param;
import org.injae.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/list")
	public void list(@ModelAttribute("pageObj") Param param, Model model) {
		
		log.info("list page...");
		
		param.setTotal(service.getTotal());
		
		model.addAttribute("list", service.getList(param));
	}
	
	@GetMapping("/register")
	public void registerGet() {
		
	}
	
	@PostMapping("/register")
	public String registerPost(Board board, RedirectAttributes rttr) {
		
		log.info("==============================");
		
		log.info("register!!" + board);
		
		if(board.getAttachList() != null) {
			board.getAttachList().forEach(attach -> log.info(attach));
		}
		
		log.info("==============================");
		
		rttr.addFlashAttribute("result", service.register(board)==1?"SUCCESS":"FAIL");
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/read")
	public void read(@ModelAttribute("pageObj") Param param, Model model, @CookieValue(value="views",required=false)String cookie) {
		log.info("-------1");
		if(cookie == null) {
			log.info("-------2");
			service.updateViews(param);
		}else if(!cookie.contains(param.getBno()+"")){
			log.info("-------3");
			service.updateViews(param);
		}
		
		log.info("-------4");
		model.addAttribute("board", service.read(param));
		
	}
	
	@GetMapping("/modify")
	public void modify(@ModelAttribute("pageObj") Param param, Model model) {
		
		model.addAttribute("board", service.read(param));
		
	}
	
	@PreAuthorize("principal.vo.username == #writer")
	@PostMapping("/remove")
	public String remove(@ModelAttribute("pageObj") Param param, RedirectAttributes rttr, String writer) {
		
		List<BoardAttachVO> attachList = service.getAttachList(param);
		
		deleteFiles(attachList);
		
		rttr.addFlashAttribute("result", service.remove(param)==1?"SUCCESS":"FAIL");
		
		return param.getLink("redirect:/board/list");
	}
	
	@PreAuthorize("principal.vo.username == #board.writer")
	@PostMapping("/modify")
	public String modify(@ModelAttribute("pageObj") Param param, Board board, RedirectAttributes rttr) {
		
		log.info("modify Post....");
		
		rttr.addFlashAttribute("result", service.modify(board)==1?"SUCCESS":"FAIL");
		
		return param.getLink("redirect:/board/list");
	}
	
	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Param param){
		
		return new ResponseEntity<>(service.getAttachList(param), HttpStatus.OK);		
	}
	
	private void deleteFiles(List<BoardAttachVO> attachList) {
		
		if(attachList == null || attachList.size() == 0) {
			return;
		}
		
		log.info("delete attach files");
		log.info(attachList);
		
		attachList.forEach(attach -> {
			try {
				
				Path file = Paths.get("C:\\upload\\"+ attach.getPath() + "\\" + attach.getUuid() + "_" + attach.getFilename());
				
				Files.deleteIfExists(file);
				
				if(Files.probeContentType(file).startsWith("image")) {
					
					Path thumbNail = Paths.get("C:\\upload\\"+ attach.getPath() + "\\s_" + attach.getUuid() + "_" + attach.getFilename());
					
					Files.delete(thumbNail);
				}
				
			}catch(Exception e){
				log.error("delete file error" + e.getMessage());
			}
			
		});
		
	}

}
