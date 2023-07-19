package com.example.monara_backend.Configuration;

import com.example.monara_backend.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JWTAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors();
        // Disable Cross-Site Request Forgery (CSRF)
        http.csrf().disable();
        // Set the session creation policy to stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/reports/**").permitAll()
                .requestMatchers("/api/GIN/**").permitAll()
                .requestMatchers("/api/GRN/**").permitAll()

//                .requestMatchers("/api/user/**").permitAll()
                .requestMatchers("/api/user/admin").hasRole(Role.ADMIN.name())
                .requestMatchers("/api/user/inventory_admin").hasRole(Role.INVENTORY_ADMIN.name())
                .requestMatchers("/api/user/stock_manager").hasRole(Role.STOCK_MANAGER.name())
                .requestMatchers("/api/user/purchase_coordinator").hasRole(Role.PURCHASE_COORDINATOR.name())
                .requestMatchers("/api/user/stock_keeper").hasRole(Role.STOCK_KEEPER.name())
                .requestMatchers("/api/user/designer").hasRole(Role.DESIGNER.name())
                .requestMatchers("/api/user/showroom_manager").hasRole(Role.SHOWROOM_MANAGER.name())


                .requestMatchers("api/bill/**").permitAll()
                .requestMatchers("api/billdata/**").permitAll()
                .requestMatchers("api/billmail/**").permitAll()
                .requestMatchers("api/admin/**").permitAll()
                .requestMatchers("api/showroom/**").permitAll()
                .requestMatchers("api/designer/**").permitAll()



                .requestMatchers("/api/product/**").permitAll()

                .requestMatchers("/api/file/**").permitAll()
                .requestMatchers("/api/add/**").permitAll()


                .requestMatchers("/api/v1/category/**").permitAll()
                .requestMatchers("/api/purchaseOrder/**").permitAll()
                .requestMatchers("/api/sellOrder/**").permitAll()

                .requestMatchers("/api/forecasting/grn/**").permitAll()
                .requestMatchers("/api/forecasting/gin/**").permitAll()

                .requestMatchers("/api/backup/**").permitAll()





                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
