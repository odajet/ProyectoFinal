package com.salesianostriana.dam.forowow.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioRepo usuarios;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .anonymous().and()
        .csrf().disable()
        .authorizeRequests()
      
        .antMatchers("/hilos/**", "/usuarios/**", "/categoria/**", "/formulario-hilo/**", "/hilo/**", 
        		"/perfil/**", "/responder-hilo-formulario/**", "/formulario-categoria/**", 
        		"/crear-categoria/**", "/eliminar-categoria/**", "/editar-categoria-formulario/**", 
        		"/actualizar-categoria/**", "/hilos-ordenar-fecha/**", "/crear-hilo/**", 
        		"/eliminar-hilo/**", "/editar-hilo-formulario/**", "/actualizar-hilo/**", 
        		"/dar-like/**", "/crear-mensaje/**", "/eliminar-mensaje/**", "/editar-mensaje-formulario/**", 
        		"/actualizar-mensaje/**", "/logout/**", "/usuarios-ordenar-puntos/**", "/buscar-todo/**").authenticated()
        .antMatchers("/hilos/**", "/usuarios/**", "/formulario-categoria/**", "/crear-categoria/**",
        		"/eliminar-categoria/**", "/editar-categoria-formulario/**", "/actualizar-categoria/**",
        		"/hilos-ordenar-fecha/**", "/eliminar-hilo/**", "/editar-hilo-formulario/**",
        		"/actualizar-hilo/**", "/eliminar-mensaje/**", "/editar-mensaje-formulario/**", 
        		"/actualizar-mensaje/**", "/usuarios-ordenar-puntos/**").hasRole("ADMIN")
        .antMatchers("/**", "/login/**", "/index", "/autenticar-usuario/**", "login-error/**").permitAll()
       
        .anyRequest().authenticated()
        .and().exceptionHandling().accessDeniedPage("/index")
        .and().formLogin().defaultSuccessUrl("/")
        .and().formLogin().loginPage("/login").loginProcessingUrl("/autenticar-usuario").failureUrl("/login-error").permitAll()
        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll()
        .and().headers().frameOptions().disable();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        usuarios.getUsuarios()
                .stream()
                .map(u -> {
                    return User
                            .withUsername(u.getUsername())
                            .password("{noop}"+ u.getPassword())
                            .roles(u.getRole())
                            .build();

                })
                .forEach(userDetailsManager::createUser);


        return userDetailsManager;


    }
}