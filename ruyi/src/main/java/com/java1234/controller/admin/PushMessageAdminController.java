package com.java1234.controller.admin;


import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;
import com.google.gson.Gson;
import com.java1234.entity.Access_token;
import com.java1234.entity.Result;
import com.java1234.entity.User;
import com.java1234.http.HttpClient;
import com.java1234.service.UserService;
import com.java1234.service.WeiXinMSGService;
import com.java1234.util.ResponseUtil;
import com.java1234.weixin.WeiXinUtil;



@Controller
@RequestMapping("/admin/pushmsg")
public class PushMessageAdminController {
	
	@Resource
	private UserService userService;
	@Resource
	private WeiXinMSGService weiXinMSGService;
	
	
	@RequestMapping("/push")
	public String push(@RequestParam(value="ids",required=false)String ids,
			@RequestParam(value="first",required=false)String first,
			@RequestParam(value="rw_name",required=false)String rw_name,
			@RequestParam(value="about",required=false)String about,
			HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		User user = null;
		
		
		for(int i=0;i<idsStr.length;i++){
			user = userService.findById_(idsStr[i]);
			if(user.getWeiXinUserInfo().getTrueOpenid()!=null){
				weiXinMSGService.push_sms(user.getWeiXinUserInfo().getTrueOpenid(), first, rw_name, about);
			}
		}
		
		
		Gson g = new Gson();
		Result result=new Result();
		result.setSuccess(true);
		result.setMsg("ÍÆËÍ³É¹¦.");
		ResponseUtil.write(response, g.toJson(result));
		
		return null;
	}
	
	
}
