package com.SpringBoot.SpringSecutiryBasics.Config;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.SpringBoot.SpringSecutiryBasics.filter.AuthoritiesLoggingAfterFilter;
import com.SpringBoot.SpringSecutiryBasics.filter.AuthoritiesLoggingAtFilter;
import com.SpringBoot.SpringSecutiryBasics.filter.CsrfCookieFilter;
import com.SpringBoot.SpringSecutiryBasics.filter.RequestValidationBeforeFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class ProjectSecurityConfig { 
	 @Bean
	    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
	        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
	        requestHandler.setCsrfRequestAttributeName("_csrf");
	        http.securityContext((context) -> context.requireExplicitSave(false))
	                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
	                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
	                    @Override
	                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
	                        CorsConfiguration config = new CorsConfiguration();
	                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
	                        config.setAllowedMethods(Collections.singletonList("*"));
	                        config.setAllowCredentials(true);
	                        config.setAllowedHeaders(Collections.singletonList("*"));
	                        config.setMaxAge(3600L);
	                        return config;
	                    }
	                })).csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact","/register")
	                 .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
	                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
	                .addFilterBefore(new RequestValidationBeforeFilter(),BasicAuthenticationFilter.class)
	                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
                    .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
	                .authorizeHttpRequests((requests)->requests
	                        /*.requestMatchers("/myAccount").hasAuthority("VIEWACCOUNT")
	                        .requestMatchers("/myBalance").hasAnyAuthority("VIEWACCOUNT","VIEWBALANCE")
	                        .requestMatchers("/myLoans").hasAuthority("VIEWLOANS")
	                        .requestMatchers("/myCards").hasAuthority("VIEWCARDS")*/
	                        .requestMatchers("/myAccount").hasRole("USER")
	                        .requestMatchers("/myBalance").hasAnyRole("USER","ADMIN")
	                        .requestMatchers("/myLoans").hasRole("USER")
	                        .requestMatchers("/myCards").hasRole("USER")
	                        .requestMatchers("/user").authenticated()
	                        .requestMatchers("/notices","/contact","/register").permitAll())
	                .formLogin(Customizer.withDefaults())
	                .httpBasic(Customizer.withDefaults());
	        return http.build();
	    }


	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	/*@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		return http.build();

	 */
	/*
	 * @Bean SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
	 * throws Exception { http.authorizeHttpRequests((requests) ->
	 * requests.anyRequest().permitAll()); http.formLogin(withDefaults());
	 * http.httpBasic(withDefaults()); return http.build(); }
	 */
	/*
	 * @Bean public InMemoryUserDetailsManager userDetailsService() {
	 */
			/*
			 * Approach 1
			 * UserDetails admin =
			 * org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder
			 * () .username("admin") .password("12345") .authorities("admin") .build();
			 * UserDetails user =
			 * org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder
			 * () .username("user") .password("12345") .authorities("read") .build(); return
			 * new InMemoryUserDetailsManager(admin, user);
			 */	
			/* Approach 2 where we use NoOpPasswordEncoder Bean */
			/*
			 * UserDetails admin =
			 * org.springframework.security.core.userdetails.User.withUsername("admin")
			 * .password("12345") .authorities("admin") .build(); UserDetails user =
			 * org.springframework.security.core.userdetails.User.withUsername("user")
			 * .password("12345") .authorities("read") .build(); return new
			 * InMemoryUserDetailsManager(admin, user); }
			 */
			/*
			 * @Bean public UserDetailsService userDetailsService(DataSource dataSource) {
			 * return new JdbcUserDetailsManager(dataSource); }
			 */
		

}
	
