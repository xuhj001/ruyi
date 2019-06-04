package com.java1234.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.java1234.entity.Config;
import com.java1234.entity.FenDian;
import com.java1234.entity.InstallPos;
import com.java1234.entity.JiXing;
import com.java1234.entity.ShuiYuan;
import com.java1234.entity.User;
import com.java1234.entity.XiaoShou;
import com.java1234.service.ConfigService;
import com.java1234.service.FenDianService;
import com.java1234.service.InstallPosService;
import com.java1234.service.JiXingService;
import com.java1234.service.PublicService;
import com.java1234.service.ShuiYuanService;
import com.java1234.service.UserService;
import com.java1234.service.XiaoShouService;
import com.java1234.util.DateUtil;

@Controller
@RequestMapping("/xiaoshou/pc")
public class XiaoShou_PC_Controller {
	
	@Resource
	private PublicService publicService;
	@Resource
	private UserService userService;
	@Resource
	private XiaoShouService  xiaoShouService;
	@Resource
	private JiXingService  jiXingService;
	@Resource
	private ShuiYuanService shuiYuanService;
	@Resource
	private InstallPosService installPosService;
	@Resource
	private FenDianService fenDianService;
	@Resource
	private ConfigService configService;
	
	/**
	 * 用户管理页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/manage")
	public ModelAndView manage(@RequestParam(value="fendian",required=false)String fendian) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "销售管理");
		
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rev_", 2);
		List<User> userList = userService.list(map);
		mav.addObject("userList", userList);
		
		List<JiXing> jixingList = jiXingService.list(map );
		mav.addObject("jixingList", jixingList);
		
		List<ShuiYuan> shuiyuanList = shuiYuanService.list(map);
		mav.addObject("shuiyuanList", shuiyuanList);
		
		List<InstallPos> installPosList= installPosService.list(map);
		mav.addObject("installPosList", installPosList);
		
		List<FenDian> fendianList = fenDianService.list(map);
		mav.addObject("fendianList", fendianList);
		
		if(fendian!=null){
			mav.addObject("fendian", currentUser.getWeiXinUserInfo().getFendian());
		}
		
		Config config = configService.findById(1);
		String date = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
		date = DateUtil.addDay("yyyy-MM-dd", date, (0-config.getDays()));
		mav.addObject("last_change_xin_dataTime", date);
		//查的话，就查比这个日期小的  单子
		
		mav.setViewName("/admin/page/xiaoshou/xiaoshou_manage");
		return mav;
	}
	
	
	
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		XiaoShou xiaoshou    = xiaoShouService.findById(Integer.parseInt(id));
		mav.addObject("xiaoshou", xiaoshou);
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<JiXing> jixingList = jiXingService.list(map );
		mav.addObject("jixingList", jixingList);
		
		List<ShuiYuan> shuiyuanList = shuiYuanService.list(map);
		mav.addObject("shuiyuanList", shuiyuanList);
		
		List<InstallPos> installPosList= installPosService.list(map);
		mav.addObject("installPosList", installPosList);
		
		mav.addObject("save_url", "/admin/xiaoshou/update?id="+id);
		mav.setViewName("/admin/page/xiaoshou/edit");
		
		return mav;
	}
	
	
	
	
}
