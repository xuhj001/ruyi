package com.java1234.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.java1234.entity.Result;
import com.java1234.entity.User;
import com.java1234.entity.WeiXinUserInfo;
import com.java1234.service.GroupService;
import com.java1234.service.WeiXinUserInfoService;
import com.java1234.util.ResponseUtil;

@Controller
@RequestMapping("/admin/wxuser")
public class WXUserAdminController {

	@Resource
	private WeiXinUserInfoService weiXinUserInfoService;
	
	/**
	 * /admin/wxuser/update
	 * @param weiXinUserInfo
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update")
	public String update(WeiXinUserInfo weiXinUserInfo ,HttpServletResponse response)throws Exception{
		int resultTotal=0;
		resultTotal=weiXinUserInfoService.update(weiXinUserInfo);
		Result result=new Result();
		Gson g = new Gson();
		if(resultTotal>0){
			result.setSuccess(true); 
			result.setMsg("资料修改成功");
		}else{
			result.setSuccess(false); 
			result.setMsg("资料修改失败");
		}
		ResponseUtil.write(response, g.toJson(result));
		return null;
	}
	
	
	
	
}
