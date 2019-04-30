package com.slin.study.client.config;




import com.slin.study.client.model.UserInfo;
import com.slin.study.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@SuppressWarnings("ALL")
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService,
		AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {
	@Autowired
	private UserService userService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo user = userService.findByUsername(username);
		return user;
	}

	/**
	 * 实现AuthenticationUserDetailsService的方法，
	 * 用于获取cas server返回的用户信息，再根据用户关键信息加载出用户在当前系统的权限
	 * @param token
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
		String name = token.getName();
		System.out.println("获得的用户名："+name);
		UserInfo user = userService.findByUsername(name);
		if (user==null){
			throw new UsernameNotFoundException(name+"不存在");
		}
		return user;
	}
}

