package com.java1234.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.google.gson.Gson;
import com.java1234.entity.Result;
import com.java1234.service.PublicService;
import com.java1234.util.ResponseUtil;

@Controller
@RequestMapping("/admin/wx")
public class WeiXinAdminController {

	
	@Resource
	private PublicService publicService;
	
	/**
	 *  /admin/wx/refresh_token
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/refresh_token")
	public String refresh_token(HttpServletResponse response)throws Exception{
		publicService.refreshToken();
		Result result=new Result();
		Gson g = new Gson();
		result.setSuccess(true); 
		result.setMsg("刷新成功");
		
		ResponseUtil.write(response, g.toJson(result));
		return null;
	}
	
	
}
