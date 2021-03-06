package com.java1234.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.google.gson.Gson;
import com.java1234.entity.FenDian;
import com.java1234.entity.PageBean;
import com.java1234.entity.Result;
import com.java1234.service.FenDianService;
import com.java1234.util.ResponseUtil;



@Controller
@RequestMapping("/admin/fendian")
public class FenDianAdminController {
	
	@Resource
	private FenDianService fenDianService;
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page
			,@RequestParam(value="rows",required=false)String rows
			,@RequestParam(value="isUse",required=false)String isUse
			,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("isUse", isUse);
		List<FenDian> list=fenDianService.list(map);
		Integer total=fenDianService.getTotal(map);
		map.clear();
		Gson gson = new Gson();
		map.put("rows", list);
		map.put("total", total);
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	
	
	
	@RequestMapping("/add")
	public String add(FenDian fendian ,HttpServletResponse response,Integer flag)throws Exception{
		int resultTotal=0;
		resultTotal=fenDianService.add(fendian);
		Result result=new Result();
		Gson g = new Gson();
		if(resultTotal>0){
			result.setSuccess(true); 
			result.setMsg("添加成功");
		}else{
			result.setSuccess(false); 
			result.setMsg("添加失败");
		}
		ResponseUtil.write(response, g.toJson(result));
		return null;
	}
	
	
	
	@RequestMapping("/update")
	public String update(FenDian fendian ,HttpServletResponse response,Integer flag)throws Exception{
		int resultTotal=0;
		resultTotal=fenDianService.update(fendian);
		Result result=new Result();
		Gson g = new Gson();
		if(resultTotal>0){
			result.setSuccess(true); 
			result.setMsg("修改成功");
		}else{
			result.setSuccess(false); 
			result.setMsg("修改失败");
		}
		ResponseUtil.write(response, g.toJson(result));
		return null;
	}
	
	
	
	
	
}
