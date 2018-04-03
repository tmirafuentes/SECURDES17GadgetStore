package edu.dlsu.securdeproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
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
                             "/admin-prods",
                             "/admin-trans", 
                             "/admin-users",
                             "/admin-audit-trail",
                             "/add-product", 
                             "/edit-product",
                             "/delete-product"
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
                .maximumSessions(2)
                .expiredUrl("/session-expired")
                .invalidSessionUrl("/invalid-session")
                .sessionFixation().migrateSession();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
