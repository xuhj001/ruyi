package com.java1234.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java1234.entity.Config;
import com.java1234.entity.User;
import com.java1234.service.ConfigService;
import com.java1234.service.PublicService;

@Controller
@RequestMapping("/config/pc")
public class Config_PC_Controller {
	
	
	@Resource
	private PublicService publicService;
	@Resource
	private ConfigService configService;
	
	
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "配置管理");
		mav.addObject("title", "配置管理");
		mav.setViewName("/admin/page/config/config_manage");
		return mav;
	}
	
	
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Config config = configService.findById(Integer.parseInt(id));
		
		mav.addObject("config", config);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/config/update");
		
		mav.setViewName("/admin/page/config/edit");
		return mav;
	}
	
	
}
