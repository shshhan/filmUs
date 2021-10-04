package com.filmus.myapp.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.filmus.myapp.domain.AdminVO;
import com.filmus.myapp.domain.Criteria;
import com.filmus.myapp.domain.CriteriaReport;
import com.filmus.myapp.domain.PageDTO;
import com.filmus.myapp.domain.PunishDTO;
import com.filmus.myapp.domain.ReportPageDTO;
import com.filmus.myapp.domain.ReportVO;
import com.filmus.myapp.domain.UserCountsVO;
import com.filmus.myapp.domain.UserVO;
import com.filmus.myapp.service.AdminService;
import com.filmus.myapp.service.AdminUserService;
import com.filmus.myapp.service.ReportService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Controller
@RequestMapping("/admin")
public class AdminController implements InitializingBean {
	
	@Autowired private AdminService aService;
	
	@Setter(onMethod_=@Autowired)
	private ReportService rService;
	
	@Setter(onMethod_=@Autowired)
	private AdminUserService uService;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("afterPropertiesSet() invoked.");
		
		Objects.requireNonNull(this.aService);
		Objects.requireNonNull(this.rService);
		Objects.requireNonNull(this.uService);
		
	}//afterPropertiesSet
	
	
	
	@GetMapping("/main")
	public String adminMain(Model model) {
		log.debug("adminMain() invoked.");
		
		AdminVO total = this.aService.totalCount();
		log.info(">>totalCount : "+total);
		model.addAttribute("total", total);
		
		return "admin/main";
	}//adminMain
	
	
	// ================ Report ================
	
	
	@GetMapping("/report/list")
	public String getList(@ModelAttribute("cri")CriteriaReport cri, Model model) {
		log.debug(">> report list () invoked.");
		
		List<ReportVO> list = this.rService.getList(cri);
		ReportPageDTO page = new ReportPageDTO(cri, this.rService.getTotal(cri));
		
		model.addAttribute("list",list);
		model.addAttribute("pageMaker",page);
		
		return "admin/report/list";
	}//getList

	
	@PostMapping("/report/complete")
	public String complete(PunishDTO dto) {
		log.debug("complete({}) invoked.", dto);
		
		int result = this.rService.complete(dto);
		
		
		return "redirect:/admin/report/list";
		
	}//complete
		
	
	// ================ User ================

		
	@GetMapping("/user")
	public String list(@ModelAttribute("cri") Criteria cri, Model model) {
		log.debug("list({}, model) invoked.", cri);
		
		List<UserVO> users = this.uService.getList(cri);
		
		Objects.requireNonNull(users);
		
		PageDTO pageDTO = new PageDTO(cri,this.uService.getTotal(cri));
		
		UserCountsVO vo = this.uService.getUsersStatus();
		
		String pagingUri = cri.getPagingUri();
		log.info("pagingUri : {}", pagingUri);
		
		model.addAttribute("pagingUri", pagingUri);
		model.addAttribute("list", users);
		model.addAttribute("pageMaker", pageDTO);
		model.addAttribute("count", vo);
		
		return "/admin/user/list";
	}//list
	
	@PostMapping("/user/changeIsAdmin")
	public String changeIsAdmin(Criteria cri, Integer userId) {
		log.debug("changeIsAdmin({}, {}, {}) invoked.", cri, userId);
		
		this.uService.changeUserAdmin(userId);
		
		String pagingUri = cri.getPagingUri();
		log.info("pagingUri : {}", pagingUri);
		
		return "redirect:/admin/user"+pagingUri;
		
	}//changeIsAdmin
	

}//end class
