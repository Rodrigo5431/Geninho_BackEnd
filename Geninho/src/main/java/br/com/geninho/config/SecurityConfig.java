package br.com.geninho.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.geninho.Security.Jwt;
import br.com.geninho.Security.JwtAuthenticationFilter;
import br.com.geninho.Security.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private Jwt jwt;

	@Autowired
	UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).cors((cors) -> cors.configurationSource(configurationSource()))
				.authorizeHttpRequests(requests -> requests.requestMatchers("/public/").permitAll()

						.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**")
						.permitAll().requestMatchers(HttpMethod.GET, "/products/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/products/**").permitAll()
						.anyRequest().permitAll())

				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.headers((headers) -> headers.disable());

		http.addFilter(new JwtAuthenticationFilter(
				authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwt));
		http.addFilter(new JwtAuthorizationFilter(
				authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwt,
				userDetailsService));
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CorsConfigurationSource configurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));

		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());

		return source;
	}
}