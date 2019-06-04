package com.java1234.util;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlUtil {

	/**
	 * ���� str  ȡ���е�imgs  Ȼ�󷵻�list_img�б�
	 */
	public static List<String> getImgUrls(String htmlStr) {
		
		List<String> img_urls = new ArrayList<String>();
		
		Document contentDoc = Jsoup.parse(htmlStr);
		Elements imgs = contentDoc.select("img");
		for (Element img : imgs) {
			img_urls.add(img.attr("src"));
		}
		return img_urls;
	}
	
	/**
	 * ����str  ɾ�����е�img ��ǩ ����   ��ֻ�����ı���
	 */
	public static String removeImg(String htmlStr){
		
		Document contentDoc = Jsoup.parse(htmlStr);
		contentDoc.select("img").remove();
		
		return contentDoc.html();
		
	}
	
}
