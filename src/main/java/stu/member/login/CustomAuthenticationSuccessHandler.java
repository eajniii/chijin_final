package stu.member.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import stu.common.common.CommandMap;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		// 로그인 성공 시 수행할 로직
	    // 예: 세션에 사용자 정보 저장
	    HttpSession session = request.getSession();
	    User user = (User) authentication.getPrincipal();
	    session.setAttribute("userInfo", user);
	    logger.info("**********************************username: "+user.getUsername());
	    logger.info("**********************************password: "+user.getPassword());
	    logger.info("**********************************auth: "+authentication.toString());
	    logger.info("**********************************auth: "+authentication.getAuthorities());

	    // 리다이렉션
	    //setDefaultTargetUrl("/loginAction.do");
	    //super.onAuthenticationSuccess(request, response, authentication);
		//getRedirectStrategy().sendRedirect(request, response, "/loginAction.do");
	
		//response.sendRedirect("/loginAction.do");
	}

}
