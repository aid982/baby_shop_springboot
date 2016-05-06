package com.osetskiy.baby_shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

import com.osetskiy.baby_shop.config.AuthFailure;
import com.osetskiy.baby_shop.config.AuthSuccess;
import com.osetskiy.baby_shop.config.EntryPointUnauthorizedHandler;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	  @Autowired
	    private AuthFailure authFailure;

	    @Autowired
	    private AuthSuccess authSuccess;

	    @Autowired
	    private EntryPointUnauthorizedHandler unauthorizedHandler;
	
	//@Autowired
	//private DataSource dataSource;
	
	 @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService) throws Exception {
	        auth
	            .userDetailsService(userDetailsService)
	                .passwordEncoder(new BCryptPasswordEncoder());
	    }

	
	 
/*	@Autowired
 * in case i wont ODBC authentification 
	public void configureGlobal(AuthenticationManagerBuilder auth) throws  Exception	{
		auth
		.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(
                    "SELECT  user_table.name,  user_table.password,1 FROM user_table WHERE  user_table.name = ?")
            .authoritiesByUsernameQuery(
                    "SELECT   user_table.name,   user_table_roles.roles_id FROM  public.user_table FULL JOIN  public.user_table_roles on user_table_roles.users_id = user_table.id WHERE  user_table.name = ?");		
		
	}*/
	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http.authorizeRequests()
		//antMatchers("/rest/accounts/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST,"/rest/products/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST,"/rest/categories/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST,"/prodIMG_post/**").hasRole("ADMIN")
		//.antMatchers(HttpMethod.GET, "/rest/categories/**").hasRole("ADMIN")		
		//.antMatchers(HttpMethod.POST, "/cart/**").permitAll()
		.anyRequest().permitAll()		
		.and()
		.exceptionHandling()
        .authenticationEntryPoint(unauthorizedHandler)
        .and()
		.formLogin()
		.successHandler(authSuccess)
        .failureHandler(authFailure)
		.loginPage("/login") 
		.permitAll()
		.and()
		.httpBasic();
		
		http
		.csrf().disable();
	}
	// 
	 @Bean
	    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
	        return new SecurityEvaluationContextExtension();
	    }
	
	

}
