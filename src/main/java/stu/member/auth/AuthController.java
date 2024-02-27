package stu.member.auth;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import stu.admin.main.AdminMainService;
import stu.common.common.CommandMap;

@Controller
@RequestMapping("/auth/*")
public class AuthController {
	Logger log = Logger.getLogger(this.getClass());
	@Resource(name="adminMainService")
	private AdminMainService adminMainService;
	
	@RequestMapping(value="/welcome.do")
	public ModelAndView welcomePage() throws Exception{
		ModelAndView mv = new ModelAndView("main");
		return mv;
	}
	@RequestMapping(value="/mypage.do")
	public ModelAndView mypage() throws Exception{
		ModelAndView mv = new ModelAndView("my/myMain");
		return mv;
	}
	@RequestMapping(value="/mgmt.do")
	public ModelAndView mgmtpage(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("admin/adminMain");
		List<Map<String,Object>> dashList = adminMainService.dashBoard(commandMap);
		mv.addObject("dashList", dashList);
		return mv;
	}
	
}
