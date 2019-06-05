package com.java1234.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java1234.entity.InstallPos;
import com.java1234.service.GroupService;
import com.java1234.service.InstallPosService;


@Controller
@RequestMapping("/installpos/pc")
public class InstallPos_PC_Controller {
	
	@Resource
	private InstallPosService installPosService;
	
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "安装位置管理");
		mav.addObject("title", "安装位置管理");
		
		mav.setViewName("/admin/page/install_pos/install_pos_manage");
		return mav;
	}
	
	
	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/installpos/add");
		mav.setViewName("/admin/page/install_pos/add_or_update");
		return mav;
	}
	
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		InstallPos installPos = installPosService.findById(Integer.parseInt(id));
		
		mav.addObject("installPos", installPos);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/installpos/update?id="+id);
		
		mav.setViewName("/admin/page/install_pos/add_or_update");
		return mav;
	}
	
	
	
}
