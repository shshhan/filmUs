package com.filmus.myapp.interceptor;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import com.filmus.myapp.controller.MainController;
import com.filmus.myapp.domain.UserVO;
import com.filmus.myapp.service.LoginService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class AuthInterceptor 
	implements HandlerInterceptor {

	@Setter(onMethod_= @Autowired)
	private LoginService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("preHandle(request, response, handler) invoked.");
	
		//Session Scope에서 로그인 정보 획득
		HttpSession session = request.getSession();
		UserVO user = (UserVO)session.getAttribute(MainController.loginKey);

		if(user != null) {	//로그인 정보가 있다면
			log.info(">>>>> Login Authenticated. >>>>>");
		
			return true;	 //요청을 그대로 컨트롤러로 전달
					
		}else {	//로그인 정보가 없다면
			//기존 URI & QueryString 획득
			String originalRequestURI = request.getRequestURI();
			String originalQueryString = request.getQueryString();

			originalRequestURI += 
					( (originalQueryString != null) && ( !(originalQueryString.equals("")) ) ?
							"?"+originalQueryString : "" );
			
			log.info("originalRequestURI : {}", originalRequestURI);
			
			//기존 URI를 값으로 하는 쿠키 생성
			Cookie uriCookie = 
					new Cookie("__ORIGINAL_REQUEST_URI__", originalRequestURI);
			
			uriCookie.setMaxAge(60);	//쿠키 유효기간 1분
			uriCookie.setPath("/");		//쿠키 경로 : 모든 경로
			
			response.addCookie(uriCookie);	//응답문서에 쿠키 추가
			
			response.sendRedirect("/main/loginRequired");	//메인의 로그인 modal로 Redirect
			log.info("Redirected to /main/loginRequired");
			
		}//if-elseIf-else
		
		return false;
		
	}//preHandle
	
}//end class