package de.zooplus.converter.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dragan on 20-Nov-16.
 */
@Configuration
@EnableWebMvcSecurity
@ComponentScan(basePackages = {"de.zooplus.converter"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("ConverterUserDetailsService")
    private UserDetailsService converterUserDetailsService;

    private static final String PASSWORD_SECRET = "secret";

    @Value("${REST_USER}")
    private String restUser;

    @Value("${REST_PASSWORD}")
    private String restPassword;


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(converterUserDetailsService);
        auth.authenticationProvider(authenticationProvider());
        auth.inMemoryAuthentication()
                .withUser(restUser).password(restPassword).authorities("WEBSERVICE_USER");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder(PASSWORD_SECRET);
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(converterUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/resources/**").permitAll()
//                .anyRequest().authenticated()
////                .antMatchers("/", "/login", "/registration").permitAll()
////                .antMatchers("/home/**").hasAnyAuthority("AUTHENTICATED", "ROLE_AUTHENTICATED")
////                .antMatchers("/home/**").access("hasRole('AUTHENTICATED')")
////                .antMatchers("/", "/login", "/registration").permitAll()
//                .and().formLogin().loginPage("/login").permitAll()
////                .usernameParameter("email").passwordParameter("password")
//                .and().csrf().and().logout().permitAll()
//                .and().exceptionHandling().accessDeniedPage("/accessDenied");
        http
                .authorizeRequests()
                .antMatchers("/login", "/registration", "/resources/**")
                .permitAll()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/webservice/**")
//                .hasAnyAuthority("WEBSERVICE_USER")
//                .and()
//                .httpBasic()
                .and()ovaj dio oko resta treba da proradi
                .authorizeRequests()
                .anyRequest()
                .hasAnyAuthority("USER")
                .and()
                .csrf()
                .disable()
                .formLogin()
                .loginPage("/login").usernameParameter("email").passwordParameter("password");

//                .and()
//                .exceptionHandling().accessDeniedPage("/error/accessDenied.jsp");
//                .failureUrl("/login?error=true");
//                .loginProcessingUrl("/login_process");
//                .loginPage("/login")
//                .defaultSuccessUrl("/home")
//                .loginProcessingUrl("/login_process")
//                .and()
//                .logout().logoutSuccessUrl("/login");
    }
}
