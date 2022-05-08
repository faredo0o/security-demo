package com.faredo0o.securitydemo.security;

import com.faredo0o.securitydemo.jwt.JwtConfig;
import com.faredo0o.securitydemo.jwt.JwtTokenVerifier;
import com.faredo0o.securitydemo.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;

import static com.faredo0o.securitydemo.security.UserRoles.*;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
   private PasswordEncoder passwordEncoder;
   private final JwtConfig jwtConfig;
   private final SecretKey secretKey;
@Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, JwtConfig jwtConfig, SecretKey secretKey) {
        this.passwordEncoder = passwordEncoder;
    this.jwtConfig = jwtConfig;
    this.secretKey = secretKey;
}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(),jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig,secretKey),JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                .antMatchers("/api/*").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated();


    }


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails essam= User.builder()
                .username("essam")
                .password(passwordEncoder.encode("password"))
                //.roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        UserDetails Omar=User.builder()
                .username("omar")
                .password(passwordEncoder.encode("password123"))
               // .roles(STUDENT.name())
                .authorities(STUDENT.getGrantedAuthorities())
                .build();
        UserDetails Ali=User.builder()
                .username("ali")
                .password(passwordEncoder.encode("password123"))
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
                //.roles(ADMINTRAINEE.name())
                .build();
        List<UserDetails> users=new ArrayList<>();
        users.add(essam);
        users.add(Omar);
        users.add(Ali);
        return new InMemoryUserDetailsManager(users);
    }
}
