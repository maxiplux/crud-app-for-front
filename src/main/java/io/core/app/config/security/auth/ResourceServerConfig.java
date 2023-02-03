package io.core.app.config.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()

                .antMatchers(HttpMethod.GET,

                        "/common/**", "/v2/api-docs", "/configuration/ui", "/swagger-resources",
                        "/configuration/security", "/swagger-ui.html", "/webjars/**",
                        "/auth/singin/**",
                        "/metrics/**",
                        "/manage/**",
                        "/health/**", "/info/**",
                        "/api/clientes", "/api/clientes/page/**", "/api/uploads/img/**", "/images/**",

                        "/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-ui.html",
                        "/webjars/**",

                        "/v3/api-docs/**",
                        "/v3/**",
                        "/v2/**",
                        "/auth/token/**",
                        "/swagger-resources/**"

                )
                /*.antMatchers(HttpMethod.GET, "/api/clientes/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/clientes/upload").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
                .antMatchers("/api/clientes/**").hasRole("ADMIN")*/
                .permitAll()

                .antMatchers("/api/v1/password/**")
                .permitAll()
                .antMatchers("/api/v1/auth/**")
                .permitAll()
                .antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
                .permitAll()

                .and().cors().configurationSource(corsConfigurationSource());

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200","https://frontbase-angular-production.up.railway.app"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }


}
