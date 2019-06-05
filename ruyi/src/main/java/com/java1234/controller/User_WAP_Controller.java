package com.java1234.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java1234.entity.User;
import com.java1234.service.GroupService;
import com.java1234.service.PublicService;
import com.java1234.service.UserService;
import com.java1234.util.CryptographyUtil;

@Controller
@RequestMapping("/user/wap")
public class User_WAP_Controller {
	
	@Resource
	private UserService userService;
	@Resource
	private PublicService publicService;
	@Resource
	private GroupService groupService;
	
	
	/**
	 *  /user/wap/login
	 * @param user
	 * @param err_url
	 * @param bj
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(User user,String err_url,@RequestParam(value = "bj", required = false) String bj
			,HttpServletResponse response,HttpServletRequest request)throws Exception{
		Subject subject=SecurityUtils.getSubject();
		subject.getSession().setAttribute("login_type", "wx");//是个wx是用于realm登陆哪里。
		subject.getSession().setAttribute("bj", bj);//设置登陆的标识  用于区分android和ios pc  weixin
		UsernamePasswordToken token=new UsernamePasswordToken(user.getId_(), CryptographyUtil.md5(user.getPwd_(), "chenhao"));
		
		try{
			subject.login(token); // 登录验证
			user = userService.findById_(user.getId_());
			userService.setGroups(user);
			
			subject.getSession().setAttribute("currentUser", user); 
			
			//return "go_home";
			return "redirect:/wap_index";
			
			//如果登陆成功 就不会报错  报错就是登陆失败了
			//return "redirect:/admin/main.jsp";//地址栏发生变化了
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("user", user);
			request.setAttribute("errorInfo", "用户名或者密码错误！");
			return "redirect:/"+err_url;
		}
	}
	
	
	
	
}
