package ru.iokhin.tm.boot.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;

@Configuration
public class PrettyFilterConfig {

    @Bean
    public FilterRegistrationBean prettyFilter() {
        FilterRegistrationBean prettyFilter = new FilterRegistrationBean(new com.ocpsoft.pretty.PrettyFilter());
        prettyFilter.setDispatcherTypes(DispatcherType.FORWARD, DispatcherType.REQUEST,
                DispatcherType.ASYNC, DispatcherType.ERROR);
        prettyFilter.addUrlPatterns("/*");
        return prettyFilter;
    }

}
