package com.finalproject.appdavc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http

            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "/login*", "/register*")
            .anonymous()
            .antMatchers("/user*").hasRole("ADMIN")
            .antMatchers("/product*").hasAnyRole("ADMIN", "EMPLOYEE")
                .antMatchers("/menu", "/home", "/css/**").permitAll()
                .antMatchers("/css/**", "/images/**").permitAll()
            .anyRequest()
            .authenticated()

            .and()

                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .usernameParameter("emailAddress")
                .and()
                .logout()
                .permitAll();
    }
    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/fonts/**", "/static/**", "/css/**");

    }

}
