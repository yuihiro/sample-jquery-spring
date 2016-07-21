package nagi.starter.SpringRest.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import nagi.starter.SpringRest.common.bean.LogBean;

@Component(value = "authSuccessHandler")
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		System.out.println("성공");

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LogBean.log.info("login_success : " + "/" + user.getUsername() + "/" + user.getPassword());
		//session.setAttribute("auth_info", user);

		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print("success");
		response.getWriter().flush();
	}
}
