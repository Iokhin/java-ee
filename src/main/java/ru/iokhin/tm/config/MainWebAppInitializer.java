package ru.iokhin.tm.config;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.*;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.iokhin.tm.endpoint.soap.ProjectEndpoint;
import ru.iokhin.tm.endpoint.soap.TaskEndpoint;
import ru.iokhin.tm.endpoint.soap.UserEndpoint;

import javax.xml.ws.Endpoint;

@Configuration
@EnableWebMvc
@Import(DataSourceConfiguration.class)
public class MainWebAppInitializer extends AbstractSecurityWebApplicationInitializer {

//    ALTERNATIVE WAY TO PUBLISH ENDPOINT
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

//    @Bean
//    public Server project() {
//
//        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
//        endpoint.setBus(springBus());
//        endpoint.setAddress("/project");
//        endpoint.setServiceBean(new ProjectEndpoint()); //через new неверно
//        return endpoint.create();
//    }

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
