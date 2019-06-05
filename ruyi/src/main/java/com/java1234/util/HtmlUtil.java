package com.java1234.util;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlUtil {

	/**
	 * 接受 str  取其中的imgs  然后返回list_img列表
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
	 * 接受str  删除其中的img 标签 返回   【只留下文本】
	 */
	public static String removeImg(String htmlStr){
		
		Document contentDoc = Jsoup.parse(htmlStr);
		contentDoc.select("img").remove();
		
		return contentDoc.html();
		
	}
	
}
