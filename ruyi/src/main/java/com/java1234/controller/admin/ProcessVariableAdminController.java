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
import com.java1234.entity.ProcessVariable;
import com.java1234.entity.Result;
import com.java1234.service.ProcessVariableService;
import com.java1234.util.ResponseUtil;



@Controller
@RequestMapping("/admin/procevar")
public class ProcessVariableAdminController {
	
	@Resource
	private ProcessVariableService   processVariableService;
	
	
	
	@RequestMapping("/add")
	public String add(ProcessVariable processVariable ,HttpServletResponse response,Integer flag)throws Exception{
		int resultTotal=0;
		resultTotal=processVariableService.add(processVariable);
		
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
	
	
	
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,String procdefId, HttpServletResponse response)throws Exception{
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("procdefId", procdefId);
		List<ProcessVariable> procevarList=processVariableService.list(map);
		
		Gson gson = new Gson();
		map.put("rows", procevarList);
		
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	
	
	
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(String id,HttpServletResponse response)throws Exception{
		processVariableService.delete(Integer.parseInt(id));
		Gson g = new Gson();
		Result result=new Result();
		result.setSuccess(true);
		result.setMsg("删除成功.");
		ResponseUtil.write(response, g.toJson(result));
		return null;
	}
	
	
	
	
	
	
}
