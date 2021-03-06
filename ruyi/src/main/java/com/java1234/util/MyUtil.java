package com.java1234.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Decoder;

public class MyUtil {

	/**
	 * 取得用户ip
	 */
	public static String getRemoteAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	
	/**
	 * 把传来的string ids 变成 List<Integer> ids = new ArrayList<Integer>() 返回
	 * 因为mybatis查范围,要用的迭代
	 */
	public static List<Integer> Str_ids_To_ListInteger_ids(String ids) {
		List<Integer> ListInteger_ids = new ArrayList<Integer>();
		String[] arr = ids.split(",");
		for (String i : arr) {
			//验证是不是数字
			if(i.matches("\\d+")){
				ListInteger_ids.add(Integer.parseInt(i));
			}
		}
		return ListInteger_ids;
	}

	/**
	 * 这个方法是拿 复选框 时用的 判断id在不在ids之内。如果在返回true
	 */
	public static boolean existStrArr(String id, String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			if (ids[i].equals(id)) {
				return true;
			}
		}
		return false;
	}

	
	/**
	 * 把用户的menus过虑一边，过虑掉一些特殊的菜单
	 */
	public static String filterMenuIds(String[] ids) {
		String subids = "500";
		String[] arr = arrContrast(ids, subids.split(","));
		return org.apache.commons.lang.StringUtils.join(arr, ',');
	}

	/**
	 * 以arr1为 基准 把arr2的内容从arr中过虑掉。删除 返回新的arr
	 */
	private static String[] arrContrast(String[] arr1, String[] arr2) {
		List<String> list = new LinkedList<String>();
		for (String str : arr1) { // 处理第一个数组,list里面的值为1,2,3,4
			if (!list.contains(str)) {
				list.add(str);
			}
		}
		for (String str : arr2) { // 如果第二个数组存在和第一个数组相同的值，就删除
			if (list.contains(str)) {
				list.remove(str);
			}
		}
		String[] result = {}; // 创建空数组
		return list.toArray(result); // List to Array
	}

	/**
	 * 把对象或者 数组转成string
	 */
	public static String listToString(List list, char separator) {
		return org.apache.commons.lang.StringUtils.join(list.toArray(), separator);
	}

	// 将image str 转成图片 存起来。
	public static boolean GenerateImage(String imgStr, String Folder, String imgFilePath) {// 对字节数组字符串
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {
					// 调整异常数据
					bytes[i] += 256;
				}
			}

			// 创建文件夹
			makeDirs(Folder);
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(Folder+"\\"+imgFilePath);

			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	
	public static boolean makeDirs(String filePath) {
		if (filePath == null || filePath.isEmpty()) {
			return false;
		}
		File folder = new File(filePath);
		if(folder.exists()){//IsDirectory( ) 判断文件夹是否存在
			return true; 
		}else{
			return folder.mkdirs();
		}
	}

}
