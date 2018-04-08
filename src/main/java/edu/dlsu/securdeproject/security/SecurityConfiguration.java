package edu.dlsu.securdeproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**", 
                             "/js/**", 
                             "/images/**",
                             "/", 
                             "/welcome", 
                             "/index",
                             "/signup", 
                             "/signin",
                             "/desktops", 
                             "/laptops", 
                             "/mobiles",
                             "/tablets", 
                             "/view-product"
                            ).permitAll()
                .antMatchers("/account", 
                             "/editAccount",
                             "/buy-product", 
                             "/thank-you",
                             "/purchases"           
                            ).hasRole("USER")
                .antMatchers("/admin", 
                             "/admin/products",
                             "/admin/trans",
                             "/admin/users",
                             "/admin-audit-trail",
                             "/admin/add-product",
                             "/admin/edit-product",
                             "/admin/delete-product"
                             ).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/signin")
                .defaultSuccessUrl("/")
                .failureUrl("/signin?error=true")
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/signin")
                .permitAll()
                .and()
            .sessionManagement()
                .sessionFixation().migrateSession()
                .maximumSessions(2)
                .expiredUrl("/session-expired");
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        //auth.authenticationProvider(authenticationProvider);
    }
}
