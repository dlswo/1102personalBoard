package org.injae.interceptor;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.injae.service.BoardService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
public class CheckLoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

String bno = request.getParameter("bno");
		
		log.info("=============================");
		log.info(bno);
		
		Cookie[] cks = request.getCookies();
		Cookie viewsCookie = null;
		boolean updateable = false;
		
		for (Cookie cookie : cks) {
			
			if(cookie.getName().equals("views")) {
				viewsCookie = cookie;
				break;
			}
			
		} 
		
		if(viewsCookie == null) {
			
			Cookie newCookie = new Cookie("views",bno+"_");
			newCookie.setMaxAge(60*60*24);
			viewsCookie = newCookie;
			updateable = true;
			
		}else {
			String cookieValue = viewsCookie.getValue();
			updateable = cookieValue.contains(bno+"_") == false;
			
			if(updateable) {
				cookieValue += bno + "_";
				viewsCookie.setValue(cookieValue);
			}
		}
		
		response.addCookie(viewsCookie);
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
