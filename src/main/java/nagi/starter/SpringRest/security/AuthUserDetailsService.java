package nagi.starter.SpringRest.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import nagi.starter.SpringRest.common.bean.LogBean;
import nagi.starter.SpringRest.repository.UserRepository;

@Component(value = "authService")
public class AuthUserDetailsService implements UserDetailsService {

	@Autowired
	public UserRepository dao;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		Map info = dao.selectOneById(username);
		if (info == null) {
			throw new UsernameNotFoundException(username);
		}

		LogBean.log.info(info.get("id"));
		LogBean.log.info(info.get("password"));

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		User user = new User(username, info.get("password").toString(), authorities);
		return user;
	}
}