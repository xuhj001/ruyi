package com.java1234.service.impl;

import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.java1234.entity.Access_token;
import com.java1234.weixin.WeiXinUtil;

@Component
public class InitComponent implements ServletContextListener,ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	
	/**
	 * //�����õ�application���� spring�ṩ�ġ�
		ServletContext application=RequestContextUtils.getWebApplicationContext(request).getServletContext();
	 */
	public void contextInitialized(ServletContextEvent sce) {
		
		ServletContext application=sce.getServletContext();
		
		//��ʼ��access_token ���浽 ������
		Access_token access_token = WeiXinUtil.getToken();
		application.setAttribute("access_token", access_token);
		System.out.println(access_token.toString());
		
		
		
		/*
		BloggerService bloggerService=(BloggerService) applicationContext.getBean("bloggerService");
		Blogger blogger=bloggerService.find(); // ��ȡ������Ϣ
		blogger.setPassword(null);
		application.setAttribute("blogger", blogger);
		
		
		LinkService linkService=(LinkService) applicationContext.getBean("linkService");
		List<Link> linkList=linkService.list(null); // ��ѯ���е�����������Ϣ
		application.setAttribute("linkList", linkList);
		
		BlogTypeService blogTypeService=(BlogTypeService) applicationContext.getBean("blogTypeService");
		List<BlogType> blogTypeCountList=blogTypeService.countList(); // ��ѯ��������Լ����͵�����
		application.setAttribute("blogTypeCountList", blogTypeCountList);
		
		BlogService blogService=(BlogService) applicationContext.getBean("blogService");
		List<Blog> blogCountList=blogService.countList(); // �������ڷ����ѯ����
		application.setAttribute("blogCountList", blogCountList);
		*/
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}

}
