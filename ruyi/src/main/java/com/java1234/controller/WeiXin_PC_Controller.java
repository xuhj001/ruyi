package com.java1234.controller;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.java1234.service.PublicService;


@Controller
@RequestMapping("/wx/pc")
public class WeiXin_PC_Controller {
	
	@Resource
	private PublicService publicService;
	
	
	@RequestMapping("/refresh_token")
	public ModelAndView refresh_token() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "强制刷新token");
		mav.addObject("title", "强制刷新token");
		publicService.addLeftMenu(mav);
		
		mav.addObject("page", "/admin/page/weixin/refresh_token.jsp");
		mav.setViewName("/admin/main");
		return mav;
	}
	
	
	
}
