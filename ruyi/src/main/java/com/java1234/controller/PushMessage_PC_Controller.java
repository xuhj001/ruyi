package com.java1234.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.java1234.entity.Result;
import com.java1234.entity.User;
import com.java1234.service.PublicService;
import com.java1234.service.UserService;
import com.java1234.util.ResponseUtil;

@Controller
@RequestMapping("/pushmsg/pc")
public class PushMessage_PC_Controller {
	
	@Resource
	private UserService userService;
	
	
	@RequestMapping("/push")
	public ModelAndView edit(@RequestParam(value="ids",required=true)String ids
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		String about = "";
		
		User user = null;
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			user = userService.findById_(idsStr[i]);
			about = about+user.getFirst_()+",";//设置相关人员
		}
		
		//about 删除最后一个逗号
		about =about.substring(0, about.length()-1);
		
		mav.addObject("ids", ids);
		mav.addObject("about", about);
		
		mav.setViewName("/admin/page/pushmsg/push");
		return mav;
	}
	
	
	
}
