package com.java1234.controller.admin;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.google.gson.Gson;
import com.java1234.entity.Config;
import com.java1234.entity.Result;
import com.java1234.service.ConfigService;
import com.java1234.util.ResponseUtil;

@Controller
@RequestMapping("/admin/config")
public class ConfigAdminController {
	
	
	@Resource
	private ConfigService configService;
	
	
	@RequestMapping("/update")
	public String update(Config config,HttpServletResponse response,Integer flag)throws Exception{
		int resultTotal=0;
		resultTotal=configService.update(config);
		Result result=new Result();
		Gson g = new Gson();
		if(resultTotal>0){
			result.setSuccess(true); 
			result.setMsg("修改成功.");
		}else{
			result.setSuccess(false); 
			result.setMsg("修改失败.");
		}
		ResponseUtil.write(response, g.toJson(result));
		return null;
	}
	
	
	
	@RequestMapping("/findById")
	public String list(@RequestParam(value="id",required=false)String id,HttpServletResponse response)throws Exception{
		Config config = configService.findById(Integer.parseInt(id));
		Gson gson = new Gson();
		ResponseUtil.write(response, gson.toJson(config));
		return null;
	}
	
	
	
	
	
	
}
