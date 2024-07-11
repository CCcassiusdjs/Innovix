package com.innovix.config;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.innovix.dto.AddressDTO;
import com.innovix.dto.CategoryDTO;
import com.innovix.dto.OrderDTO;
import com.innovix.entity.Person;
import com.innovix.mapper.AddressMapper;
import com.innovix.mapper.CategoryMapper;
import com.innovix.mapper.OrderMapper;
import com.innovix.usecase.CustomerUseCase;
import com.innovix.usecase.EmployeeUseCase;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;

    @Autowired
    public SecurityConfigurations(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        //Registro de Cliente e Funcionario
                        .requestMatchers(HttpMethod.POST, "/api/persons/registerCustomer").permitAll()  
                        .requestMatchers(HttpMethod.POST, "/api/persons/registerEmployee").permitAll()  
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        
                        // Produtos
                        .requestMatchers(HttpMethod.GET, "/api/products").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/products/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/products").hasAuthority("EMPLOYEE")
                        .requestMatchers(HttpMethod.DELETE, "/api/products/***").hasAuthority("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/products/name/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/products/category/**").permitAll()
                        //.requestMatchers(HttpMethod.GET, "/api/products/price-range").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/products/size/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/products/material/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/products/promotion/*").permitAll()
                        
                        // CEP
                        .requestMatchers(HttpMethod.GET, "/api/cep/**").permitAll()
                        
                        // Shopping Cart
                        .requestMatchers(HttpMethod.GET, "/api/shopping-carts").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/api/shopping-carts").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/shopping-carts/**").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/shopping-carts/customer/*").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.DELETE, "/api/shopping-carts/*").hasAuthority("CUSTOMER")
                        
                        // Address
                        .requestMatchers(HttpMethod.GET, "/api/addresses").permitAll() 
                        .requestMatchers(HttpMethod.POST, "/api/addresses").permitAll() // 'EMPLOYEE'
                        .requestMatchers(HttpMethod.GET, "/api/addresses/***").permitAll() 
                        .requestMatchers(HttpMethod.GET, "/api/addresses/city/***").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/addresses/state/***").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/addresses/country/***").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/addresses/person/***").permitAll()
                        // .requestMatchers(HttpMethod.DELETE, "/api/addresses/***").permitAll() // 'EMPLOYEE'
                        
                        // Order
                        .requestMatchers(HttpMethod.GET, "/api/orders").permitAll() 
                        .requestMatchers(HttpMethod.POST, "/api/orders").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/orders/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/orders/status/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/orders/customer/**").hasAuthority("CUSTOMER")
                        // .requestMatchers(HttpMethod.GET, "/api/orders/date-range").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/orders/product/*").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/orders/**").hasAuthority("EMPLOYEE")
                        
                        // Category
                        .requestMatchers(HttpMethod.GET, "/api/categories").hasAuthority("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/categories").hasAuthority("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/categories/**").hasAuthority("EMPLOYEE")
                        .requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasAuthority("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/categories/name/**").hasAuthority("EMPLOYEE")


                        ////Outros
                        .requestMatchers(HttpMethod.POST, "/api/payment-methods").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/categories").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/orders/employee/**").hasAuthority("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/orders").hasAnyAuthority("CUSTOMER", "EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/payment-methods/**").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/promotions").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/promotions").hasAuthority("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/stores/**").hasAnyAuthority("CUSTOMER", "EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/stores/**").hasAuthority("EMPLOYEE")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected CorsConfigurationSource configurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
