package org.osiam.example.hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * This class contains the configuration about the web security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private OsiamAccessDecisionManager decisionManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .accessDecisionManager(decisionManager)
                .antMatchers("/profile").fullyAuthenticated()
                .antMatchers("/search").fullyAuthenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(getAuthenticationEntryPoint());
    }

    private AuthenticationEntryPoint getAuthenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/login");
    }
}
