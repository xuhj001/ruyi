package com.java1234.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Update;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java1234.entity.FenDian;
import com.java1234.entity.Group;
import com.java1234.entity.User;
import com.java1234.service.FenDianService;
import com.java1234.service.GroupService;
import com.java1234.service.PublicService;
import com.java1234.service.UserService;
import com.java1234.util.CryptographyUtil;

@Controller
@RequestMapping("/user/pc")
public class User_PC_Controller {
	
	@Resource
	private UserService userService;
	@Resource
	private PublicService publicService;
	@Resource
	private GroupService groupService;
	@Resource
	private FenDianService fenDianService;
	/**
	 * /user/pc/login  ��֤�û���½
	 * @param err_url 
	 * @param bj ���  �û���¼ pc ����ios android
	 * @return return err_url;//������ص���login  ����ֱ�ӷ��ص���ͼ login.jsp
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(User user,String err_url,@RequestParam(value = "bj", required = false) String bj
			,HttpServletResponse response,HttpServletRequest request,RedirectAttributes attr)throws Exception{
	        
	        
		Subject subject=SecurityUtils.getSubject();
		subject.getSession().setAttribute("login_type", "wx");//�Ǹ�wx������realm��½���
		subject.getSession().setAttribute("bj", bj);//���õ�½�ı�ʶ  ��������android��ios pc  weixin
		UsernamePasswordToken token=new UsernamePasswordToken(user.getId_(), CryptographyUtil.md5(user.getPwd_(), "chenhao"));
		
		//�ж� �Ƿ�֧�ֵ��Ե�½
		User t_User = userService.findById_(user.getId_());
		if(t_User==null){
			attr.addAttribute("errorInfo", "�û��������������");  
			return "redirect:/login";
		}
		
		if(t_User.getEmail_().equals("1")){
			//֧�ֵ��Ե�½
		}else{
			//��֧�ֵ��Ե�½
			attr.addAttribute("errorInfo", "��֧�ֵ��Ե�½");  
			return "redirect:/login";
		}
		
		try{
			subject.login(token); // ��¼��֤
			user = userService.findById_(user.getId_());
			userService.setGroups(user);
			
			subject.getSession().setAttribute("currentUser", user); 
			
			//return "go_home";
			return "redirect:/admin/main";
			
			//�����½�ɹ� �Ͳ��ᱨ��  ������ǵ�½ʧ����
			//return "redirect:/admin/main.jsp";//��ַ�������仯��
		}catch(Exception e){
			e.printStackTrace();
			
			attr.addAttribute("errorInfo", "�û��������������");  
			return "redirect:/login";
		}
	}
	
	
	/**
	 * �û�����ҳ��
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "�û�����");
		mav.addObject("title", "�û�����");
		mav.setViewName("admin/page/user/user_manage");
		return mav;
	}
	
	
	
	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("btn_text", "���");
		mav.addObject("save_url", "/admin/user/add");
		
		mav.setViewName("/admin/page/user/add_or_update");
		return mav;
	}
	
	
	/**
	 * 
	 * @param id
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		User user = userService.findById_(id);
		
		mav.addObject("user", user);
		mav.addObject("btn_text", "�޸�");
		mav.addObject("save_url", "/admin/user/update");
		
		mav.setViewName("/admin/page/user/add_or_update");
		return mav;
	}
	
	
	
	
	@RequestMapping("/setPersm")
	public ModelAndView setPersm(@RequestParam(value = "id", required = false) String id,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		//���id��ֵ���� ���� ���û��ֵ  �������
		ModelAndView mav = new ModelAndView();
		mav.addObject("userId", id);
		mav.setViewName("admin/page/user/set_persm");
		return mav;
	}
	
	
	
	///user/pc/setGroup?id='+id
	@RequestMapping("/setGroup")
	public ModelAndView setGroup(@RequestParam(value = "id", required = false) String id,HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		//���id��ֵ���� ���� ���û��ֵ  �������
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Group> groupList = groupService.list(map );
		mav.addObject("groupList", groupList);
		
		//�û���ӵ�е�Ȩ��
		List<Group> groupSelectedList = groupService.listByUserId(id);
		
		StringBuffer groups=new StringBuffer();
		for(Group g:groupSelectedList){
			groups.append(g.getId_()+",");
		}
		if(groups.length()>0){
			groups.deleteCharAt(groups.length()-1).toString();
		}
		
		mav.addObject("groupSelectedList",groups);
		
		User user = userService.findById_(id);
		mav.addObject("user", user);
		
		if(user==null){
			return null;
		}
		
		mav.addObject("url", "/admin/ms/update");
		mav.addObject("btn_text", "����");
		mav.setViewName("admin/page/user/set_group");
		return mav;
	}
	
	/**
	 *   /user/pc/logout
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public String logout()throws Exception{
		SecurityUtils.getSubject().logout(); //shiro���˳�
		return "redirect:/login";
	}
	
	
	/**
	 * /user/pc/modify_ps?id=1  �޸�����Ŀ�����
	 * @param id
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modify_ps")
	public ModelAndView modify_ps(String id,HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		//���id��ֵ���� ���� ���û��ֵ  �������
		ModelAndView mav = new ModelAndView();
		
		User user  =  userService.findById_(id);
		mav.addObject("user", user);
		
		mav.setViewName("admin/page/user/modify_ps");
		return mav;
	}
	
	
	/**
	 * 
	 * @param idΪuserid
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/set_fendian")
	public ModelAndView set_fendian(@RequestParam(value = "id", required = false) String id,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isUse", 1);
		
		List<FenDian> fendianList = fenDianService.list(map );
		
		User user = userService.findById_(id);
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("fendianId", user.getWeiXinUserInfo().getFendianId());
		mav.addObject("userId", id);
		mav.addObject("url", "/admin/wxuser/update");
		mav.addObject("fendianList", fendianList);
		mav.setViewName("admin/page/user/set_fendian");
		return mav;
	}
	
	
	
	
}
