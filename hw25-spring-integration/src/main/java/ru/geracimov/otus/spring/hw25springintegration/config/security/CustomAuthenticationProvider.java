package ru.geracimov.otus.spring.hw25springintegration.config.security;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final PasswordEncoder pe;
    private final UserDetailsService uds;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getPrincipal()
                                    .toString();
        String password = authentication.getCredentials()
                                        .toString();
        val userDetails = uds.loadUserByUsername(name);
        if (!pe.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Login/password is incorrect!");
        }
        if (!(userDetails.isAccountNonExpired()
                && userDetails.isAccountNonLocked()
                && userDetails.isCredentialsNonExpired()
                && userDetails.isEnabled())) {
            throw new AccountExpiredException("Account status incorrect!");
        }
        return new UsernamePasswordAuthenticationToken(
                name, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
