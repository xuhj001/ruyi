package com.java1234.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java1234.entity.Group;
import com.java1234.entity.User;
import com.java1234.service.GroupService;
import com.java1234.service.PublicService;

@Controller
@RequestMapping("/group/pc")
public class Group_PC_Controller {

	@Resource
	private GroupService groupService;
	
	@Resource
	private PublicService publicService;
	
	
	/**
	 * @url  /group/pc/manage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "部门&组 管理");
		mav.addObject("title", "部门&组 管理");
		
		mav.setViewName("/admin/page/group/group_manage");
		return mav;
	}
	
	
	
	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/group/add");
		
		mav.setViewName("/admin/page/group/add_or_update");
		return mav;
	}
	
	
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Group group = groupService.findById_(id);
		
		mav.addObject("group", group);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/group/update");
		
		mav.setViewName("/admin/page/group/add_or_update");
		return mav;
	}
	
	
	
	
	
	
}
