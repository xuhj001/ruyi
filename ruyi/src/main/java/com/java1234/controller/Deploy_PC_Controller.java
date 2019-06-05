package com.java1234.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java1234.service.PublicService;


@Controller
@RequestMapping("/deploy/pc")
public class Deploy_PC_Controller {
	
	@Resource
	private PublicService publicService;
	
	
	
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "流程部署");
		mav.addObject("title", "流程部署");
		
		mav.setViewName("/admin/page/deploy/deploy_manage");
		return mav;
	}
	
	
	@RequestMapping("/deploy")
	public ModelAndView addClientConn(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("url", "/admin/deploy/deploy");
		mav.setViewName("admin/page/deploy/deploy");
		return mav;
	}
	
	
	
	
}



