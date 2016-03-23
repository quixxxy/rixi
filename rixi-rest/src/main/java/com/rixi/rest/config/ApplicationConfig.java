package com.rixi.rest.config;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.rixi.rest.filter.HeaderFilter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.rixi.rest"})
public class ApplicationConfig
{
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        HeaderFilter securityFilter = new HeaderFilter();
        registrationBean.setFilter(securityFilter);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
