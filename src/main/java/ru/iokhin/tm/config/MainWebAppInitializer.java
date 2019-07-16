package ru.iokhin.tm.config;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import ru.iokhin.tm.endpoint.ProjectEndpoint;
import ru.iokhin.tm.endpoint.TaskEndpoint;

import javax.xml.ws.Endpoint;

@Configuration
@Import(DataSourceConfiguration.class)
public class MainWebAppInitializer extends AbstractSecurityWebApplicationInitializer {

//    ALTERNATIVE WAY TO PUBLISH ENDPOINT
//    @Bean(name = Bus.DEFAULT_BUS_ID)
//    public SpringBus springBus() {
//        return new SpringBus();
//    }

//    @Bean
//    public ProjectEndpoint projectEndpointImpl() {
//        return new ProjectEndpoint();
//    }
//
//    @Bean
//    public Endpoint projectEndpoint() {
//        EndpointImpl endpoint = new EndpointImpl(springBus(), projectEndpointImpl());
//        endpoint.publish("/services/projectEndpoint");
//        return endpoint;
//    }

//    @Bean
//    public TaskEndpoint taskEndpointImpl() {
//        return new TaskEndpoint();
//    }
//
//    @Bean
//    public Endpoint taskEndpoint() {
//        EndpointImpl endpoint = new EndpointImpl(springBus(), taskEndpointImpl());
//        endpoint.publish("/services/taskEndpoint");
//        return endpoint;
//    }

}
