package com.java1234.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java1234.entity.JiXing;
import com.java1234.entity.ShuiYuan;
import com.java1234.service.JiXingService;
import com.java1234.service.ShuiYuanService;

@Controller
@RequestMapping("/shuiyuan/pc")
public class ShuiYuan_PC_Controller {
	
	@Resource
	private ShuiYuanService shuiYuanService;
	
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "水源管理");
		mav.addObject("title", "水源管理");
		mav.setViewName("/admin/page/shuiyuan/shuiyuan_manage");
		return mav;
	}
	
	
	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/shuiyuan/add");
		mav.setViewName("/admin/page/shuiyuan/add_or_update");
		return mav;
	}
	
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		ShuiYuan shuiyuan   = shuiYuanService.findById(Integer.parseInt(id));
		
		mav.addObject("shuiyuan", shuiyuan);
		
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/shuiyuan/update?id="+id);
		
		mav.setViewName("/admin/page/shuiyuan/add_or_update");
		return mav;
	}
	
	
	
	
}
