package com.java1234.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.task.Comment;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.java1234.entity.Config;
import com.java1234.entity.MyComment;
import com.java1234.entity.RenWu;
import com.java1234.entity.User;
import com.java1234.service.ConfigService;
import com.java1234.service.ProcessVariableService;
import com.java1234.service.PublicService;
import com.java1234.service.RenWuService;
import com.java1234.service.SMSTaskService;
import com.java1234.util.HtmlUtil;
import com.java1234.util.StringUtil;


@Controller
@RequestMapping("/renwu/wap")
public class RenWu_WAP_Controller {

	
	@Resource
	private PublicService publicService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
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
	
	
	
	
	/**
	 * 我需要 接受一个taskId
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/banli")
	public ModelAndView banli(String taskId, String taskName, HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		
		//如果传来看任务id  是错误的 这里直接报错。
		Integer renwuId = (Integer) taskService.getVariable(taskId, "renwuId");
		
		RenWu renwu = renWuService.findById(renwuId);
		mav.addObject("renwu", renwu);
		
		
		// 根据任务id 查找 历史批注
		List<Comment> commentList = publicService.listCommentByTaskId(taskId);
		Collections.reverse(commentList); // 集合元素反转
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
		mav.addObject("myCommentList", myCommentList);//执行明细
		
		
		// 根据实例id查换 流程执行过程  添加排序
		List<HistoricActivityInstance> haiList=historyService.createHistoricActivityInstanceQuery()
				.orderByHistoricActivityInstanceStartTime().asc()
				.executionId(renwu.getProcessInstanceId())
				//.processInstanceId()
		.list();
		
		
		Config config = configService.findById(1);
		mav.addObject("config", config);
		mav.addObject("haiList", haiList);//执行列表
		mav.addObject("pageTitle", taskName+"处理");
		mav.addObject("title", taskName+"处理");
		mav.addObject("taskName", taskName);
		
		
		if(renwu.getXiaoshou()!=null){
			mav.addObject("detailPage","/foreground/xiaoshou/detail.jsp");
		}
		
		mav.addObject("acceptPage","/foreground/renwu/accept.jsp");
		//pizhuPage  =  执行-明细   和  执行-列表
		mav.addObject("pizhuPage","/foreground/renwu/pizhu.jsp");
		mav.addObject("addPizhuPage","/foreground/renwu/addPizhu.jsp");
		mav.addObject("banliPage","/foreground/renwu/banli.jsp");
		
		
		mav.addObject("map_select_point_page","/foreground/map/renwu_select_point_page.jsp");
		
		
		mav.setViewName("foreground/renwu/model");
		return mav;
	}
	
	
	
	@RequestMapping("/last_banli")
	public ModelAndView last_banli(String taskId, String taskName, HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		
		//如果传来看任务id  是错误的 这里直接报错。
		Integer renwuId = (Integer) taskService.getVariable(taskId, "renwuId");
		
		RenWu renwu = renWuService.findById(renwuId);
		mav.addObject("renwu", renwu);
		
		
		// 根据任务id 查找 历史批注
		List<Comment> commentList = publicService.listCommentByTaskId(taskId);
		Collections.reverse(commentList); // 集合元素反转
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
		mav.addObject("myCommentList", myCommentList);//执行明细
		
		
		// 根据实例id查换 流程执行过程  添加排序
		List<HistoricActivityInstance> haiList=historyService.createHistoricActivityInstanceQuery()
				.orderByHistoricActivityInstanceStartTime().asc()
				.executionId(renwu.getProcessInstanceId())
				//.processInstanceId()
		.list();
		
		
		Config config = configService.findById(1);
		mav.addObject("config", config);
		mav.addObject("haiList", haiList);//执行列表
		mav.addObject("pageTitle", taskName+"处理");
		mav.addObject("title", taskName+"处理");
		mav.addObject("taskName", taskName);
		
		
		if(renwu.getXiaoshou()!=null){
			mav.addObject("detailPage","/foreground/xiaoshou/detail.jsp");
		}
		
		mav.addObject("acceptPage","/foreground/renwu/accept.jsp");
		//pizhuPage  =  执行-明细   和  执行-列表
		mav.addObject("pizhuPage","/foreground/renwu/pizhu.jsp");
		mav.addObject("addPizhuPage","/foreground/renwu/addPizhu.jsp");
		
		mav.addObject("banliPage","/foreground/renwu/last_banli.jsp");
		
		mav.setViewName("foreground/renwu/model");
		return mav;
	}
	
	
	/**
	 *  /renwu/wap/my_banli
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/my_banli")
	public ModelAndView my_banli(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("pageTitle", "我办理的");
		mav.addObject("title", "我办理的");
		/**
		 * 为什么要把我办理的页面放到common里面，
		 * 我是这样想的，我办理的以后会肯定会设计成 选项卡式的。 比较【售后，销售，返修】
		 * 放在这里，是为以后考虑。
		 */
		
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		
		mav.addObject("currentUserId",currentUser.getId_());
		mav.addObject("page", "foreground/common/my_banli.jsp");
		mav.setViewName("mobile_main");
		return mav;
	}
	
	
	/**
	 * 
	 * 我的销售单
	 * 分店销售单
	 * 全部销售单
	 * 
	 * 都是这个接口
	 * 接受参数不同
	 * /renwu/wap/all_dingdan
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/dingdan")
	public ModelAndView dingdan(
			@RequestParam(value="fendianId",required=false)String fendianId,
			@RequestParam(value="processDefinitionKey",required=false)String processDefinitionKey,
			@RequestParam(value="currentUserId",required=false)String currentUserId,
			@RequestParam(value="title",required=false)String title,
			HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("title", title);
		/**
		 * 为什么要把我办理的页面放到common里面，
		 * 我是这样想的，我办理的以后会肯定会设计成 选项卡式的。 比较【售后，销售，返修】
		 * 放在这里，是为以后考虑。
		 */
		
		mav.addObject("currentUserId",currentUserId);
		mav.addObject("processDefinitionKey",processDefinitionKey);
		mav.addObject("fendianId",fendianId);
		
		mav.addObject("page", "foreground/common/dingdan.jsp");
		mav.setViewName("mobile_main");
		return mav;
	}
	
	/**
	 * /renwu/wap/12
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception
	 */
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
			mav.addObject("detailPage","/foreground/xiaoshou/detail.jsp");
		}
		mav.addObject("pizhuPage","/foreground/renwu/pizhu.jsp");
		
		
		mav.setViewName("foreground/renwu/model");
		return mav;
	}
	
	
	
	
}
