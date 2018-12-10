package com.aisat.hkgott.shiro;

import com.aisat.hkgott.entity.Role;
import com.aisat.hkgott.entity.User;
import com.aisat.hkgott.entity.UserRole;
import com.aisat.hkgott.service.IFunctionService;
import com.aisat.hkgott.service.IRoleService;
import com.aisat.hkgott.service.IUserRoleService;
import com.aisat.hkgott.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * shiro身份校验核心类
 * 
 * @author 作者: z77z
 * @date 创建时间：2017年2月10日 下午3:19:48
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService;

	@Autowired
	private IUserRoleService userRoleService;
	
	@Autowired
	private IFunctionService functionService;

	
	@Autowired
	private IRoleService roleService;

	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	//用户登录次数计数  redisKey 前缀
	private String SHIRO_LOGIN_COUNT = "shiro_login_count_";
	
	//用户登录是否被锁定    一小时 redisKey 前缀
	private String SHIRO_IS_LOCK = "shiro_is_lock_";

	/**
	 * 认证信息.(身份验证) : Authentication 是用来验证用户身份
	 * 
	 * @param
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String name = token.getUsername();
		String password = String.valueOf(token.getPassword());
		//访问一次，计数一次
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		opsForValue.increment(SHIRO_LOGIN_COUNT+name, 1);
		//计数大于5时，设置用户被锁定一小时
		if(Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT+name))>=5){
			opsForValue.set(SHIRO_IS_LOCK+name, "LOCK");
			stringRedisTemplate.expire(SHIRO_IS_LOCK+name, 1, TimeUnit.HOURS);
		}
		if ("LOCK".equals(opsForValue.get(SHIRO_IS_LOCK+name))){
			throw new DisabledAccountException("由于密码输入错误次数大于5次，帐号已经禁止登录！");
		}
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_name",name);
		queryWrapper.eq("password",password);
		//密码进行加密处理  明文为  password+name
		String paw = password+name;

		// 从数据库获取对应用户名密码的用户
		User user = userService.getOne(queryWrapper);

		if (null == user) {
			throw new AccountException("帐号或密码不正确！");
//		}else if("0".equals(user.get())){
//			/**
//			 * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
//			 */
//			throw new DisabledAccountException("此帐号已经设置为禁止登录！");
		}else{
			//登录成功
			//更新登录时间 last login time
//			user.get(new Date());
//			sysUserService.updateById(user);
			//清空登录计数
			opsForValue.set(SHIRO_LOGIN_COUNT+name, "0");
		}
		log.info("身份认证成功，登录用户："+name);
		return new SimpleAuthenticationInfo(user, password, getName());
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		System.out.println("权限认证方法：MyShiroRealm.doGetAuthorizationInfo()");
		User user = (User)SecurityUtils.getSubject().getPrincipal();
		String userName = user.getUserName();
        User userSave = userService.getOne(new QueryWrapper<User>().eq("user_name", userName));
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		//根据用户ID查询角色（role），放入到Authorization里。
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", userSave.getUserId());
        Collection<UserRole> userRoles = userRoleService.listByMap(map);
        Set<String> roleSet = new HashSet<String>();
        List<Integer> ids = new ArrayList<>();
		for(UserRole userRole : userRoles){
			ids.add(userRole.getRoleId());
		}

        Collection<Role> roles = roleService.listByIds(ids);

        for (Role role : roles) {
            roleSet.add(role.getRoleUniqueNo());
        }
        Set<String> permissionSet = new HashSet<String>();
        permissionSet.add("权限添加");
        permissionSet.add("权限删除");
        info.addRoles(roleSet);
        return info;
	}
}
