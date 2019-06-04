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
import com.java1234.entity.PageBean;
import com.java1234.entity.Result;
import com.java1234.entity.ShowPhone;
import com.java1234.service.ShowPhoneService;
import com.java1234.util.ResponseUtil;

@Controller
@RequestMapping("/admin/showPhone")
public class ShowPhoneAdminController {

	
	@Resource
	private ShowPhoneService showPhoneService   ;
	
	
	/**
	 * 
	 * @param type （完成1，接受2）
	 * @param procdefId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,
			String type,String procdefId, HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		
		map.put("type", type);
		map.put("procdefId", procdefId);
		List<ShowPhone> showPhones  =showPhoneService.list(map);
		
		map.clear();
		Gson gson = new Gson();
		map.put("rows", showPhones);
		
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	
	
	
	@RequestMapping("/add")
	public String add(ShowPhone showPhone   ,HttpServletResponse response,Integer flag)throws Exception{
		int resultTotal=0;
		resultTotal=showPhoneService.add(showPhone);
		
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
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(String id,HttpServletResponse response)throws Exception{
		showPhoneService.delete(Integer.parseInt(id));
		Gson g = new Gson();
		Result result=new Result();
		result.setSuccess(true);
		result.setMsg("删除成功.");
		ResponseUtil.write(response, g.toJson(result));
		return null;
	}
	
	
	
	
	
	
}
