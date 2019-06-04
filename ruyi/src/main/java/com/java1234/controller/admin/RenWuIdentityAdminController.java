package com.java1234.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.bridge.MessageWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.java1234.entity.PageBean;
import com.java1234.entity.RenWu;
import com.java1234.entity.RenWuIdentity;
import com.java1234.service.RenWuIdentityService;
import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;


@Controller
@RequestMapping("/admin/rwidtt")
public class RenWuIdentityAdminController {
	
	
	@Resource
	private RenWuIdentityService  rwidttService;
	
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,
			@RequestParam(value="createUserId",required=false)String createUserId,
			@RequestParam(value="processDefinitionKey",required=false)String processDefinitionKey,
			@RequestParam(value="fendianId",required=false)String fendianId,
			HttpServletResponse response)throws Exception{
		
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("userId", createUserId);
		map.put("processDefinitionKey", processDefinitionKey);
		map.put("fendianId", fendianId);
		
		List<RenWuIdentity> list=rwidttService.list(map);
		
		Gson gson  = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		ResponseUtil.write(response, gson.toJson(list));
		return null;
		
	}
	
	
	
}
