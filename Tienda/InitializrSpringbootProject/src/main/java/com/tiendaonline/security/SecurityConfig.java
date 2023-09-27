package com.tiendaonline.security;

import com.tiendaonline.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private PasswordEncoderProvider passwordEncoder;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usuarioService);
        auth.setPasswordEncoder(passwordEncoder.passwordEncoder());
        return auth;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpsecurity) throws Exception {
        httpsecurity
                .csrf().disable()
                .authorizeHttpRequests(
                        authorize -> authorize.antMatchers(
                                "/productos/nuevo", 
                                "/productos/editar/**",
                                "/productos/cambioestadoafalse/**",
                                "/productos/cambioestadoatrue/**",
                                "/productos/eliminardefinitivamente/**"
                                ).hasAuthority("ROLE_ADMIN")
                                /*carrito de compras*/
                                .antMatchers("/cart").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                                /**/
                                .anyRequest().permitAll()
                )
                .formLogin(
                        form -> form.loginPage("/login")
                                .loginProcessingUrl("/login")
                                .failureUrl("/login?error")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/")
                                .permitAll()
                )
                .logout(
                        logout -> logout
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/login?logout")
                                .permitAll()
                );

        return httpsecurity.build();
    }

}
/*
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
*/

    // PARA PRUEBAS
    /*
    @Bean
    public InMemoryUserDetailsManager UserDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("password").authorities("ADMIN").build();
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").authorities("USER").build();
        return new InMemoryUserDetailsManager(admin, user);
    }
     */
