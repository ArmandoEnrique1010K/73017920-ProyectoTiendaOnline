package com.tiendaonline.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // PARA PRUEBAS
    @Bean
    public InMemoryUserDetailsManager UserDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("password").authorities("ADMIN").build();
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").authorities("USER").build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpsecurity) throws Exception {
        httpsecurity
                .csrf().disable()
                .authorizeHttpRequests(
                        authorize -> authorize.antMatchers("/productos/nuevo").hasAuthority("ADMIN")
                                .anyRequest().permitAll()
                )
                .formLogin(
                        form -> form.loginPage("/login").permitAll()
                                .loginProcessingUrl("/login")
                                .failureUrl("/login?error")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/")
                )
                .logout(
                        logout -> logout
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/login?logout").permitAll()
                );

        return httpsecurity.build();
    }

}
