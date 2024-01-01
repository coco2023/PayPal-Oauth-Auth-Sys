package com.umiuni.shop.auth.config;

import com.umiuni.shop.auth.component.CustomAuthenticationSuccessHandler;
import com.umiuni.shop.auth.service.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/login").permitAll()
                .antMatchers("/", "/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/login/success", true)  // Redirect after successful login
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(customAuthenticationSuccessHandler);
    }
}

//                .defaultSuccessUrl("/loginSuccess")
//                .failureUrl("/loginFailure");


//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/", "/auth/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .oauth2Login()
//                .loginPage("/login")
//                .defaultSuccessUrl("/auth/login/success", true)
//                .failureUrl("/auth/login/failure");
