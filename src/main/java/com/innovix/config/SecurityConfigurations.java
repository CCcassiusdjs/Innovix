package com.innovix.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Security configuration class for Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;

    @Autowired
    public SecurityConfigurations(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    /**
     * Configures the security filter chain.
     *
     * @param httpSecurity the HttpSecurity to configure
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/login/customer").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/login/employee").hasAuthority("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/login/registerCustomer").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login/registerEmployee").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/products").hasAuthority("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/payment-methods").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/products").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/addresses").hasAnyAuthority("CUSTOMER", "EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/addresses").hasAuthority("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/categories").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/categories").hasAuthority("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/orders/customer/**").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/orders/employee/**").hasAuthority("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/orders").hasAnyAuthority("CUSTOMER", "EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/orders").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/payment-methods/**").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/promotions").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/promotions").hasAuthority("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/shopping-carts/**").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/stores/**").hasAnyAuthority("CUSTOMER", "EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/stores/**").hasAuthority("EMPLOYEE")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    /**
     * Configures the authentication manager.
     *
     * @param configuration the authentication configuration
     * @return the configured AuthenticationManager
     * @throws Exception if an error occurs
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Configures the password encoder.
     *
     * @return the configured PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the CORS settings.
     *
     * @return the configured CorsConfigurationSource
     */
    @Bean
    protected CorsConfigurationSource configurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
