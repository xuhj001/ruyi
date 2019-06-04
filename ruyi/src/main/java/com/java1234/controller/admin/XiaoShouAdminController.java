package com.java1234.controller.admin;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.java1234.entity.Result;
import com.java1234.entity.XiaoShou;
import com.java1234.service.XiaoShouService;
import com.java1234.util.ResponseUtil;

@Controller
@RequestMapping("/admin/xiaoshou")
public class XiaoShouAdminController {
	
	@Resource
	private XiaoShouService   xiaoShouService;
	
	/**
	 * 
	 *   /admin/xiaoshou/add
	 * @param xiaoShou
	 * @param response
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add")
	public String add(XiaoShou xiaoShou  ,HttpServletResponse response,Integer flag)throws Exception{
		int resultTotal=0;
		resultTotal=xiaoShouService.add(xiaoShou);
		
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
	
	/**
	 *    /admin/xiaoshou/update
	 * @param xiaoShou
	 * @param response
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update")
	public String update(XiaoShou xiaoShou  ,HttpServletResponse response)throws Exception{
		int resultTotal=0;
		resultTotal=xiaoShouService.update(xiaoShou);
		
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
	
	
	/**
	 *  /admin/xiaoshou/findById
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findById")
	public String findById(@RequestParam(value="id",required=false)String id  ,HttpServletResponse response)throws Exception{
		XiaoShou xiaoshou = xiaoShouService.findById(Integer.parseInt(id));
		Gson g = new Gson();
		ResponseUtil.write(response, g.toJson(xiaoshou));
		return null;
	}
	
	
	
}
