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
	 * @param ����ӿ������ڲ��û�ʹ�õģ��������Լ������Լ���openid
	 * @param state
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/set_openid")
	public ModelAndView set_openid(
			@RequestParam(value="code",required=false)String code,
			@RequestParam(value="state",required=false)String state,//���stateŪ���Ƽ��˵�id
			HttpServletResponse response)throws Exception{
			
			ModelAndView mav = new ModelAndView();
			
			Gson gson = new Gson();
			
			String accessToken_openid_json = "";
			if(StringUtil.isNotEmpty(code)){
				accessToken_openid_json = HttpClient.get(WeiXinUtil.getOpenidUrl(code));
			}else{
				//��ת������ҳ��   �����û���ת ��������Ȩҳ��
				return null;
			}
			
			
			JSONObject accessToken_openid_obj = JSONObject.fromObject(accessToken_openid_json);
			
			
			if(accessToken_openid_obj.getString("access_token")==null){
				//��ת��ҳ��ҳ�� ��Ȩ
			}
			
			
			WeiXinUserInfo wxuserinfo = null;
			//ʹ��access_token��openid ���û�����
			String WXUserInfo_json  = HttpClient.get(WeiXinUtil.getWXUserInfoUrl(accessToken_openid_obj));
			wxuserinfo = gson.fromJson(WXUserInfo_json, WeiXinUserInfo.class);
			//���������base��Ȩ�Ļ���wxuserinfo�����ݻ���null 
			
			
			mav.addObject("title","��openid");
			mav.addObject("mui_title","��openid");
			
			mav.addObject("wxuserinfo",wxuserinfo);
			mav.setViewName("/foreground/user/set_openid");
			
			return mav;
	}
	
	
	
}
