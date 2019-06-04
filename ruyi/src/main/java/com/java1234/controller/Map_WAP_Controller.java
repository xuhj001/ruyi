package com.java1234.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.java1234.entity.User;

@Controller
@RequestMapping("/map/wap")
public class Map_WAP_Controller {
	
	/**
	 * ��ת��ѡ����������ҳ�档�ٶȵ�ͼ
	 * 
	 *    /map/wap/select_point
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/select_point")
	public ModelAndView select_point(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("mui_title", "��ͼѡ��");
		mav.setViewName("/foreground/map/select_point_page");
		return mav;
	}
	
	
}
