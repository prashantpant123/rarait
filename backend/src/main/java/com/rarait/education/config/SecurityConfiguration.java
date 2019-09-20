package com.rarait.education.config;

import com.rarait.education.login.auth.*;
import com.rarait.education.login.impl.RoleName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailService userDetailService;
    private final JwtAuthEntryPoint unauthorizedHandler;
    private final JwtProvider jwtProvider;


    @Autowired
    public SecurityConfiguration(CustomUserDetailService userDetailService,
                                 JwtAuthEntryPoint unauthorizedHandler,
                                 JwtProvider jwtProvider) {
        this.userDetailService = userDetailService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtProvider = jwtProvider;
    }

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter(jwtProvider, userDetailService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/css/**", "/js/**", "/images/**", "/assets/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/admin/institute/**/file/**").permitAll()
                .antMatchers("/api/education/**/file/**").permitAll()
                .antMatchers("/api/education/**/pdf/**").permitAll()
                .antMatchers("/api/admin/**").hasAuthority(RoleName.ROLE_ADMIN.name())
                .antMatchers("/api/education/**").hasAuthority(RoleName.ROLE_INSTITUTE_ADMIN.name())
                .antMatchers("/api/utility/**").hasAnyAuthority(RoleName.ROLE_ADMIN.name(), RoleName.ROLE_INSTITUTE_ADMIN.name())
//                .antMatchers("/swagger-ui.html", "/monitor/application-health").hasAuthority(RoleName.ROLE_ADMIN.toString())
                .and()
//                .formLogin().loginPage("/login").permitAll()
//                .successHandler(authSuccessHandler)
//                .failureHandler(authFailureHandler)
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
//                .logoutSuccessUrl("/login?logout")
//                .and()
//                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
                .ignoring()
                .antMatchers("/static/**", "/css/**",
                        "/js/**", "/images/**", "/swagger-ui.html", "webapps", "/assets/**");
    }
}