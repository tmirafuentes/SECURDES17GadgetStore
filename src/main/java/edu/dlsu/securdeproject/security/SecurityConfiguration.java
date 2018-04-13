package edu.dlsu.securdeproject.security;

import edu.dlsu.securdeproject.security.brute_force_prevention.CustomAuthenticationFailureHandler;
import edu.dlsu.securdeproject.security.brute_force_prevention.CustomAuthenticationSuccessHandler;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RequestContextListener requestContextListener() {return new RequestContextListener();}

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
                             "/success",
                             "/signup-confirm",
                             "/signup-success",
                             "/signup-resend-email",
                             "/forgot-password",
                             "/forgot-password-confirm",
                             "/confirm-password",
                             "/reset-password",
                             "/reset-password-success",
                             "/signin",
                             "/desktops",
                             "/laptops",
                             "/mobiles",
                             "/tablets",
                             "/view-product",
                             "/search",
                             "/filter"
                            ).permitAll()
                .antMatchers("/account",
                             "/editAccount",
                             "/buy-product",
                             "/thank-you",
                             "/purchases",
                             "/change-password"
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
                .defaultSuccessUrl("/welcome")
                .failureUrl("/signin?error=true")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/signin")
                .permitAll()
                .and()
            .sessionManagement()
                .sessionFixation().migrateSession()
                .maximumSessions(1)
                .expiredUrl("/signin?expired");
        http.headers()
                .xssProtection()
                .and()
                .cacheControl();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
