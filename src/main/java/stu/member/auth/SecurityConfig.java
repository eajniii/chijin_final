//package stu.member.auth;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
//import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig{
//
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		http
//			.authorizeHttpRequests((authorizeHttpRequests)->authorizeHttpRequests
//					.requestMatchers("/resources/**", "/css/**","/image/**","/js/**","/login.do").permitAll())
//			.csrf((csrf)->csrf
//					.ignoringRequestMatchers(new AntPathRequestMatcher("/**")))
//			.headers((headers) -> headers
//					.addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN)));
//		
//			return http.build();
//	}
//	protected void configure(HttpSecurity http) throws Exception{
//		http.authorizeRequests().requestMatchers("/mgmt/**").hasAnyRole("ADMIN").and().portMapper().http(8972).mapsTo(80).and().formLogin().loginPage("/mgmt/login").permitAll();
//		
//	}
//	
//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//}
