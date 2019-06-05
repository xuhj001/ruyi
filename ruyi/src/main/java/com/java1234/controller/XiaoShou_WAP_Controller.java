package com.java1234.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java1234.entity.InstallPos;
import com.java1234.entity.JiXing;
import com.java1234.entity.ShuiYuan;
import com.java1234.entity.User;
import com.java1234.service.InstallPosService;
import com.java1234.service.JiXingService;
import com.java1234.service.ShuiYuanService;
import com.java1234.service.UserService;


@Controller
@RequestMapping("/xiaoshou/wap")
public class XiaoShou_WAP_Controller {
	
	@Resource
	private JiXingService  jiXingService;
	@Resource
	private ShuiYuanService  shuiYuanService;
	@Resource
	private InstallPosService  installPosService;
	
	
	
	
	@RequestMapping("/create")
	public ModelAndView create(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("title", "创建销售");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isUse", 1);
		
		List<JiXing> jixings  = jiXingService.list(map );
		List<ShuiYuan> shuiyuans = shuiYuanService.list(map);
		List<InstallPos> inspos = installPosService.list(map);
		
		mav.addObject("inspos",inspos);
		mav.addObject("jixings",jixings);
		mav.addObject("shuiyuans",shuiyuans);
		
		mav.addObject("page", "foreground/xiaoshou/create.jsp");
		
		mav.setViewName("mobile_main");
		return mav;
	}
	
	
	
	
}
