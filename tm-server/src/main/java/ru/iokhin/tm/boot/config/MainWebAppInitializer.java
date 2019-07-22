package ru.iokhin.tm.boot.config;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import ru.iokhin.tm.boot.endpoint.soap.ProjectEndpoint;
import ru.iokhin.tm.boot.endpoint.soap.TaskEndpoint;
import ru.iokhin.tm.boot.endpoint.soap.UserEndpoint;

import javax.xml.ws.Endpoint;

@Configuration
public class MainWebAppInitializer extends AbstractSecurityWebApplicationInitializer {

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public UserEndpoint userEndpointImpl() {
        return new UserEndpoint();
    }

    @Bean
    public Endpoint userEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), userEndpointImpl());
        endpoint.publish("/userEndpoint");
        return endpoint;
    }

    @Bean
    public ProjectEndpoint projectEndpointImpl() {
        return new ProjectEndpoint();
    }

    @Bean
    public Endpoint projectEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), projectEndpointImpl());
        endpoint.publish("/projectEndpoint");
        return endpoint;
    }

    @Bean
    public TaskEndpoint taskEndpointImpl() {
        return new TaskEndpoint();
    }

    @Bean
    public Endpoint taskEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), taskEndpointImpl());
        endpoint.publish("/taskEndpoint");
        return endpoint;
    }

}
