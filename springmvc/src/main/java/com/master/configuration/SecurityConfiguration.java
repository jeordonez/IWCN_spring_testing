package com.master.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public CustomAuthenticationProvider authenticationProvider;
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        // Paths that can be visited without authentication
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();

        // Paths that cannot be visited without authentication
        http.authorizeRequests().anyRequest().authenticated();
        	
        // Login form...
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("user");
        http.formLogin().passwordParameter("pass");
        http.formLogin().defaultSuccessUrl("/home");
        http.formLogin().failureUrl("/error?errorp");
        http.formLogin().and().exceptionHandling().accessDeniedPage("/denied");
        
        // Logout...
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/login?logout");
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)  throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

}







