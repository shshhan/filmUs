package com.filmus.myapp.controller;

import java.util.List;
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
import com.filmus.myapp.domain.ReviewCommentDTO;
import com.filmus.myapp.domain.ReviewCommentVO;
import com.filmus.myapp.domain.ReviewDTO;
import com.filmus.myapp.domain.ReviewVO;
import com.filmus.myapp.service.FilmService;
import com.filmus.myapp.service.ReviewService;

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
	@Setter(onMethod_= @Autowired)
	private ReviewService rSerice;
	
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

	@GetMapping({"{filmId}/review/{rno}"})
	public String getReview(@PathVariable("rno") Integer rno, @PathVariable("filmId") Integer filmId, Model model) {
		log.debug("getReview() invoked.");
		
		ReviewVO review = this.rSerice.reviewDetail(rno, filmId);
		List<ReviewCommentVO> list = this.rSerice.rcList(rno);
		model.addAttribute("list", list);
		model.addAttribute("review", review);
		
		return "film/review";
	}//getReview
	
	@PostMapping("modReview")
	public String modReview(ReviewDTO dto) {
		log.debug("modReview({})invoked.", dto);
		
		if(this.rSerice.modReview(dto)==1) {
			return "redirect:/film/"+dto.getFilmId()+"/review/"+dto.getRno();			
		} else {
			return "/errorPage";
		}//if-else
	}//modReview
	
	@PostMapping("delReview")
	public String delReview(Integer rno, Integer filmId, RedirectAttributes rttrs) {
		log.debug("delReview({},{})invoked.", rno, filmId);
		
		if(this.rSerice.delReview(rno,filmId)==1) {
			rttrs.addFlashAttribute("message","review deleted");
			return "redirect:/film/"+filmId;			
		} else {
			return "/errorPage";
		}//if-else
	}//delReview
	
	@PostMapping("newReply")
	public String newReply(ReviewCommentDTO dto, Integer filmId, Integer rno, RedirectAttributes rttrs) {
		log.debug("newReply()invoked.");

		if(this.rSerice.rcCreate(dto)==1) {
			return "redirect:/film/"+filmId+"/review/"+rno;
		} else {
			return "/errorPage";
		}//if-else
	}//newReply
	
	
	@PostMapping("delReply")
	public String delReply(Integer rcno, Integer filmId, Integer rno, RedirectAttributes rttrs) {
		log.debug("newReply()invoked.");

		if(this.rSerice.rcDelete(rcno, rno)==1) {
			return "redirect:/film/"+filmId+"/review/"+rno;
		} else {
			return "/errorPage";
		}//if-else
	}//newReply

} // end class