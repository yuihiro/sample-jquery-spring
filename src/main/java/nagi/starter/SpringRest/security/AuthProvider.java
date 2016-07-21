package nagi.starter.SpringRest.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import nagi.starter.SpringRest.common.bean.LogBean;
import nagi.starter.SpringRest.repository.UserRepository;

@Component(value = "authProvider")
public class AuthProvider implements AuthenticationProvider {

	@Autowired
	public UserRepository dao;

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

		String id = (String) token.getName();
		String pwd = (String) token.getCredentials();

		LogBean.log.info(id);
		LogBean.log.info(pwd);

		Map info = dao.selectOneById(id);
		if (info == null) {
			throw new UsernameNotFoundException(id);
		}

		if (pwd.equals(info.get("id"))) {
			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			roles.add(new SimpleGrantedAuthority("ROLE_USER"));
			UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(id, pwd, roles);
			return result;
		} else {
			throw new BadCredentialsException("Bad credentials");
		}
	}
}