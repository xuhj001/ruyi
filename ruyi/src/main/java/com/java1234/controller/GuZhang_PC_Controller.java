package com.java1234.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.java1234.entity.GuZhang;
import com.java1234.service.GuZhangService;



@Controller
@RequestMapping("/guzhang/pc")
public class GuZhang_PC_Controller {
	
	
	@Resource
	private GuZhangService guZhangService;
	
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "故障管理");
		mav.addObject("title", "故障管理");
		
		mav.setViewName("/admin/page/guzhang/guzhang_manage");
		return mav;
	}
	
	
	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/guzhang/add");
		mav.setViewName("/admin/page/guzhang/add_or_update");
		return mav;
	}
	
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		GuZhang guzhang  = guZhangService.findById(Integer.parseInt(id));
		
		mav.addObject("guzhang", guzhang);
		
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/guzhang/update?id="+id);
		
		mav.setViewName("/admin/page/guzhang/add_or_update");
		return mav;
	}
	
	
	
}
