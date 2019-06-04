package com.java1234.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.java1234.entity.UserTask;
import com.java1234.service.PublicService;



@Controller
@RequestMapping("/showPhone/pc")
public class ShowPhone_PC_Controller {
	
	
	@Resource
	private PublicService publicService ;
	
	
	@RequestMapping("/manage")
	public ModelAndView manage(String procdefId, HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("procdefId", procdefId);
		mav.setViewName("admin/page/ShowPhone/show_phone_manage");
		return mav;
	}
	
	
	
	@RequestMapping("/add")
	public ModelAndView add(String procdefId, HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> map = new HashMap<String ,Object>();
		map.put("procdefId", procdefId);
		List<UserTask> list = publicService.getAllUserTaskByProcdefId(map);
		
		
		mav.addObject("list", list);
		mav.addObject("procdefId", procdefId);
		mav.setViewName("admin/page/ShowPhone/add");
		return mav;
	}
	
	
	
	
	
}
