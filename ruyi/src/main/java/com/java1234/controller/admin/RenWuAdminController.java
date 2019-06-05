package com.java1234.controller.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.ibatis.javassist.expr.NewArray;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.java1234.entity.Group;
import com.java1234.entity.PageBean;
import com.java1234.entity.Result;
import com.java1234.entity.SMSTask;
import com.java1234.entity.RenWu;
import com.java1234.entity.RenWuIdentity;
import com.java1234.entity.User;
import com.java1234.entity.UserTask;
import com.java1234.service.AssignedService;
import com.java1234.service.GroupService;
import com.java1234.service.PublicService;
import com.java1234.service.RenWuIdentityService;
import com.java1234.service.RenWuService;
import com.java1234.service.SMSTaskService;
import com.java1234.service.ShowPhoneService;
import com.java1234.util.DateUtil;
import com.java1234.util.FileUtil;
import com.java1234.util.MyUtil;
import com.java1234.util.ResponseUtil;
import com.java1234.util.SMSTaskUtil;
import com.java1234.util.StringUtil;
import com.java1234.yunpian.YunPianUtil;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/renwu")
public class RenWuAdminController {
	
	@Resource
	private RenWuService  renWuService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private HistoryService historyService;
	@Resource
	private TaskService taskService;
	@Resource
	private GroupService  groupService;
	@Resource
	private PublicService  publicService;
	@Resource
	private AssignedService  assignedService;
	@Resource
	private SMSTaskService  smsTaskService;
	@Resource
	private ShowPhoneService showPhoneService;
	@Resource
	private RenWuIdentityService rwidttService;

