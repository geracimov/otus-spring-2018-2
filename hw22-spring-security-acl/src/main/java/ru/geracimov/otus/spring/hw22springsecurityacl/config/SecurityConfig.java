package ru.geracimov.otus.spring.hw22springsecurityacl.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.geracimov.otus.spring.hw22springsecurityacl.config.security.CustomAuthenticationProvider;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
           .mvcMatchers("/js/**", "/css/**", "/favicon.ico", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
            .disable()
            .cors()
            .disable()
            .authorizeRequests()
//            .antMatchers(HttpMethod.POST, "/**/delete", "/**/edit")
//            .hasAnyAuthority("EDITOR", "ADMIN")
            .antMatchers("/**")
            .authenticated()
            .antMatchers("/h2-console/**")
            .permitAll()
            .and()
            .formLogin();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        AuthenticationProvider provider = new CustomAuthenticationProvider(passwordEncoder(), userDetailsService);
        auth.authenticationProvider(provider);
    }

}
