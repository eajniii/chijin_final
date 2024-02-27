package stu.member.join;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import stu.common.common.CommandMap;


@Controller
public class JoinController {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="joinService")
	private JoinService joinService;
	
	// 회원가입 폼
	@RequestMapping(value="/joinForm.do")
	public ModelAndView joinForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("login/joinForm");
		
		return mv;
	}
	
	// 회원가입 처리
	@RequestMapping(value="/joinAction.do", method=RequestMethod.POST)
	public ModelAndView insertMember(CommandMap commandMap, HttpServletRequest request) throws Exception {
		//------------------------- step 1. 회원가입 요청 확인----------------------------------------------------
		ModelAndView mv = new ModelAndView("/joinForm.do");
		
		int id_min = 3;
		int id_max = 12;
		int pnb_len = 11;
		int zip_len = 5;
		boolean result = true;
		Pattern id_ptr = Pattern.compile("^[a-z0-9]*$");
		Pattern email_ptr = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
		Pattern pw_ptr = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%&*()_+=|<>?{}\\[\\]~-]).{8,20}$");
		
		//id 제한
		String id = request.getParameter("MEMBER_ID").toLowerCase();
		if(id.length()<id_min || id.length()>id_max || !id_ptr.matcher(id).matches()) {
			log.info("--------------------------------------------------------------------id: "+ id);
			result = false;
		}
		
		//pw 제한
		String pw = request.getParameter("MEMBER_PASSWD");
		if(!pw_ptr.matcher(pw).matches()) {
			log.info("--------------------------------------------------------------------pw: "+pw);
			result = false;
		}
		
		// 이메일 제한
		String email = request.getParameter("MEMBER_EMAIL") + "@" + request.getParameter("MEMBER_EMAIL2");
		System.out.println("이메일 : "+email);
		if(request.getParameter("MEMBER_EMAIL2") == "") {
			email = request.getParameter("MEMBER_EMAIL");
		}
		if (!email_ptr.matcher(email).matches()) {
			log.info("------------------------------------------------------------------email: "+email);
			result = false;
		}
		
		// 전화번호 제한
		String phone = request.getParameter("MEMBER_PHONE").replace("[^\\d]", "");
		if (phone.length()!=pnb_len) {
			log.info("-------------------------------------------------------------------phone: "+phone);
			result = false;
		}
		
		//집주소 제한
		String zipcode = request.getParameter("MEMBER_ZIPCODE");
		if(zipcode.length()!=zip_len || !zipcode.chars().allMatch(Character::isDigit)) {
			log.info("-------------------------------------------------------------------zipcode: "+zipcode);
			result = false;
		}
		
		String addr1 = request.getParameter("MEMBER_ADDR1");
		if (addr1.length()<5) {
			log.info("-------------------------------------------------------------------addr1: "+addr1);
			result = false;
		}
		
		log.info("------------------------------------checking---------------------------------------------");
		if (!result) {
			return mv;
		}
		log.info("------------------------------------success---------------------------------------------");
		
		//-------------------------step 2. 회원가입-------------------------------------------------------
		ModelAndView mv_success = new ModelAndView("login/joinAction");
		// 이메일, SMS 수신 여부
		String email_agree = (String)commandMap.get("EMAIL_AGREE");
		String sms_agree = (String)commandMap.get("SMS_AGREE");
		// 체크를 하지 않으면 '0' 으로 set 후 넘김
		if(email_agree == null) {
			email_agree = "0";
			commandMap.put("EMAIL_AGREE", email_agree);
			mv_success.addObject("EMAIL_AGREE",email_agree);
		}
		if(sms_agree == null) {
			sms_agree = "0";
			commandMap.put("SMS_AGREE", sms_agree);
			mv_success.addObject("SMS_AGREE",sms_agree);
		}
		// 이메일
		commandMap.remove("MEMBER_EMAIL");
		commandMap.put("MEMBER_EMAIL", email);
		mv_success.addObject("MEMBER_EMAIL",email);
		
		String birth = request.getParameter("MEMBER_BIRTH")
					 + request.getParameter("MEMBER_BIRTH2") 
					 + request.getParameter("MEMBER_BIRTH3");	
		commandMap.remove("MEMBER_BIRTH");
		commandMap.put("MEMBER_BIRTH", birth);
		mv_success.addObject("MEMBER_BIRTH",birth);

		joinService.insertMember(commandMap.getMap());

		mv_success.addObject("MEMBER_NAME", commandMap.get("MEMBER_NAME")); 
		mv_success.addObject("MEMBER_ID", commandMap.get("MEMBER_ID"));
		mv_success.addObject("MEMBER_PW", commandMap.get("MEMBER_PW"));
		mv_success.addObject("MEMBER_PHONE", commandMap.get("MEMBER_PHONE"));
		mv_success.addObject("MEMBER_ZIPCODE", commandMap.get("MEMBER_ZIPCODE"));
		mv_success.addObject("MEMBER_ADDR1", commandMap.get("MEMBER_ADDR1"));
		mv_success.addObject("MEMBER_ADDR2", commandMap.get("MEMBER_ADDR2"));

		return mv_success;
	}

	//아이디 중복 체크
	@RequestMapping(value="/selectIdCheck.do", method=RequestMethod.GET)
	@ResponseBody
	public int selectIdCheck(@RequestParam("mem_userid") String mem_userid) throws Exception{
		
		int cnt = joinService.selectIdCheck(mem_userid);
		
		return cnt;
	}
	
	//이메일 중복 체크 - KMK 추가
	@RequestMapping(value="/selectEmailCheck.do", method=RequestMethod.GET)
	@ResponseBody
	public int selectEmailCheck(@RequestParam("user_email") String user_email) throws Exception{
		System.out.println("이메일 체크"+user_email);
		int cnt = joinService.selectEmailCheck(user_email);
		
		return cnt;
	}
	
	//이메일 인증-회원가입
    @RequestMapping(value = "/emailAuth.do", produces = "application/json")
    @ResponseBody
    public boolean sendMailAuth(HttpSession session, @RequestParam String user_email) {
        int ran = new Random().nextInt(100000) + 10000; // 10000 ~ 99999
        String joinCode = String.valueOf(ran);
        session.setAttribute("joinCode", joinCode);
 
        String subject = "<JM COLLECTION> 회원가입 인증 코드입니다.";
        StringBuilder sb = new StringBuilder();
        sb.append("귀하의 인증 코드는 " + joinCode + " 입니다.");
        return joinService.send(subject, sb.toString(), "1teampjt@gmail.com", user_email, null);
    }
     
    //이메일 인증확인 - KMK 추가
    @RequestMapping(value = "/emailAuthCheck.do", produces = "application/json")
    @ResponseBody
    public ModelAndView emailAuth(HttpSession session, @RequestParam String joinCode) {
    	ModelAndView mv = new ModelAndView("jsonView");
    	String originalJoinCode = (String)session.getAttribute("joinCode");
    	log.debug("originalJoinCode >>>>"+originalJoinCode +" & "+joinCode);
    	if(originalJoinCode.equals(joinCode)) mv.addObject("result","complete");
    	else mv.addObject("result","fail");
    	
    	return mv;
    }
}