	@RequestMapping("/accept")
	public String accept(String taskId ,String taskName ,HttpServletResponse response,Integer flag)throws Exception{
		Result result=new Result();
		Gson gson = new Gson();
		
		//根据任务id拿流程变量   renwuId
		Integer renwuId=(Integer) taskService.getVariable(taskId, "renwuId");
		RenWu renwu  =renWuService.findById(renwuId);
		//判断下  shouhou  是否被锁住 
		if(renwu.getIsLock()==2){
			JSONObject  rest = new JSONObject();
			rest.put("success", false);
			rest.put("msg","已有人接单了，"+"接单人:"+renwu.getAcceptUser().getFirst_());
			ResponseUtil.write(response, rest);
			return null;
		}
		
		renWuService.update_accept(taskId, taskName);
		
		result.setSuccess(true); 
		result.setMsg("添加成功");
		
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	
	
	/**
	 *	
	 * @param file
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addImage")
	public String addImage(@RequestParam("file") MultipartFile file, 
			HttpServletResponse response,HttpServletRequest request)throws Exception{
		
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		
		JSONObject result = new JSONObject();
		
		if(!file.isEmpty()){
			//这个就相当于取得项目路径
			String webPath=request.getServletContext().getRealPath("/");
			//把文件名子换成（时间搓.png）yyyyMMddHHmmss.jpg(imageName)
			String imageName = DateUtil.getCurrentDateStr("yyyyMMddHHmmss")+"."+file.getOriginalFilename().split("\\.")[1];
			String dateStr = DateUtil.formatDate(new Date(), "yyyyMMdd");
			//我希望产生一个日期的文件夹
			String imagePath = "static/images/comment/"+dateStr+"/";
			//调用产生文件夹的方法
			FileUtil.makeDirs(webPath+imagePath);
			//C:\javaSpace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\mayikeji03\【webPath】
			//static/images/comment/20170728/【imagePath】
			System.out.println(webPath+imagePath);
			//保存到路径
			file.transferTo(new File(webPath+imagePath+imageName));
			
			result.put("success", true);
			result.put("url", "/"+imagePath+imageName);
			result.put("msg", "添加图片成功");
			
			ResponseUtil.write(response, result);
			return null;
		}
		return null;
	}
	
	
	
	/**
	 * /admin/renwu/uploader
	 * @param request
	 * @param response
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/uploader")
	public String updata(HttpServletRequest request,  HttpServletResponse response,Integer flag)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
        if(request.getHeader("content-type")!=null&&"application/x-www-form-urlencoded".equals(request.getHeader("content-type"))){
            return null;//不支持断点续传，直接返回null即可
        }
        JSONObject result = new JSONObject();
        
        
		//将请求转换成
        MultipartHttpServletRequest mRequest=(MultipartHttpServletRequest)request;
        Enumeration<String> ps = mRequest.getParameterNames();
        if(ps.hasMoreElements()){
            String hname = ps.nextElement();
            //前端  task.addData('name','file');
            System.out.println(hname);//输出 name
            System.out.println(mRequest.getParameter(hname));//输出 file
        }
        
        
        Iterator<String> fns=mRequest.getFileNames();//获取上传的文件列表
        while(fns.hasNext()){
            String s =fns.next();
            System.out.println(s+"==="+mRequest.getFile(s));
            //System.out.println(mRequest.getFile(s));//get file success!!!!!
            MultipartFile mFile = mRequest.getFile(s);  
            if(mFile.isEmpty()){
                map.put("error", "EventAction.picture.failed");
            }else{
            	//有内容开始存
            	String webPath=request.getServletContext().getRealPath("/");
    			//把文件名子换成（时间搓.png）yyyyMMddHHmmss.jpg(imageName)
            	
    			String imageName =  mFile.getOriginalFilename();
    			
    			String dateStr = DateUtil.formatDate(new Date(), "yyyyMMdd");
    			//我希望产生一个日期的文件夹
    			String imagePath = "static/images/comment/"+dateStr+"/";
    			//调用产生文件夹的方法
    			FileUtil.makeDirs(webPath+imagePath);
    			System.out.println(webPath+imagePath);
    			mFile.transferTo(new File(webPath+imagePath+imageName));
    			result.put("success", true);
    			result.put("url", "/"+imagePath+imageName);
    			result.put("msg", "添加图片成功");
    			
    			ResponseUtil.write(response, result);
    			return null;
            }
        }
		return null;
	}
	
	
	
	
	@RequestMapping("/delImage")
	public String delImage(String url, 
			HttpServletResponse response,HttpServletRequest request)throws Exception{
		
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		
		JSONObject result = new JSONObject();
		
		//这个就相当于取得项目路径
		String webPath=request.getServletContext().getRealPath("/");
		//C:\javaSpace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\mayikeji03\
		System.out.println(webPath+url);
		
		FileUtil.deleteFile(webPath+url);
		
		result.put("success",true );
		result.put("msg", "删除成功");
		ResponseUtil.write(response, result);
		return null;
	}
	
	
	@RequestMapping("/addComment")
	public String addComment(String taskId,String comment,String taskName,
			HttpServletResponse response)throws Exception{
		
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		int i = publicService.addComment(taskId, currentUser, comment,taskName);
		
		//根据任务id拿流程变量  shouhouId
		Integer renwuId=(Integer) taskService.getVariable(taskId, "renwuId");
		RenWu renwu  =renWuService.findById(renwuId);
		
		//添加任务的身份联系人
		rwidttService.add_idt(currentUser, renwu);
		//添加任务的身份联系人
		
		JSONObject result=new JSONObject();
		
		if(i>0){
			result.put("success", true);
			result.put("msg", "添加批注成功");
		}else{
			result.put("success", true);
			result.put("msg", "添加批注失败");
		}
		
		ResponseUtil.write(response, result);
		return null;
	}
	
	
	
	
	@RequestMapping("/done")
	public String done(String taskId,String comment,String taskName,String msg,
			HttpServletResponse response)throws Exception{
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		
		//拿出 售后实例  判断有没有占坑
		Integer renwuId=(Integer) taskService.getVariable(taskId, "renwuId");
		RenWu renwu = renWuService.findById(renwuId);
		
		if(renwu.getAcceptUser()!=null){
			JSONObject result=new JSONObject();
			if ((renwu.getAcceptUser().getId_().equals(currentUser.getId_()))) {
				//占坑 和当前 一样
			}else{
				//如果不是占坑人，提示无权操作
				result.put("success", false);
				result.put("msg", "你不是办理人，无权操作");
				ResponseUtil.write(response, result);
				return null;
			}
		}
		
		
		renWuService.done(taskId, taskName, comment, msg);
		
		
		JSONObject result=new JSONObject();
		result.put("success", true);
		result.put("msg", "完成任务，执行成功");
		
		ResponseUtil.write(response, result);
		return null;
	}
	
	
	
	@RequestMapping("/last_done")
	public String last_done(String taskId,String comment,String taskName,String msg,
			HttpServletResponse response)throws Exception{
		
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		
		//拿出 售后实例  判断有没有占坑
		Integer renwuId=(Integer) taskService.getVariable(taskId, "renwuId");
		RenWu renwu = renWuService.findById(renwuId);
		
		if(renwu.getAcceptUser()!=null){
			JSONObject result=new JSONObject();
			if ((renwu.getAcceptUser().getId_().equals(currentUser.getId_()))) {
			}else{
				//如果不是占坑人，提示无权操作
				result.put("success", false);
				result.put("msg", "你不是办理人，无权操作");
				ResponseUtil.write(response, result);
				return null;
			}
		}
		
		renWuService.last_done(taskId, taskName, comment, msg, renwu);
		
		JSONObject result=new JSONObject();
		result.put("success", true);
		result.put("msg", "完成任务，执行成功");
		
		ResponseUtil.write(response, result);
		return null;
	}
	
	
	
	
	/**
	 * /admin/renwu/list
	 * 
	 * @param page
	 * @param rows
	 * @param createUserId
	 * @param state
	 * @param q
	 * @param processDefinitionKey
	 * @param processDefinitionName
	 * @param date1
	 * @param date2
	 * @param install_
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,
			@RequestParam(value="createUserId",required=false)String createUserId,
			@RequestParam(value="state",required=false)String state,
			@RequestParam(value="q",required=false)String q,
			@RequestParam(value="processDefinitionKey",required=false)String processDefinitionKey,
			@RequestParam(value="processDefinitionName",required=false)String processDefinitionName,
			@RequestParam(value="date1",required=false)String date1,
			@RequestParam(value="date2",required=false)String date2,
			@RequestParam(value="install_",required=false)String install_,
			@RequestParam(value="fendianId",required=false)String fendianId,
			@RequestParam(value="jixingId",required=false)String jixingId,
			@RequestParam(value="shuiyuanId",required=false)String shuiyuanId,
			@RequestParam(value="last_change_xin_dataTime",required=false)String last_change_xin_dataTime,
			HttpServletResponse response)throws Exception{
		
		
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("createUserId", createUserId);
		map.put("state", state);
		map.put("install_", install_);
		map.put("processDefinitionName", processDefinitionName);
		map.put("processDefinitionKey", processDefinitionKey);
		map.put("q", StringUtil.formatLike(q));
		
		map.put("date1", date1);//大于
		map.put("date2", date2);//小于
		
		map.put("fendianId", fendianId);// 
		map.put("jixingId", jixingId);// 
		map.put("shuiyuanId", shuiyuanId);// 
		map.put("last_change_xin_dataTime", last_change_xin_dataTime);// 
		
		
		
		List<RenWu> list=renWuService.list(map);
		Integer total=renWuService.getTotal(map);
		map.clear();
		
		
		Gson gson  = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		
		map.put("rows", list);
		map.put("total", total);
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	
	
	
	/**
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			//判断任务 是否已关闭 才可以删除
			RenWu renwu = renWuService.findById(Integer.parseInt(idsStr[i]));
			if(renwu.getState()!=1){
				renWuService.delete(Integer.parseInt(idsStr[i]));
			}else{
				Gson g = new Gson();
				Result result=new Result();
				result.setSuccess(false);
				result.setMsg("任务正在进行中，不能删除，请关闭任务。再删除！！！！！！！！");
				ResponseUtil.write(response, g.toJson(result));
				return null;
			}
		}
		Gson g = new Gson();
		Result result=new Result();
		result.setSuccess(true);
		result.setMsg("删除成功");
		ResponseUtil.write(response, g.toJson(result));
		return null;
	}
	
	
	
	
	
	
}
