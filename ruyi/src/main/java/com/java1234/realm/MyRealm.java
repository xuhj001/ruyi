package com.java1234.realm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.java1234.entity.Tree;
import com.java1234.entity.User;
import com.java1234.service.TreeService;
import com.java1234.service.UserService;
import com.java1234.util.MyUtil;
import com.java1234.util.StringUtil;

/**
 * �Զ���Realm
 * 
 * @author Administrator
 *
 */
public class MyRealm extends AuthorizingRealm {

	@Resource
	private TreeService treeService;

	@Resource
	private UserService userService;
	
	/**
	 * Ϊ��ǰ�ĵ�¼���û���ɫ��Ȩ��
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// ͨ���û�ȡ����Ӧ��ӵ�е�Ȩ��
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// ���ý�ɫ ���� ���Ŀǰ�ò�����
		// authorizationInfo.setRoles(roles);
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		user = userService.findById_(user.getId_());
		Map<String, Object> map = new HashMap<String, Object>();
		List<Integer> ids = MyUtil.Str_ids_To_ListInteger_ids(user.getWeiXinUserInfo().getTreeIds());
		map.put("ids", ids);
		if(ids.size()>0){
		}else{
			return authorizationInfo;
		}
		List<Tree> treeList = treeService.getTreesByFatherOrIds(map);
		// Ȩ�޼���
		Set<String> stringPermissions = new HashSet<String>();
		for (Tree tree : treeList) {
			if (StringUtil.isNotEmpty(tree.getPermissions())) {
				stringPermissions.add(tree.getPermissions());
			}
		}
		authorizationInfo.setStringPermissions(stringPermissions);
		return authorizationInfo;
	}

	/**
	 * ��֤��ǰ��¼���û�
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		// String login_type = (String)
		// SecurityUtils.getSubject().getSession().getAttribute("login_type");

		String openid = (String) token.getPrincipal();// ������ʹ����openid
		User user = userService.findById_(openid);
		if (user != null) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getId_(), user.getPwd_(), "xxx");
			return authcInfo;
		}

		return null;

	}

}
