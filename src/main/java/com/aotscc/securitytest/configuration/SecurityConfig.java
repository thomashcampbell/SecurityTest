package com.aotscc.securitytest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        //User Role
        UserDetails theUser = User.withUsername("utils")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .password("utils").roles("USER").build();


        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(theUser);

        return userDetailsManager;
    }


//    @Bean
//    public SecurityFilterChain actuatorFilterChain(HttpSecurity http) throws Exception {
//
//        /* Allows any request with "/actuator" and authenticated by Basic Auth
//        http.authorizeHttpRequests()
//                .requestMatchers("/actuator/**")
//                .authenticated()
//                .and()
//                .httpBasic();
//         */
//
//        /* Allows any request with "/actuator" without authentication */
//        http.authorizeHttpRequests((authorization) -> authorization
//                .requestMatchers("/actuator/health")
//                .permitAll());
//
//        return http.build();
//    }
//

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        /* Ignore security for anything starting with /actuator
        return (web) -> web.ignoring().requestMatchers("/actuator/**");
         */

        /* Ignore security for anything starting with /actuator/health */
        return (web) -> web.ignoring().requestMatchers("/actuator/health", "/actuator/info");
    }

    @Bean
    @Order(3)
    public SecurityFilterChain actuatorFilterChain(HttpSecurity http) throws Exception {

        /* Allows any request with "/path" and authenticated by Basic Auth */
        http.authorizeHttpRequests((authorization) -> {
            try {
                authorization
                        .requestMatchers("/actuator/**")
                        .authenticated().and().httpBasic();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        /* Allows any request with "/path" and authenticated by Basic Auth */
        http.authorizeHttpRequests((authorization) -> {
            try {
                authorization
                        .requestMatchers("/path/**")
                        .authenticated().and().httpBasic();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        /* Allows any request with "/path" and authenticated by Basic Auth
        http.authorizeHttpRequests()
                .requestMatchers("/path/**")
                .authenticated()
                .and()
                .httpBasic();
         */

        /* Allows everything
        http.authorizeHttpRequests()
                .requestMatchers("/**")
                .permitAll();
         */


        /* Allows any request with path "/path"
        http.authorizeHttpRequests()
                .requestMatchers("/path/**").permitAll()
                .anyRequest().authenticated();
        */


        /**************************/

        /*
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

         */

        return http.build();
    }
}
