// package com.example.demo.infrastructure.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .authorizeHttpRequests() // Reemplaza authorizeRequests() por authorizeHttpRequests()
//             .requestMatchers("/", "/home", "/register-user", "/register-advisor", "/public/**").permitAll() // Las rutas permitidas
//             .anyRequest().authenticated() // Todas las demás rutas requieren autenticación
//             .and()
//             .formLogin()
//                 .loginPage("/login") // Página de login personalizada
//                 .permitAll()
//             .and()
//             .logout()
//                 .permitAll(); // Permite el logout para todos los usuarios
//         return http.build();
//     }
// }