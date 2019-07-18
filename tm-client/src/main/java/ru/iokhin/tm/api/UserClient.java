package ru.iokhin.tm.api;

import feign.Feign;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.support.SpringDecoder;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iokhin.tm.model.ResultDTO;
import ru.iokhin.tm.model.UserDTO;

import java.util.List;

@FeignClient("userClient")
public interface UserClient {

    static UserClient client(final String baseUrl) {
        final FormHttpMessageConverter converter = new FormHttpMessageConverter();
        final HttpMessageConverters converters = new HttpMessageConverters(converter);
        final ObjectFactory<HttpMessageConverters> objectFactory = () -> converters;
        return Feign.builder().contract(new SpringMvcContract()).encoder(new SpringEncoder(objectFactory))
                .decoder(new SpringDecoder(objectFactory)).target(UserClient.class, baseUrl);
    }

    @GetMapping(value = "/findByLogin", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDTO findByLogin(@RequestParam(name = "login") @NotNull String login);

    @PostMapping(value = "/persist", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO persist(@RequestBody @NotNull UserDTO entity);

    @PostMapping(value = "/merge", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO merge(@RequestBody @NotNull UserDTO entity);

    @PostMapping(value = "/removeById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO removeById(@RequestParam(name = "id") @NotNull String id);

    @GetMapping(value = "/findOne", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO findOne(@RequestParam(name = "id") @NotNull String id);

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> findAll();

}
