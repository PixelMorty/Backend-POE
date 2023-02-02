package canard.intern.post.following.backend.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthenticationConfig {

	

	  @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    return http
	    	.cors()
	    	// CSRF
	    	.and()
	    	.csrf()
	    	.disable() // for dev only
	    	// authorizations
	    	.authorizeRequests()
	        .antMatchers("/login", "/public/**").permitAll()
	        .anyRequest().authenticated()
	        // authentication
	        .and()
	        .httpBasic()
	        // session
	        .and()
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        // finalize chain
	    	.and()
	   		.build();
	  }

	  @Bean
	  public InMemoryUserDetailsManager userDetailsService() {
	    UserDetails user = User
	        .withUsername("user")
	        .password(passwordEncoder().encode("password"))
	        .roles("USER_ROLE")
	        .build();
	    return new InMemoryUserDetailsManager(user);
	  }

	  @Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder(8);
	  }
}

