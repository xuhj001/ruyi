package com.java1234.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.java1234.entity.Access_token;
import com.java1234.entity.Group;
import com.java1234.entity.MemberShip;
import com.java1234.entity.RenWu;
import com.java1234.entity.SMSTask;
import com.java1234.entity.User;
import com.java1234.http.HttpClient;
import com.java1234.service.GroupService;
import com.java1234.service.MemberShipService;
import com.java1234.service.PublicService;
import com.java1234.service.SMSTaskService;
import com.java1234.service.WeiXinMSGService;
import com.java1234.weixin.WeiXinUtil;

import net.sf.json.JSONObject;


@Service("weiXinMSGService")
public class WeiXinMSGServiceImpl implements WeiXinMSGService {
	
	
	@Resource
	private SMSTaskService sMSTaskService;
	@Resource
	private MemberShipService memberShipService;
	@Resource
	private PublicService publicService;
	@Resource
	private GroupService groupService;
	
	
	
	
	@Override
	public void completeAfterSendWXmsg(String taskName, String msg, String procdefId, String type, RenWu renwu,
			User currentUser) {
		
		//��ѯ������������  ���� taskName�� procdefId ��ѯ
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskName", taskName);
		map.put("procdefId", renwu.getProcessDefinitionId());
		map.put("type", type);
		map.put("msg", msg);
		List<SMSTask> TaskList = sMSTaskService.list(map);
		
		List<User> users = new ArrayList<User>();//���弯��װuser
		
		for(SMSTask smsTask :TaskList){
			if(smsTask.getWx_sms().equals("1")){
				//�����е� ��Ա���� 
		        Map<String,Object> group_map  = new HashMap<String,Object>();
		        group_map.put("groupId", smsTask.getGroupId());
		        List<MemberShip> list = memberShipService.list(group_map);
				for(MemberShip m:list){
					users.add(m.getUser());
				}
		        
		        //users�������з� ΢����Ϣ����
		        Group group = groupService.findById_(smsTask.getGroupId());
		        
			     // ��������� �ͷ��������ݡ������������� �ͷ�������ݡ�
			     List<JSONObject> push_list = new ArrayList<JSONObject>();
			     
		        //��������� �ͷ��������ݡ�������������  �ͷ�������ݡ�
		        if(renwu.getXiaoshou()!=null){
		        	for(User user :users){
			        	String first = "��"+msg+"����"+"\t\n"
			        			+"�ͻ�����:"+renwu.getXiaoshou().getClientName()+"\t\n"
			        +"�ͻ��绰:"+renwu.getXiaoshou().getClientPhone()+"\t\n"
			        +"�Ƿ�װ:"+renwu.getXiaoshou().getInstall_()+"\t\n"
			        +"�ͻ���ַ:"+renwu.getXiaoshou().getClientAddress()+"\t\n"
			        +"���۵���ע:"+renwu.getXiaoshou().getRemark()+"\t\n"
			        +"����:"+renwu.getXiaoshou().getDing_jine()+"\t\n"
			        +"���:"+renwu.getXiaoshou().getYu_jine()+"\t\n";
			        	JSONObject obj = new JSONObject();
						obj.put("openid", user.getWeiXinUserInfo().getTrueOpenid());
						obj.put("first", first);
						obj.put("rw_name", "��Ҫ��" + group.getName_() + "������");
						obj.put("about", group.getName_() + "����");
						push_list.add(obj);
			        }
		        }
		        this.start_thread_push_msm(push_list);
			}
		}
	}



	@Override
	public void push_sms(String openid, String first, String rw_name, String about) {
		
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext servletContext = webApplicationContext.getServletContext(); 
		Access_token token = (Access_token)  servletContext.getAttribute("access_token");
		String post_url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token.getAccess_token();
		String tit_data = WeiXinUtil.get_OPENTM205196607(openid, first, rw_name, about);
		
		try {
			HttpClient.post(post_url, tit_data);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	


	@Override
	public void start_thread_push_msm(final List<JSONObject> push_list) {
		
		final ExecutorService executorService = Executors.newFixedThreadPool(5);
		final WeiXinMSGService weiXinMSGService = this;
		
		
		executorService.execute(new Runnable() {
			public void run() {
				
				for(JSONObject obj : push_list){
					weiXinMSGService.push_sms(obj.get("openid").toString(), obj.get("first").toString(), obj.get("rw_name").toString(),obj.get("about").toString() );
				}
				
				executorService.shutdown();// �ر�
				
			}
		});
			 
		
		
		
		
	}

}
