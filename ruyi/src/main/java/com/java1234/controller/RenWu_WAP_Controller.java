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
	 * ����Ҫ ����һ��taskId
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
		
		//�������������id  �Ǵ���� ����ֱ�ӱ���
		Integer renwuId = (Integer) taskService.getVariable(taskId, "renwuId");
		
		RenWu renwu = renWuService.findById(renwuId);
		mav.addObject("renwu", renwu);
		
		
		// ��������id ���� ��ʷ��ע
		List<Comment> commentList = publicService.listCommentByTaskId(taskId);
		Collections.reverse(commentList); // ����Ԫ�ط�ת
		List<MyComment> myCommentList = new ArrayList<MyComment>();
		
		MyComment mc = null;
		
		// ����ʷ��ע װ�������Լ���װ��ʵ����
		for (Comment comment : commentList) {
			// ȡ��str�е�img ��ǩ
			mc = new MyComment();
			mc.setMessage(HtmlUtil.removeImg(comment.getFullMessage()));
			mc.setTime(comment.getTime());
			mc.setUserId(comment.getUserId());
			mc.setImageList(HtmlUtil.getImgUrls(comment.getFullMessage()));
			myCommentList.add(mc);
		}
		mav.addObject("myCommentList", myCommentList);//ִ����ϸ
		
		
		// ����ʵ��id�黻 ����ִ�й���  �������
		List<HistoricActivityInstance> haiList=historyService.createHistoricActivityInstanceQuery()
				.orderByHistoricActivityInstanceStartTime().asc()
				.executionId(renwu.getProcessInstanceId())
				//.processInstanceId()
		.list();
		
		
		Config config = configService.findById(1);
		mav.addObject("config", config);
		mav.addObject("haiList", haiList);//ִ���б�
		mav.addObject("pageTitle", taskName+"����");
		mav.addObject("title", taskName+"����");
		mav.addObject("taskName", taskName);
		
		
		if(renwu.getXiaoshou()!=null){
			mav.addObject("detailPage","/foreground/xiaoshou/detail.jsp");
		}
		
		mav.addObject("acceptPage","/foreground/renwu/accept.jsp");
		//pizhuPage  =  ִ��-��ϸ   ��  ִ��-�б�
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
		
		//�������������id  �Ǵ���� ����ֱ�ӱ���
		Integer renwuId = (Integer) taskService.getVariable(taskId, "renwuId");
		
		RenWu renwu = renWuService.findById(renwuId);
		mav.addObject("renwu", renwu);
		
		
		// ��������id ���� ��ʷ��ע
		List<Comment> commentList = publicService.listCommentByTaskId(taskId);
		Collections.reverse(commentList); // ����Ԫ�ط�ת
		List<MyComment> myCommentList = new ArrayList<MyComment>();
		
		MyComment mc = null;
		
		// ����ʷ��ע װ�������Լ���װ��ʵ����
		for (Comment comment : commentList) {
			// ȡ��str�е�img ��ǩ
			mc = new MyComment();
			mc.setMessage(HtmlUtil.removeImg(comment.getFullMessage()));
			mc.setTime(comment.getTime());
			mc.setUserId(comment.getUserId());
			mc.setImageList(HtmlUtil.getImgUrls(comment.getFullMessage()));
			myCommentList.add(mc);
		}
		mav.addObject("myCommentList", myCommentList);//ִ����ϸ
		
		
		// ����ʵ��id�黻 ����ִ�й���  �������
		List<HistoricActivityInstance> haiList=historyService.createHistoricActivityInstanceQuery()
				.orderByHistoricActivityInstanceStartTime().asc()
				.executionId(renwu.getProcessInstanceId())
				//.processInstanceId()
		.list();
		
		
		Config config = configService.findById(1);
		mav.addObject("config", config);
		mav.addObject("haiList", haiList);//ִ���б�
		mav.addObject("pageTitle", taskName+"����");
		mav.addObject("title", taskName+"����");
		mav.addObject("taskName", taskName);
		
		
		if(renwu.getXiaoshou()!=null){
			mav.addObject("detailPage","/foreground/xiaoshou/detail.jsp");
		}
		
		mav.addObject("acceptPage","/foreground/renwu/accept.jsp");
		//pizhuPage  =  ִ��-��ϸ   ��  ִ��-�б�
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
		
		mav.addObject("pageTitle", "�Ұ����");
		mav.addObject("title", "�Ұ����");
		/**
		 * ΪʲôҪ���Ұ����ҳ��ŵ�common���棬
		 * ����������ģ��Ұ�����Ժ��϶�����Ƴ� ѡ�ʽ�ġ� �Ƚϡ��ۺ����ۣ����ޡ�
		 * ���������Ϊ�Ժ��ǡ�
		 */
		
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		
		mav.addObject("currentUserId",currentUser.getId_());
		mav.addObject("page", "foreground/common/my_banli.jsp");
		mav.setViewName("mobile_main");
		return mav;
	}
	
	
	/**
	 * 
	 * �ҵ����۵�
	 * �ֵ����۵�
	 * ȫ�����۵�
	 * 
	 * ��������ӿ�
	 * ���ܲ�����ͬ
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
		 * ΪʲôҪ���Ұ����ҳ��ŵ�common���棬
		 * ����������ģ��Ұ�����Ժ��϶�����Ƴ� ѡ�ʽ�ġ� �Ƚϡ��ۺ����ۣ����ޡ�
		 * ���������Ϊ�Ժ��ǡ�
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
		
		
		//����ʵ��id��� ��ʷ��ע
		List<Comment> commentList = taskService.getProcessInstanceComments(renwu.getProcessInstanceId());
		List<MyComment> myCommentList = new ArrayList<MyComment>();
		MyComment mc = null;
		// ����ʷ��ע װ�������Լ���װ��ʵ����
		for (Comment comment : commentList) {
			// ȡ��str�е�img ��ǩ
			mc = new MyComment();
			mc.setMessage(HtmlUtil.removeImg(comment.getFullMessage()));
			mc.setTime(comment.getTime());
			mc.setUserId(comment.getUserId());
			mc.setImageList(HtmlUtil.getImgUrls(comment.getFullMessage()));
			myCommentList.add(mc);
		}
		mav.addObject("myCommentList", myCommentList);
		
		// ����ʵ��id�黻 ����ִ�й���  �������
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
