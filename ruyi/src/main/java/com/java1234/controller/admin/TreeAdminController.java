package com.java1234.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.google.gson.Gson;
import com.java1234.entity.Result;
import com.java1234.entity.User;
import com.java1234.service.TreeService;
import com.java1234.service.UserService;
import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;

@Controller
@RequestMapping("/admin/tree")
public class TreeAdminController {

	@Resource
	private TreeService treeService;
	
	@Resource
	private UserService userService;
	
	
	
	/**
	 * 用户的id【openid】  更新菜单
	 * 更新 用户的树
	 */
	@RequestMapping("/update")
	public String update(
			@RequestParam(value="id",required=false)String id,
			@RequestParam(value="treeIds",required=false)String treeIds,
			HttpServletRequest requset,HttpServletResponse response)throws Exception{
	 
		return null ;
	}
	
	
}
