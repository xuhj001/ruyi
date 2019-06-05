package com.java1234.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.task.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java1234.entity.Config;
import com.java1234.entity.MyComment;
import com.java1234.entity.RenWu;
import com.java1234.service.ConfigService;
import com.java1234.service.ProcessVariableService;
import com.java1234.service.RenWuService;
import com.java1234.service.SMSTaskService;
import com.java1234.util.HtmlUtil;

@Controller
@RequestMapping("/renwu/pc")
public class RenWu_PC_Controller {
	
	@Resource
	private TaskService taskService;
	@Resource
	private ProcessVariableService  processVariableService;
	@Resource
	private SMSTaskService  sMSTaskService;
	@Resource
	private RenWuService  renWuService;
	@Resource
	private ConfigService  configService;
	@Resource
	private HistoryService historyService;
	
	
	
	@RequestMapping("/{id}")
	public ModelAndView view_id(@PathVariable("id") Integer id, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		RenWu renwu = renWuService.findById(id);
		mav.addObject("renwu", renwu);
		
		
		//根据实例id查的 历史批注
		List<Comment> commentList = taskService.getProcessInstanceComments(renwu.getProcessInstanceId());
		List<MyComment> myCommentList = new ArrayList<MyComment>();
		MyComment mc = null;
		// 把历史批注 装到我们自己封装的实体中
		for (Comment comment : commentList) {
			// 取出str中的img 标签
			mc = new MyComment();
			mc.setMessage(HtmlUtil.removeImg(comment.getFullMessage()));
			mc.setTime(comment.getTime());
			mc.setUserId(comment.getUserId());
			mc.setImageList(HtmlUtil.getImgUrls(comment.getFullMessage()));
			myCommentList.add(mc);
		}
		mav.addObject("myCommentList", myCommentList);
		
		// 根据实例id查换 流程执行过程  添加排序
		List<HistoricActivityInstance> haiList=historyService.createHistoricActivityInstanceQuery()
				.orderByHistoricActivityInstanceStartTime().asc()
				.executionId(renwu.getProcessInstanceId())
				//.processInstanceId()
		.list();
		
		mav.addObject("haiList", haiList);
		
		if(renwu.getXiaoshou()!=null){
			mav.setViewName("admin/page/xiaoshou/view");
		}
		return mav;
	}
	
	
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		RenWu renwu  = renWuService.findById(Integer.parseInt(id));
		mav.addObject("renwu", renwu);
		
		if(renwu.getXiaoshou()!=null){
			mav.addObject("save_url", "/admin/xiaoshou/update?id="+renwu.getXiaoshouId());
			mav.setViewName("/admin/page/xiaoshou/edit");
		}
		
		return mav;
	}
	
	
	
}
