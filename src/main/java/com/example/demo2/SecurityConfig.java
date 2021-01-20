package com.example.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo2.rest_controller.UserResource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("example.users")
    @Autowired
    private UserDetailsService userDetailsService;

   @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        // auth.inMemoryAuthentication()// static users
        // .withUser("user").password("123456").roles("USER").and()//
        // .withUser("manager").password("123456").roles("MANAGER").and()//
        // .withUser("admin").password("123456").roles("ADMIN", "MANAGER", "USER");
    }
   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
               .csrf().disable() //Cross-Site Request Forgery disable to API
               .httpBasic() // login with Auth Basic for getting a token
               .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // stateless to API
               .and().addFilter(jwtAuthorizationFilter()).authorizeRequests()
               .antMatchers("/")
               .permitAll();
	/*//Todos los recursos requiren auntentificacion excepto el Login  
	  http
       .csrf().disable() //Cross-Site Request Forgery disable to API
       .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // stateless to API
       .and().addFilter(jwtAuthorizationFilter()).authorizeRequests()
       .antMatchers(HttpMethod.POST, UserResource.LOGIN).permitAll()
       .anyRequest().authenticated();
*/
   
   }
   @Override
   public void configure(WebSecurity web) throws Exception {
       web
           .ignoring()
           .antMatchers("/h2-console/**");
   }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(this.authenticationManager());
    }

}
