package com.alolorsus.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/collector/usuario/login").permitAll()
				//ESTO ESTA MUY MAL HABRA QUE ACEPTAR SOLO LOS DATOS DE IOT CENTRAL
				// .antMatchers(HttpMethod.POST, "/ecomove/v0.1/infraccion", "/ecomove/v0.1/telemetria").permitAll()
				/*
				 * .antMatchers(HttpMethod.GET, "/ecomove/v0.1/usuarios",
				 * "/ecomove/v0.1/usuarios/pg/{pagina}", "/ecomove/v0.1/usuarios/{id}",
				 * "/ecomove/v0.1/usuario").hasAnyRole("USER", "ADMIN")
				 * .antMatchers(HttpMethod.POST,"/ecomove/v0.1/vehiculo").hasRole("ADMIN")
				 * .antMatchers(HttpMethod.GET,"/ecomove/v0.1/dispositivo",
				 * "/ecomove/v0.1/usuarios", "/ecomove/v0.1/vehiculo").permitAll()
				 * .antMatchers(HttpMethod.POST,"/ecomove/v0.1/dispositivo",
				 * "/ecomove/v0.1/usuarios", "/ecomove/v0.1/vehiculo").permitAll()
				 * .antMatchers(HttpMethod.GET, "/ecomove/v0.1/usuarios/pg/{pagina}",
				 * "/ecomove/v0.1/usuarios/{id}", "/ecomove/v0.1/usuario").hasAnyRole("USER",
				 * "ADMIN") .antMatchers("/ecomove/v0.1/**").hasRole("ADMIN") /*
				 * .antMatchers(HttpMethod.POST, "/ecomove/v0.1/usuario").hasRole("ADMIN")
				 * .antMatchers(HttpMethod.PUT, "/ecomove/v0.1/usuario/{id}").hasRole("ADMIN")
				 * .antMatchers(HttpMethod.DELETE,
				 * "/ecomove/v0.1/usuarios/{id}").hasRole("ADMIN")
				 */
				.anyRequest().authenticated().and().cors().configurationSource(corsConfigurationSource());
	}

	// Aqui dejamos que acceda angular desde nuestro angular en localhost, cuando
	// este subido habra que probablemente modificar este valor
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(
				new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
