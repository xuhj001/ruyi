package com.java1234.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.task.Comment;
import org.apache.http.client.ClientProtocolException;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.java1234.entity.Access_token;
import com.java1234.entity.MemberShip;
import com.java1234.entity.Tree;
import com.java1234.entity.User;
import com.java1234.entity.UserTask;
import com.java1234.http.HttpClient;
import com.java1234.service.MemberShipService;
import com.java1234.service.PublicService;
import com.java1234.service.TreeService;
import com.java1234.service.UserService;
import com.java1234.util.MyUtil;
import com.java1234.weixin.WeiXinUtil;


@Service("publicService")
public class PublicServiceImpl implements PublicService {
	
	@Resource
	private TreeService treeService;
	@Resource
	private UserService userService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private HistoryService historyService;
	@Resource
	private TaskService taskService;
	@Resource
	private  MemberShipService  memberShipService;
	
	
	public void addLeftMenu(ModelAndView mav) {
		mav.addObject("leftPage", "/admin/common/left_menu.jsp");
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		currentUser = userService.findById_(currentUser.getId_());
		Map<String, Object> map = new HashMap<String, Object>();
		String menuIds = currentUser.getWeiXinUserInfo().getTreeIds();
		if(menuIds==null){
			menuIds = "";
		}
		List<Integer> ids =MyUtil.Str_ids_To_ListInteger_ids(menuIds);  
		map.put("father", -1);
		map.put("ids", ids);
		if(ids.size()>0){
		}else{
			//�����null  ���Ǿ�����һ��null
			List<Tree> treeList = new ArrayList<Tree>();
			mav.addObject("treeList", treeList);
			return ;
		}
		
		try {
			List<Tree> treeList = getTreesByParentId(map);
			mav.addObject("treeList", treeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * �ò˵�
	 */
	public List<Tree> getTreesByParentId(Map<String,Object> map) throws Exception {
		//String parentId,String ids  = map
		List<Tree> list = treeService.getTreesByFatherOrIds(map);
		for(Tree tree : list){
			//��� �Ǹ�ѡ��  �����������ж�   
			//tree.setChecked(true);
			if("open".equals(tree.getState())){
				continue;
			}else{
				map.put("father", tree.getId()+"");//����id����ids������
				tree.setChildren(getTreesByParentId(map));
			}
		}
		return list;
	}


	
	public List<UserTask> getAllUserTaskByProcdefId(Map<String, Object> map) {
		
		List<UserTask> list = new ArrayList<UserTask>();
		UserTask userTask = null;
		
		BpmnModel model = repositoryService.getBpmnModel(map.get("procdefId").toString());
		
		if (model != null) {
			Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
			for (FlowElement e : flowElements) {
				if (e.getClass().toString().indexOf("UserTask") != -1) {
					userTask = new UserTask();
					userTask.setTaskId(e.getId());
					userTask.setTaskName(e.getName());
					list.add(userTask);
					// System.out.println("����id:" + e.getId() +
					// " ��������:" + e.getName() + " ��Ӧ��class:" +
					// e.getClass().toString());
				}
			}
		}
		return list;
	}


	/**  �õ�����ط���
	 * ���taskName ������ʹ�õ� 001[�����ۺ�]
	 */
	public Integer addComment(String taskId, User user, String comment, String taskName) {
		HistoricTaskInstance hti=historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
		Authentication.setAuthenticatedUserId(user.getFirst_()+"["+taskName+"]"); // �����û�id
		taskService.addComment(taskId, hti.getProcessInstanceId(), comment); // �����ע��Ϣ
		return 1;
	}

	
	public List<String> getPerson(Map<String, Object> queryMP) {
		List<MemberShip> list = memberShipService.list(queryMP);
		List<String> persons = new ArrayList<String>();
		for(MemberShip m:list){
			persons.add(m.getUser().getLast_());
		}
		return persons;
	}
	
	
	
	
	
	public List<Comment> listCommentByTaskId(String taskId) {
		// ��������id ����ʷ��ע
		HistoricTaskInstance hti = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
		List<Comment> commentList = taskService.getProcessInstanceComments(hti.getProcessInstanceId());
		Collections.reverse(commentList); // ����Ԫ�ط�ת
		return commentList;
	}
	
	
	
	@Override
	public void refreshToken() {
		
		Access_token access_token = WeiXinUtil.getToken();
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext servletContext = webApplicationContext.getServletContext(); 
        servletContext.setAttribute("access_token", access_token);
        System.out.println(access_token);
	}
	
	 
	
	
	
}
