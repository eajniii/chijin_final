//package stu.common.common;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.annotation.Resource;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import stu.member.login.LoginDAO;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//	
//	@Resource(name="loginDAO")
//	public LoginDAO loginDAO;
//	
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//	
//	}
//	
//	public Claims extractClaims(String token) {
//		
//		
//		return Jwts.parser()
//				.setSigningKey(null).;
//	}
//
//}
