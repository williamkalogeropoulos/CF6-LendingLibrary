package com.williamkalogeropoulos.config;

import com.williamkalogeropoulos.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // ✅ Disable CSRF for API requests
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register").permitAll() // ✅ Allow public user registration
                        .requestMatchers("/api/users/**").hasRole("ADMIN") // ✅ Only admins can access user management
                        .requestMatchers("/api/borrowings/my-borrowings").authenticated() // ✅ Users can see their own borrowings
                        .requestMatchers("/api/borrowings/{id}/return").authenticated() // ✅ Users can return their own books
                        .requestMatchers("/api/borrowings/{id}/admin-return").hasRole("ADMIN") // ✅ Only admins can return any book
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // ✅ Defines logout endpoint
                        .logoutSuccessUrl("/login")  // ✅ Redirects to login page
                        .invalidateHttpSession(true)  // ✅ Invalidates session
                        .deleteCookies("JSESSIONID")  // ✅ Deletes session cookie
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }
}
