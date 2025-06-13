package sdacademy.auctionsiteproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SpringSecurity(UserDetailsService userDetailsService)
    {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/users/**").permitAll()
//                        .requestMatchers(HttpMethod.DELETE,"/users/**").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .httpBasic();
//        return http.build();

        //---------------------

//        http
//                .cors() // ✅ activează CORS
//                .and()
//                .csrf().disable()
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/users/**").permitAll()
//                        .requestMatchers("/categories/**").permitAll()
//                        .requestMatchers("/auctions/**").permitAll()
//                        .requestMatchers("/currentBits/**").permitAll()
//                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(); // ✅ activează autentificarea BASIC
//
//        return http.build();

        //---------------------

        http.csrf().disable();

        http
                .authorizeHttpRequests((auth) -> auth
                        .anyRequest().permitAll()
                )
                .httpBasic();

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
