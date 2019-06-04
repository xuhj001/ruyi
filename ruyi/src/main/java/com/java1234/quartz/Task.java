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
	 * @Scheduled(cron="* * * * * ?") 每秒执行一次
	 * @Scheduled(cron="0 * * * * ?") 每分执行一次
	 * @Scheduled(cron="0 0 * * * ?") 每时执行一次
	 * 
	@Scheduled(cron = "0 0 * * * ?")
	public void task1() {
		publicService.refreshToken();
	}
	*/
	
	
	/**
	 * 这个定时器是查，最后一次换芯的时间 + 上我们配置表 定义的周期 
	 * 
	 * @Scheduled(cron="* * * * * ?") 每秒执行一次
	 * @Scheduled(cron="0 * * * * ?") 每分执行一次
	 * @Scheduled(cron="0 0 * * * ?") 每时执行一次
	 *
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void last_change_xin() {
		//当前 日期。减去   配置表 定义的周期天数     如果update_xin的时间  超过这个时间，就显示该用户
		Date date = new Date();
		Config config = configService.findById(1);
		
		
		publicService.refreshToken();
	}
	
	
	
	
}
