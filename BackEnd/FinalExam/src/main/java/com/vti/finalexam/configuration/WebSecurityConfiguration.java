package com.vti.finalexam.configuration;

import com.vti.finalexam.configuration.jwt.AuthEntryPointJwt;
import com.vti.finalexam.configuration.jwt.AuthTokenFilter;
import com.vti.finalexam.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private AuthEntryPointJwt authEntryPoint;

    @Bean
    public AuthTokenFilter createAuthTokenFilter(){
        return new AuthTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //    Encode pass word
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(accountService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and()
//                .authorizeRequests().antMatchers("/auth/**").permitAll()
//                .antMatchers("/accounts/v1/**").hasAnyAuthority("ADMIN")
//                .antMatchers("/departments/v1/**").hasAnyAuthority("ADMIN")
//                .anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(authEntryPoint)
//                .and().httpBasic()
//                .and().csrf().disable();
        http.cors().and()
                .authorizeRequests().antMatchers("/auth/**").permitAll()
                .antMatchers("/accounts/v1/**").permitAll()
                .antMatchers("/departments/v1/**").permitAll()
                .anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(authEntryPoint)
                .and().httpBasic()
                .and().csrf().disable();

        http.addFilterBefore(createAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
