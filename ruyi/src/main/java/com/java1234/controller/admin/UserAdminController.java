package com.java1234.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.aspectj.weaver.patterns.WildAnnotationTypePattern;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.java1234.entity.Group;
import com.java1234.entity.PageBean;
import com.java1234.entity.Result;
import com.java1234.entity.User;
import com.java1234.entity.WeiXinUserInfo;
import com.java1234.service.GroupService;
import com.java1234.service.UserService;
import com.java1234.service.WeiXinUserInfoService;
import com.java1234.util.CryptographyUtil;
import com.java1234.util.DateUtil;
import com.java1234.util.MyUtil;
import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;


@Controller
@RequestMapping("/admin/user")
public class UserAdminController {
	
	
	@Resource
	private GroupService groupService;
	@Resource
	private UserService userService;
	@Resource
	private WeiXinUserInfoService weiXinUserInfoService;
	
	
	
	@RequestMapping("/add")
	public String add(User user, HttpServletResponse response, HttpServletRequest request) throws Exception {
		user.setRev_(2);
		user.setPicture_id_(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		user.setPwd_(CryptographyUtil.md5(user.getPwd_(),  "chenhao"));
		int resultTotal = userService.add(user);
		if(resultTotal>0){
			WeiXinUserInfo wxuserinfo = new WeiXinUserInfo();
			wxuserinfo.setOpenid(user.getId_());
			wxuserinfo.setCreateDateTime(new Date());
			wxuserinfo.setDaka_count(1);
			wxuserinfo.setTreeIds("");//权限不能是null  设置个空str
			weiXinUserInfoService.add(wxuserinfo);
		}
		Result result = new Result();
		Gson gson = new Gson();
		if (resultTotal > 0) {
			result.setSuccess(true);
			result.setMsg("添加成功");
		} else {
			result.setSuccess(false);
			result.setMsg("添加失败");
		}
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	
	
	@RequestMapping("/update")
	public String update(User user,HttpServletResponse response)throws Exception{
		int resultTotal=0;
		resultTotal=userService.update(user);
		Result result=new Result();
		Gson g = new Gson();
		if(resultTotal>0){
			result.setSuccess(true); 
			result.setMsg("资料修改成功");
		}else{
			result.setSuccess(false); 
			result.setMsg("资料修改失败");
		}
		ResponseUtil.write(response, g.toJson(result));
		return null;
	}
	
	
	/**
	 * 删除用户
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			userService.delete(idsStr[i]);
		}
		
		Gson g = new Gson();
		Result result=new Result();
		result.setSuccess(true);
		result.setMsg("删除成功.");
		ResponseUtil.write(response, g.toJson(result));
		
		return null;
	}
	
	
	
	/**
	 * 显示角色和权限
	 * @param page
	 * @param rows
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listWithGroups")
	public String listWithGroups(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,
			@RequestParam(value="rev_",required=false)String rev_,
			@RequestParam(value="q",required=false)String q,
			 HttpServletResponse response)throws Exception{
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("rev_", rev_);
		map.put("q", StringUtil.formatLike(q));
		List<User> userList=userService.list(map);
		for(User user:userList){
			StringBuffer groups=new StringBuffer();
			List<Group> groupList=groupService.listByUserId(user.getId_());
			for(Group g:groupList){
				groups.append(g.getName_()+",");
			}
			if(groups.length()>0){
				//删除最后一个豆号
				user.setGroups(groups.deleteCharAt(groups.length()-1).toString());
			}else{
				user.setGroups(groups.toString());
			}
		}
		Long total=userService.getTotal(map);
		
		map.clear();
		Gson gson = new Gson();
		map.put("rows", userList);
		map.put("total", total);
		ResponseUtil.write(response, gson.toJson(map));
		
		return null;
	}
	
	
	
	/**
	 * /admin/user/set_trueOpenid
	 * 为内部人员设置  真实的openid
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/set_trueOpenid")
	public String set_trueOpenid(String id_,String openid,HttpServletResponse response)throws Exception{
		int resultTotal=0;
		User user = userService.findById_(id_);
		WeiXinUserInfo wxinfo = user.getWeiXinUserInfo();
		wxinfo.setTrueOpenid(openid);
		resultTotal = weiXinUserInfoService.update(wxinfo);
		
		Result result=new Result();
		Gson g = new Gson();
		if(resultTotal>0){
			result.setSuccess(true); 
			result.setMsg("资料修改成功");
		}else{
			result.setSuccess(false); 
			result.setMsg("资料修改失败");
		}
		ResponseUtil.write(response, g.toJson(result));
		return null;
	}
	
	

	@RequestMapping("/modify_ps")
	public String modify_ps(User user,HttpServletResponse response,Integer flag)throws Exception{
		int resultTotal=0;
		user.setPwd_(CryptographyUtil.md5(user.getPwd_(), "chenhao"));
		
		resultTotal=userService.update(user);
		Result result=new Result();
		Gson g = new Gson();
		if(resultTotal>0){
			result.setSuccess(true); 
			result.setMsg("密码修改成功");
		}else{
			result.setSuccess(false); 
			result.setMsg("密码修改失败");
		}
		ResponseUtil.write(response, g.toJson(result));
		return null;
	}
	
	

}
