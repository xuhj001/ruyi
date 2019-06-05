package com.java1234.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java1234.entity.RenWu;
import com.java1234.service.FenDianService;
import com.java1234.service.RenWuService;

@Controller
@RequestMapping("/map/pc")
public class Map_PC_Controller {
	
	@Resource
	private RenWuService renWuService;
	
	
	
	/**
	 * 打开 地图并且显示   我们的坐标
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/open_map")
	public ModelAndView open_map(@RequestParam(value="renwuId",required=false)String renwuId,
			HttpServletRequest request) throws Exception {
		RenWu  renwu = renWuService.findById(Integer.parseInt(renwuId));
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("renwu",renwu);
		mav.setViewName("/admin/page/map/select_point_page");
		return mav;
	}
	
	
}


