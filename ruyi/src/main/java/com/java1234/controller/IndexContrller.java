package com.java1234.controller;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.activiti.engine.TaskService;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import com.java1234.entity.Access_token;
import com.java1234.entity.Result;
import com.java1234.entity.Tree;
import com.java1234.entity.User;
import com.java1234.http.HttpClient;
import com.java1234.service.PublicService;
import com.java1234.service.TreeService;
import com.java1234.service.UserService;
import com.java1234.util.DateUtil;
import com.java1234.util.ResponseUtil;
import com.java1234.weixin.WeiXinUtil;
import net.sf.json.JSONObject;



/**
 * 主页Contrller
 * 
 * @author Administrator
 */

@Controller
@RequestMapping("/")
public class IndexContrller {
	
	@Resource
	private TreeService treeService;
	@Resource
	private PublicService publicService;
	@Resource
	private TaskService taskService;
	
	
	/**
	 * 请求主页
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "首页");
		mav.addObject("title", "首页");
		
		
		mav.setViewName("PC_index");
		return mav;
	}
	
	
	
	/**
	 * 请求主页
	 */
	@RequestMapping("/wap_index")
	public ModelAndView wap_index(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("pageTitle", "首页");
		mav.addObject("title", "首页");
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		
		String s_name = "";
		//组任务数量 
		long total=taskService.createTaskQuery().taskCandidateUser(currentUser.getId_()).taskNameLike("%"+s_name +"%").count(); // 获取总记录数
		//个人任务数量 
		long total2 =taskService.createTaskQuery().taskAssignee(currentUser.getId_()).taskNameLike("%"+s_name +"%").count(); // 获取总记录数
		mav.addObject("daibanTotal", total+total2);
		
		mav.addObject("page", "foreground/common/mobile_index.jsp");
		mav.setViewName("mobile_main");
		return mav;
	}
	
	
	
	/**
	 *  电脑登陆页面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("PC_login");
		return mav;
	}
	
	
	/**
	 *  ios登陆页面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ios_login")
	public ModelAndView ios_login(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ios_login");
		return mav;
	}
	
	
	/**
	 *  android登陆页面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/android_login")
	public ModelAndView android_login(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("android_login");
		return mav;
	}
	
	
	/**
	 * 后台主页
	 */
	@RequestMapping("/admin/main")
//	public ModelAndView admin_main() throws Exception {
	public ModelAndView admin_main(HttpServletResponse responce) throws Exception {
		ModelAndView mav = new ModelAndView();
//		mav.addObject("pageTitle", "汝一净水  后台");
//		mav.addObject("title", "汝一净水  后台");

		mav.addObject("pageTitle", "hello 1");

		String t = "汝一净水  后台";
//		t = new String(t.getBytes("iso-8859-1"),"utf-8");
//		t = new String(t.getBytes("GB2312"),"utf-8");
//		mav.addObject("title", t);
		mav.addObject("title", t);
		publicService.addLeftMenu(mav);
		
		mav.setViewName("/admin/main");

		responce.setCharacterEncoding("utf-8");

		return mav;
	}
	
	@RequestMapping("/fail")
	public ModelAndView fail() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "无权查看");
		mav.addObject("title", "无权查看");
		mav.addObject("page", "foreground/common/fail.jsp");
		mav.setViewName("mobile_main");
		return mav;
	}
	
	
	
	@RequestMapping("/wo")
	public ModelAndView wo() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("page", "foreground/common/wo.jsp");
		mav.setViewName("mobile_main");
		return mav;
	}
	
	
	
	@RequestMapping("/2")
	public ModelAndView wx2() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext servletContext = webApplicationContext.getServletContext(); 
		Access_token token = (Access_token)  servletContext.getAttribute("access_token");
		//https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN
		
		//请求临时二维码url  永久和临时url一样
		String ticket_post_url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+token.getAccess_token();
		String temp_ticket_data = WeiXinUtil.getTempTicketData();
		//String ticket_data = WeiXinUtil.getTicketData();
		String temp_ticket_result_json = HttpClient.post(ticket_post_url, temp_ticket_data);
		
		
		/**
		 * result_json如下
		 * {"ticket":"gQFW7zwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAycjlpaE55VzVlWGsxb3J5Wk5wMXUAAgQTm31ZAwQIBwAA"
		 * ,"expire_seconds":1800,"url":"http:\/\/weixin.qq.com\/q\/02r9ihNyW5eXk1oryZNp1u"}
		 */
		JSONObject result = JSONObject.fromObject(temp_ticket_result_json);
		
		/**
		 * GET请求说明（TICKET必需UrlEncode）
		 * https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQFW7zwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAycjlpaE55VzVlWGsxb3J5Wk5wMXUAAgQTm31ZAwQIBwAA
		 */

		
			
		
		System.out.println(result.get(""));
		
		mav.setViewName("/admin/main");
		return mav;
	}
	
	
	
	
}
