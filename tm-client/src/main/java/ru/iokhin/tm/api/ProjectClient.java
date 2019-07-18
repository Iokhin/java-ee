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
import ru.iokhin.tm.model.ProjectDTO;
import ru.iokhin.tm.model.ResultDTO;

import java.util.List;

@FeignClient("projectClient")
public interface ProjectClient {

    static ProjectClient client(final String baseUrl) {
        final FormHttpMessageConverter converter = new FormHttpMessageConverter();
        final HttpMessageConverters converters = new HttpMessageConverters(converter);
        final ObjectFactory<HttpMessageConverters> objectFactory = () -> converters;
        return Feign.builder().contract(new SpringMvcContract()).encoder(new SpringEncoder(objectFactory))
                .decoder(new SpringDecoder(objectFactory)).target(ProjectClient.class, baseUrl);
    }

    @GetMapping(value = "/findAllByUserId", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProjectDTO> findAllByUserId(@RequestParam(name = "userId") @NotNull String userId);

    @PostMapping(value = "/removeAllByUserId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO removeAllByUserId(@RequestParam(name = "userId") @NotNull String userId);

    @GetMapping(value = "/sortByUserId", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProjectDTO> sortByUserId(@RequestParam(name = "userId") @NotNull String userId,
                                         @RequestParam(name = "parameter") @NotNull String parameter);

    @GetMapping(value = "/findByPartOfNameOrDescription", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProjectDTO> findByPartOfNameOrDescription(@RequestParam(name = "userId") @NotNull String userId,
                                                          @RequestParam(name = "keyWord") @NotNull String keyWord);

    @GetMapping(value = "/findOneByUserId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectDTO findOneByUserId(@RequestParam(name = "userId") @NotNull String userId,
                                      @RequestParam(name = "id") @NotNull String id);

    @PostMapping(value = "/persist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO persist(@RequestBody @NotNull ProjectDTO entity);

    @PostMapping(value = "/merge", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO merge(@RequestBody @NotNull ProjectDTO entity);

    @PostMapping(value = "/removeById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO removeById(@RequestParam(name = "id") @NotNull String id);

    @GetMapping(value = "/findOne", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectDTO findOne(@RequestParam(name = "id") @NotNull String id);

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProjectDTO> findAll();

}
