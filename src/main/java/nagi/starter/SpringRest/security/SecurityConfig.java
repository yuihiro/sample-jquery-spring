package nagi.starter.SpringRest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	@Autowired
	@Qualifier("authProvider")
	public AuthenticationProvider authenticationProvider;
	*/

	@Autowired
	@Qualifier("authService")
	public UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		//http.addFilterBefore(filter, CsrfFilter.class);

		http.authorizeRequests().antMatchers("/*/").permitAll();
		//http.formLogin().loginPage("/login").loginProcessingUrl("/login_process").usernameParameter("id").passwordParameter("pwd").successHandler(new AuthSuccessHandler())
		//.failureHandler(new AuthFailureHandler());
		//http.logout().logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("remember-me", "JSESSIONID");
		//http.csrf().disable();
		//http.sessionManagement().invalidSessionUrl("/login_duplicate").maximumSessions(1).expiredUrl("/login_duplicate");
		//http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
	}
}
