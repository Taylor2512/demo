package com.puertto.demo;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.enums.*;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.models.info.*;

@SpringBootApplication
// @OpenAPIDefinition(info = @Info(title = "PuerttoApi", version = "2.0",
// description = "MarketPlace",contact = "","",""))

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

@OpenAPIDefinition(info = @Info(title = "Code-First Approach (reflectoring.io)", description = "" +
		"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et rhoncus quam. Aenean quis augue ac eros pulvinar malesuada. "
		+
		"In sagittis elit egestas tincidunt iaculis. " +
		"Donec eu lacus vitae nulla varius consectetur a vel quam. Aliquam erat volutpat. Duis eget ullamcorper tellus", contact = @Contact(name = "Reflectoring", url = "https://reflectoring.io", email = "petros.stergioulas94@gmail.com"), license = @License(name = "MIT Licence", url = "https://github.com/thombergs/code-examples/blob/master/LICENSE")), servers = @Server(url = "http://localhost:8080"))
@SecurityScheme(name = "api", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
class OpenAPIConfiguration {
}

@EnableWebSecurit
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/unsecured", "/swagger-ui/**", "/reflectoring-openapi/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("admin")
				.password(passwordEncoder().encode("password"))
				.authorities("ADMIN");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
