package com.java1234.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java1234.service.PublicService;

@Controller
@RequestMapping("/procedef/pc")
public class Procedef__PC_Controller {
	@Resource
	private PublicService publicService;
	
	
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "流程定义管理");
		mav.addObject("title", "流程定义管理");
		
		mav.setViewName("/admin/page/procedef/procedef_manage");
		return mav;
	}
	
	
	
}
