package com.faredo0o.securitydemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

import static com.faredo0o.securitydemo.security.UserPermissions.STUDENT_WRITE;
import static com.faredo0o.securitydemo.security.UserRoles.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
   private PasswordEncoder passwordEncoder;
@Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                .antMatchers("/api/*").hasRole(STUDENT.name())
                .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(STUDENT_WRITE.name())
                .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(STUDENT_WRITE.name())
                .antMatchers(HttpMethod.PUT,"management/api/**").hasAuthority(STUDENT_WRITE.name())
                .antMatchers(HttpMethod.GET,"/management/api/**").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.GET,"/management/api/**").hasRole(ADMINTRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails essam= User.builder()
                .username("Essam")
                .password(passwordEncoder.encode("password"))
                .roles(ADMIN.name())
                .build();
        UserDetails Omar=User.builder()
                .username("omar")
                .password(passwordEncoder.encode("password123"))
                .roles(STUDENT.name())
                .build();
        UserDetails Ali=User.builder()
                .username("ali")
                .password(passwordEncoder.encode("password123"))
                .roles(ADMINTRAINEE.name())
                .build();
        List<UserDetails> users=new ArrayList<>();
        users.add(essam);
        users.add(Omar);
        users.add(Ali);
        return new InMemoryUserDetailsManager(users);
    }
}
