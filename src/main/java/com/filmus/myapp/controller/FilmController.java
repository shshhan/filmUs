package com.filmus.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.filmus.myapp.domain.AdminUserPageDTO;
import com.filmus.myapp.domain.Criteria;
import com.filmus.myapp.domain.FilmInfoReactionVO;
import com.filmus.myapp.domain.FilmReviewDTO;
import com.filmus.myapp.domain.FilmReviewVO;
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
	public String filmInfo(@PathVariable("filmId") String filmId,
						   @ModelAttribute("cri")Criteria cri,
						   Model model) {
		log.debug("filmInfo({}, {}) invoked.", filmId, cri);
		
		cri.setAmount(5);
		
		Map<String, Object> filmInfo = this.service.showFilmInfo(filmId);
		
		Map<String, Object> params = new HashMap<>();
		params.put("filmId", filmId);
		params.put("currPage", cri.getCurrPage());
		params.put("amount", cri.getAmount());
		params.put("pagesPerPage", cri.getPagesPerPage());
			
		List<FilmReviewVO> reviews  = this.service.getReviewList(params);

		AdminUserPageDTO pageDTO = new AdminUserPageDTO(cri,this.service.getTotal(params));
		
		model.addAttribute("filmDetail", filmInfo.get("filmDetail"));
		model.addAttribute("director", filmInfo.get("director"));
		model.addAttribute("cast", filmInfo.get("cast"));
		model.addAttribute("reviews", reviews);
		model.addAttribute("pageMaker", pageDTO);

		
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

	@ResponseBody
	@GetMapping("getFilmReaction")
	public Map<String, Object> getFilmReaction(String userId, String filmId){
		log.debug("getFilmeaction({}, {}) invokedd", userId, filmId);
		
		Map<String, Object> result = new HashMap<>();
		
		FilmInfoReactionVO filmReaction = this.service.getFilmReactionOfThis(filmId);
		result.put("favoriteCnt", filmReaction.getFavorite());
		result.put("watchedCnt", filmReaction.getWatched());
		result.put("wishToWatchCnt", filmReaction.getWishToWatch());
		
		if(userId != null) {
			List<Integer> userReaction = this.service.getFilmReactionOfUser(userId, filmId);			
			result.put("userReaction", userReaction);
		}//if
				
		return result; 
	}//getFilmReaction
	
	@ResponseBody
	@PostMapping("addReaction")
	public Integer addReaction(String userId, String filmId, Integer code) {
		log.debug("addReaction({}, {}, {}) invoked.", userId, filmId, code);
		
		int aLine = this.service.addFilmReactionOfUser(userId, filmId, code);
		log.info("result : ", aLine);
		
		return aLine;			
	}//addReaction
	
	@ResponseBody
	@PostMapping("removeReaction")
	public Integer removeReaction(String userId, String filmId, Integer code) {
		log.debug("removeReaction({}, {}, {}) invoked.", userId, filmId, code);
		
		int aLine = this.service.removeFilmReactionOfUser(userId, filmId, code);
		log.info("result : ", aLine);
		
		return aLine;			
	}//addReaction
	
	
} // end class