package com.java1234.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.java1234.entity.Result;
import com.java1234.service.MemberShipService;
import com.java1234.util.ResponseUtil;


@Controller
@RequestMapping("/admin/ms")
public class MemberShipAdminController {

	@Resource
	private MemberShipService memberShipService;
	
	/**
	 * 更新用户权限 先删除 后批量添加
	 * @param userId
	 * @param groupsIds
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update")
	public String update(String userId,String groupsIds,HttpServletResponse response)throws Exception{
		int i = memberShipService.update(userId, groupsIds);
		Result result=new Result();
		Gson gson = new Gson();
		
		if(i>0){
			result.setSuccess(true);
			result.setMsg("权限更新成功");
		}else{
			result.setSuccess(false);
			result.setMsg("权限更新失败");
		}
	
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	
}
