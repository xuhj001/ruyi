package com.java1234.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ���ڹ�����
 * @author Administrator
 *
 */
public class DateUtil {
	
	public static void main(String []args){
		
		System.out.println(addDay("yyyy-MM-dd HH:mm","2017-01-30 12:55",-2));
		
	}
	
	/**
	 * yyyyMMdd hhmmssSSS
	 * ���ڶ���ת�ַ���
	 */
	public static String formatDate(Date date,String format){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			result=sdf.format(date);
		}
		return result;
	}
	
	
	/**
	 * �ַ���ת���ڶ���
	 * @param str
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static Date formatString(String str,String format) throws Exception{
		if(StringUtil.isEmpty(str)){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}
	
	/**
	 * yyyyMMddHHmmss
	 * @param formart �����涨��
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentDateStr(String formart)throws Exception{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	
	
	/**
	 * 
	 * @param dateFormart ���ڵĸ�ʽ "yyyy-MM-dd HH:mm"
	 * @param dateStr  ���ڵ��ַ���
	 * @param n  �Ӽ���  ���Ļ��� -1 -5 -10
	 * @return
	 */
	public static String addDay(String dateFormart,String dateStr, int n) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormart);
			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(dateStr));

			cd.add(Calendar.DATE, n);// ����һ��
			// cd.add(Calendar.MONTH, n);//����һ����
			return sdf.format(cd.getTime());

		} catch (Exception e) {
			return null;
		}
	}
	
	
	
}
