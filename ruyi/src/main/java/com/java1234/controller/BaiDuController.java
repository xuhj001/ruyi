package com.java1234.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.google.gson.Gson;
import com.java1234.http.HttpClient;
import com.java1234.util.ResponseUtil;

@Controller
@RequestMapping("/baidu")
public class BaiDuController {
	
	
	@RequestMapping("/format_point")
	public String format( String longitude,String latitude,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		String result_json = HttpClient.get("http://api.map.baidu.com/geoconv/v1/?coords="+longitude+","+latitude+"&from=1&to=5&ak=C356fa79e683d3234f089c5dfd5fda2f");
		
		JSONObject jsonObject  =   new JSONObject(result_json);
		JSONArray r =  jsonObject.getJSONArray("result");
		JSONObject result = new JSONObject();
		
		for(int i=0;i<r.length();i++){
			JSONObject j = (JSONObject) r.get(i);
			result.put("x", j.get("x").toString()) ;
			result.put("y", j.get("y").toString()) ;
		}
		
		ResponseUtil.write(response,result);
		return null;
	}
	
	
}
