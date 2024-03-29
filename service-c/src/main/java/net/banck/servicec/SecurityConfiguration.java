package net.banck.servicec;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .mvcMatchers("/info").hasAuthority("SCOPE_info.read")
            .anyRequest().authenticated()
            .and()
            .httpBasic().disable()
            //.oauth2Client()
            //.and()
            .oauth2ResourceServer()
            .jwt();
    }
}
