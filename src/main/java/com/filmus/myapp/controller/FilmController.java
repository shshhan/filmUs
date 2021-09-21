package com.filmus.myapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.filmus.myapp.domain.FilmReviewDTO;
import com.filmus.myapp.service.FilmService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/film/")
@Controller
public class FilmController {
	
	@Setter(onMethod_= @Autowired)
	private FilmService service;
	
	@GetMapping("{filmId}")
	public String filmInfo(@PathVariable("filmId") String filmId, Model model) {
		log.debug("filmInfo({}) invoked.", filmId);
		
		Map<String, Object> filmInfo = this.service.showFilmInfo(filmId);
		
		model.addAttribute("filmDetail", filmInfo.get("filmDetail"));
		model.addAttribute("director", filmInfo.get("director"));
		model.addAttribute("cast", filmInfo.get("cast"));
		model.addAttribute("reviews", filmInfo.get("reviews"));
		
		return "film/filmInfo";
	}//filmInfo
	
	@PostMapping("regReview")
	public String regReview(FilmReviewDTO dto, RedirectAttributes rttrs) {
		log.debug("regReview({}) invoked.", dto);
		
		if(this.service.registerReview(dto) == 1) {
			rttrs.addFlashAttribute("message", "review_completed");
			return "redirect:/film/"+dto.getFilmId();
		} else {
			return "/errorPage";
		}//if-else
		
	}//regReview


} // end class