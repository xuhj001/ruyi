package com.java1234.util;


import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * ���ܹ���
 * @author Administrator
 *
 */
public class CryptographyUtil {
	
	public static void main(String[] args) throws Exception {
		
		
		
		String ids = "";
		String[] arr = ids.split(",");
		System.out.println(arr.length);
		
		
		
		String password="123";//fd58e1d93432f4da5b6ff1c2821b2327
		System.out.println("Md5���ܣ�"+CryptographyUtil.md5(password, "chenhao"));
		MyUtil.existStrArr("1", ids.split(","));
	}
	
	
	/**
	 * Md5����
	 * @param str  ���ܵ�����
	 * @param salt  ��ֵ 
	 */
	public static String md5(String str,String salt){
		return new Md5Hash(str,salt).toString();
	}
	
	
}
