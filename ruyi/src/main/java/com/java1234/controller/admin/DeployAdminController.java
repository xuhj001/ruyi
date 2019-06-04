package com.java1234.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.java1234.entity.PageBean;
import com.java1234.entity.Result;
import com.java1234.util.ResponseUtil;

/**
 * 部署管理Controller
 * @author user
 *
 */
@Controller
@RequestMapping("/admin/deploy")
public class DeployAdminController {

	@Resource
	private RepositoryService repositoryService;
	
	
	/**
	 * 上传流程部署文件
	 * @param deployFile
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deploy")
	public String deploy(@RequestParam("deployFile") MultipartFile deployFile,HttpServletResponse response)throws Exception{
		repositoryService.createDeployment() // 创建部署
				.name(deployFile.getOriginalFilename()) // 流程名称
				.addZipInputStream(new ZipInputStream(deployFile.getInputStream())) // 添加zip输入流
				.deploy(); // 部署
		
		Result result = new Result();
		result.setSuccess(true);
		result.setMsg("部署成功");
		
		Gson gson = new Gson();
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	
	
	
	/**
	 * 流程部署查询
	 * @param page
	 * @param rows
	 * @param s_name
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,String q,HttpServletResponse response)throws Exception{
		if(q==null){
			q="";
		}
		
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<Deployment> deploymentList=repositoryService.createDeploymentQuery() // 创建流程部署查询
					.orderByDeploymenTime().desc() // 根据部署时间降序排列
					.deploymentNameLike("%"+q+"%") // 根据部署名称模糊查询
					.listPage(pageBean.getStart(), pageBean.getPageSize()); // 返回带分页的结果集合
		long total=repositoryService.createDeploymentQuery().deploymentNameLike("%"+q+"%").count(); // 获取总记录数
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.clear();
		map.put("rows", deploymentList);
		map.put("total", total);
		ResponseUtil.write(response, gson.toJson(map));
		 
		return null;
	}
	
	
	
	/**
	 * 删除流程部署
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			repositoryService.deleteDeployment(idsStr[i], true);
		}
		Result result = new Result();
		result.setSuccess(true);
		result.setMsg("删除成功");
		Gson gson = new Gson();
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	
	
	
}
