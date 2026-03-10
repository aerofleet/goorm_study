package com.mission.m260303.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.mission.m260303.security.CustomUserDetails;
import java.util.List;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/", "/login", "/product/list", "/css/**", "/js/**", "/images/**").permitAll()
				.requestMatchers("/product/add").hasAnyRole("MANAGER", "ADMIN")
				.requestMatchers("/product/userlist").hasRole("ADMIN")
				.requestMatchers("/product/detail").authenticated()
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/", true)
				.permitAll()
			)
			.logout(logout -> logout
				.logoutSuccessUrl("/")
				.permitAll()
			);

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails user = new CustomUserDetails(
			"user",
			passwordEncoder.encode("user123"),
			"유저",
			List.of(new SimpleGrantedAuthority("ROLE_USER"))
		);
		UserDetails manager = new CustomUserDetails(
			"manager",
			passwordEncoder.encode("manager123"),
			"매니저",
			List.of(new SimpleGrantedAuthority("ROLE_MANAGER"))
		);
		UserDetails admin = new CustomUserDetails(
			"admin",
			passwordEncoder.encode("admin123"),
			"어드민",
			List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
		);
		return new InMemoryUserDetailsManager(user, manager, admin);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
