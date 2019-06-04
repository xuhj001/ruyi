package com.java1234.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.google.gson.Gson;
import com.java1234.entity.PageBean;
import com.java1234.entity.Result;
import com.java1234.entity.User;
import com.java1234.util.DateUtil;
import com.java1234.util.ResponseUtil;

@Controller
@RequestMapping("/public")
public class PublicController {

	
	
	/**
	 * 接受时间 格式 返回时间 
	 * @param 
	 * @param response
	 * @return
	 * @throws Exception /public/getTime.do  format
	 */
	@RequestMapping("/getTime")
	public String getTime(String format,HttpServletResponse response)throws Exception{
		
		String time = DateUtil.formatDate(new Date(), format);
		Result result = new Result();
		result.setMsg(time);
		Gson gson = new Gson();
		ResponseUtil.write(response,gson.toJson(result));
		return null;
	}
	
	
	
	
}
