package com.java1234.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.java1234.entity.User;
import com.java1234.entity.WeiXinUserInfo;
import com.java1234.http.HttpClient;
import com.java1234.util.CryptographyUtil;
import com.java1234.util.DateUtil;
import com.java1234.util.StringUtil;
import com.java1234.weixin.WeiXinUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/wx/wap")
public class WeiXin_WAP_Controller {
	
	
	/**
	 * /wx/wap/set_openid
	 * @param 这个接口是让内部用户使用的，让他们自己设置自己的openid
	 * @param state
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/set_openid")
	public ModelAndView set_openid(
			@RequestParam(value="code",required=false)String code,
			@RequestParam(value="state",required=false)String state,//这个state弄成推荐人的id
			HttpServletResponse response)throws Exception{
			
			ModelAndView mav = new ModelAndView();
			
			Gson gson = new Gson();
			
			String accessToken_openid_json = "";
			if(StringUtil.isNotEmpty(code)){
				accessToken_openid_json = HttpClient.get(WeiXinUtil.getOpenidUrl(code));
			}else{
				//跳转到错误页面   引导用户跳转 真正的授权页面
				return null;
			}
			
			
			JSONObject accessToken_openid_obj = JSONObject.fromObject(accessToken_openid_json);
			
			
			if(accessToken_openid_obj.getString("access_token")==null){
				//跳转真页的页面 授权
			}
			
			
			WeiXinUserInfo wxuserinfo = null;
			//使用access_token和openid 拿用户资料
			String WXUserInfo_json  = HttpClient.get(WeiXinUtil.getWXUserInfoUrl(accessToken_openid_obj));
			wxuserinfo = gson.fromJson(WXUserInfo_json, WeiXinUserInfo.class);
			//这里如果是base授权的话，wxuserinfo的内容会是null 
			
			
			mav.addObject("title","绑定openid");
			mav.addObject("mui_title","绑定openid");
			
			mav.addObject("wxuserinfo",wxuserinfo);
			mav.setViewName("/foreground/user/set_openid");
			
			return mav;
	}
	
	
	
}
