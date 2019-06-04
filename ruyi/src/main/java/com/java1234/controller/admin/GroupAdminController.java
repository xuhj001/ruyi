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
import com.java1234.entity.Group;
import com.java1234.entity.PageBean;
import com.java1234.entity.Result;
import com.java1234.service.GroupService;
import com.java1234.util.ResponseUtil;


@Controller
@RequestMapping("/admin/group")
public class GroupAdminController {

	@Resource
	private GroupService groupService;
	
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page
			,@RequestParam(value="rows",required=false)String rows
			,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Group> list=groupService.list(map);
		Long total=groupService.getTotal(map);
		map.clear();
		Gson gson = new Gson();
		map.put("rows", list);
		map.put("total", total);
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	
	
	
	@RequestMapping("/add")
	public String add(Group group,HttpServletResponse response,Integer flag)throws Exception{
		int resultTotal=0;
		resultTotal=groupService.add(group);
		Result result=new Result();
		Gson g = new Gson();
		if(resultTotal>0){
			result.setSuccess(true); 
			result.setMsg("添加成功.");
		}else{
			result.setSuccess(false); 
			result.setMsg("添加失败.");
		}
		ResponseUtil.write(response, g.toJson(result));
		return null;
	}
	
	

	@RequestMapping("/update")
	public String update(Group group,HttpServletResponse response,Integer flag)throws Exception{
		int resultTotal=0;
		resultTotal=groupService.update(group);
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
	
	
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			groupService.delete(idsStr[i]);
		}
		Gson g = new Gson();
		Result result=new Result();
		result.setSuccess(true);
		result.setMsg("删除成功.");
		ResponseUtil.write(response, g.toJson(result));
		
		return null;
	}
	
	
	@RequestMapping("/listByUserId")
	public String listByUserId(@RequestParam(value="userId",required=false)String userId,HttpServletResponse response)throws Exception{
		List<Group> groupList=groupService.listByUserId(userId);
		StringBuffer groups=new StringBuffer();
		for(Group g:groupList){
			groups.append(g.getId_()+",");
		}
		if(groups.length()>0){
			groups.deleteCharAt(groups.length()-1).toString();
		}
		
		ResponseUtil.write(response, groups);
		return null;
	}
	
	
	
}
