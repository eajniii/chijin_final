package stu.common.controller;

import java.io.*;
import java.net.*;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import stu.common.common.CommandMap;
import stu.common.service.CommonService;



@Controller
public class CommonController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	@RequestMapping(value="/common/downloadFile.do")
	public void downloadFile(CommandMap commandMap, HttpServletResponse response) throws Exception{
		Map<String,Object> map = commonService.selectFileInfo(commandMap.getMap());
		String storedFileName = (String)map.get("UPLOAD_SAVE_NAME");
		String originalFileName = (String)map.get("UPLOAD_ORIGIN_NAME");
		
		byte fileByte[] = FileUtils.readFileToByteArray(new File("D:\\sts4File\\"+storedFileName));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8")+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.getOutputStream().write(fileByte);
		
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	@RequestMapping(value="/common/redirection.do", method = RequestMethod.GET)
	public void urlRedirect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String req_url = req.getParameter("url");
		if(req_url != null) {
			URL url = new URL(req_url);
			String htmlContent;
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuffer html = new StringBuffer();
			while ((htmlContent = reader.readLine()) != null){
				html.append(htmlContent);
			}
			reader.close();
			resp.getWriter().println(html);
		} else {
			resp.getWriter().write("?url");
		}
	}

	@RequestMapping(value="/checkPing.do", method = RequestMethod.GET)
	public ModelAndView command(ModelAndView mv, HttpServletRequest req) {

		Process process = null;
		BufferedReader in = null;
		BufferedReader err = null;
		String out = "";
		String s = null;

		String check = req.getParameter("check");
		System.out.println("check: "+check);
		if(check != null) {
			try {
				String [] cmd = {"/bin/sh","-c",check};
				System.out.println(check);
				process = Runtime.getRuntime().exec(cmd);
				in = new BufferedReader(new InputStreamReader(process.getInputStream()));
				while ((s = in.readLine()) != null) {
					out += s + "<br>";
				}
				err = new BufferedReader(new InputStreamReader(process.getErrorStream()));
				while (err.ready()) {
					out += err.readLine() + "<br>";
				}
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}

		mv.setViewName("admin/server");
		mv.addObject("out", out);
		return mv;
	}

	//봇 크롤링 방지
	@RequestMapping(value = "/robots.txt")
	@ResponseBody
	public void robotsBlock(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().write("User-agent: *\nDisallow: /\n");
		} catch (IOException e) {
			log.info("exception", e);
		}
	}
}
