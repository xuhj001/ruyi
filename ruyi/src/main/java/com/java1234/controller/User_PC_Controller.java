package com.java1234.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Update;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java1234.entity.FenDian;
import com.java1234.entity.Group;
import com.java1234.entity.User;
import com.java1234.service.FenDianService;
import com.java1234.service.GroupService;
import com.java1234.service.PublicService;
import com.java1234.service.UserService;
import com.java1234.util.CryptographyUtil;

@Controller
@RequestMapping("/user/pc")
public class User_PC_Controller {
	
	@Resource
	private UserService userService;
	@Resource
	private PublicService publicService;
	@Resource
	private GroupService groupService;
	@Resource
	private FenDianService fenDianService;
	/**
	 * /user/pc/login  验证用户登陆
	 * @param err_url 
	 * @param bj 标记  用户记录 pc 还是ios android
	 * @return return err_url;//这个返回的是login  这是直接返回的视图 login.jsp
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(User user,String err_url,@RequestParam(value = "bj", required = false) String bj
			,HttpServletResponse response,HttpServletRequest request,RedirectAttributes attr)throws Exception{
	        
	        
		Subject subject=SecurityUtils.getSubject();
		subject.getSession().setAttribute("login_type", "wx");//是个wx是用于realm登陆哪里。
		subject.getSession().setAttribute("bj", bj);//设置登陆的标识  用于区分android和ios pc  weixin
		UsernamePasswordToken token=new UsernamePasswordToken(user.getId_(), CryptographyUtil.md5(user.getPwd_(), "chenhao"));
		
		//判断 是否支持电脑登陆
		User t_User = userService.findById_(user.getId_());
		if(t_User==null){
			attr.addAttribute("errorInfo", "用户名或者密码错误！");  
			return "redirect:/login";
		}
		
		if(t_User.getEmail_().equals("1")){
			//支持电脑登陆
		}else{
			//不支持电脑登陆
			attr.addAttribute("errorInfo", "不支持电脑登陆");  
			return "redirect:/login";
		}
		
		try{
			subject.login(token); // 登录验证
			user = userService.findById_(user.getId_());
			userService.setGroups(user);
			
			subject.getSession().setAttribute("currentUser", user); 
			
			//return "go_home";
			return "redirect:/admin/main";
			
			//如果登陆成功 就不会报错  报错就是登陆失败了
			//return "redirect:/admin/main.jsp";//地址栏发生变化了
		}catch(Exception e){
			e.printStackTrace();
			
			attr.addAttribute("errorInfo", "用户名或者密码错误！");  
			return "redirect:/login";
		}
	}
	
	
	/**
	 * 用户管理页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "用户管理");
		mav.addObject("title", "用户管理");
		mav.setViewName("admin/page/user/user_manage");
		return mav;
	}
	
	
	
	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/user/add");
		
		mav.setViewName("/admin/page/user/add_or_update");
		return mav;
	}
	
	
	/**
	 * 
	 * @param id
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		User user = userService.findById_(id);
		
		mav.addObject("user", user);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/user/update");
		
		mav.setViewName("/admin/page/user/add_or_update");
		return mav;
	}
	
	
	
	
	@RequestMapping("/setPersm")
	public ModelAndView setPersm(@RequestParam(value = "id", required = false) String id,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		//如果id有值就是 更新 如果没有值  就是添加
		ModelAndView mav = new ModelAndView();
		mav.addObject("userId", id);
		mav.setViewName("admin/page/user/set_persm");
		return mav;
	}
	
	
	
	///user/pc/setGroup?id='+id
	@RequestMapping("/setGroup")
	public ModelAndView setGroup(@RequestParam(value = "id", required = false) String id,HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		//如果id有值就是 更新 如果没有值  就是添加
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Group> groupList = groupService.list(map );
		mav.addObject("groupList", groupList);
		
		//用户已拥有的权限
		List<Group> groupSelectedList = groupService.listByUserId(id);
		
		StringBuffer groups=new StringBuffer();
		for(Group g:groupSelectedList){
			groups.append(g.getId_()+",");
		}
		if(groups.length()>0){
			groups.deleteCharAt(groups.length()-1).toString();
		}
		
		mav.addObject("groupSelectedList",groups);
		
		User user = userService.findById_(id);
		mav.addObject("user", user);
		
		if(user==null){
			return null;
		}
		
		mav.addObject("url", "/admin/ms/update");
		mav.addObject("btn_text", "保存");
		mav.setViewName("admin/page/user/set_group");
		return mav;
	}
	
	/**
	 *   /user/pc/logout
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public String logout()throws Exception{
		SecurityUtils.getSubject().logout(); //shiro的退出
		return "redirect:/login";
	}
	
	
	/**
	 * /user/pc/modify_ps?id=1  修改密码的控制器
	 * @param id
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modify_ps")
	public ModelAndView modify_ps(String id,HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		//如果id有值就是 更新 如果没有值  就是添加
		ModelAndView mav = new ModelAndView();
		
		User user  =  userService.findById_(id);
		mav.addObject("user", user);
		
		mav.setViewName("admin/page/user/modify_ps");
		return mav;
	}
	
	
	/**
	 * 
	 * @param id为userid
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/set_fendian")
	public ModelAndView set_fendian(@RequestParam(value = "id", required = false) String id,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isUse", 1);
		
		List<FenDian> fendianList = fenDianService.list(map );
		
		User user = userService.findById_(id);
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("fendianId", user.getWeiXinUserInfo().getFendianId());
		mav.addObject("userId", id);
		mav.addObject("url", "/admin/wxuser/update");
		mav.addObject("fendianList", fendianList);
		mav.setViewName("admin/page/user/set_fendian");
		return mav;
	}
	
	
	
	
}
