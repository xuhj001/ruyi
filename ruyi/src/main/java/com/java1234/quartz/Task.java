package com.java1234.quartz;


import java.util.Date;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.java1234.entity.Config;
import com.java1234.service.ConfigService;
import com.java1234.service.PublicService;
import com.java1234.util.DateUtil;


@Component
public class Task {
	
	
	@Resource
	private PublicService publicService;
	@Resource
	private ConfigService configService;
	
	/**
	 * 
	 * 
	 * @Scheduled(cron="* * * * * ?") ÿ��ִ��һ��
	 * @Scheduled(cron="0 * * * * ?") ÿ��ִ��һ��
	 * @Scheduled(cron="0 0 * * * ?") ÿʱִ��һ��
	 * 
	@Scheduled(cron = "0 0 * * * ?")
	public void task1() {
		publicService.refreshToken();
	}
	*/
	
	
	/**
	 * �����ʱ���ǲ飬���һ�λ�о��ʱ�� + ���������ñ� ��������� 
	 * 
	 * @Scheduled(cron="* * * * * ?") ÿ��ִ��һ��
	 * @Scheduled(cron="0 * * * * ?") ÿ��ִ��һ��
	 * @Scheduled(cron="0 0 * * * ?") ÿʱִ��һ��
	 *
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void last_change_xin() {
		//��ǰ ���ڡ���ȥ   ���ñ� �������������     ���update_xin��ʱ��  �������ʱ�䣬����ʾ���û�
		Date date = new Date();
		Config config = configService.findById(1);
		
		
		publicService.refreshToken();
	}
	
	
	
	
}
