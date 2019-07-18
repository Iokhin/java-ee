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
import ru.iokhin.tm.model.TaskDTO;

import java.util.List;

@FeignClient("taskClient")
public interface TaskClient {

    static TaskClient client(final String baseUrl) {
        final FormHttpMessageConverter converter = new FormHttpMessageConverter();
        final HttpMessageConverters converters = new HttpMessageConverters(converter);
        final ObjectFactory<HttpMessageConverters> objectFactory = () -> converters;
        return Feign.builder().contract(new SpringMvcContract()).encoder(new SpringEncoder(objectFactory))
                .decoder(new SpringDecoder(objectFactory)).target(TaskClient.class, baseUrl);
    }

    @GetMapping(value = "/findAllByUserId", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDTO> findAllByUserId(@RequestParam(name = "userId") @NotNull String userId);

    @PostMapping(value = "/removeAllByUserId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO removeAllByUserId(@RequestParam(name = "userId") @NotNull String userId);

    @GetMapping(value = "/sortByUserId", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDTO> sortByUserId(@RequestParam(name = "userId") @NotNull String userId,
                                      @RequestParam(name = "parametr") @NotNull String parametr);

    @GetMapping(value = "/findByPartOfNameOrDescription", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDTO> findByPartOfNameOrDescription(@RequestParam(name = "userId") @NotNull String userId,
                                                       @RequestParam(name = "keyWord") @NotNull String keyWord);

    @GetMapping(value = "/findAllByProjectId", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDTO> findAllByProjectId(@RequestParam(name = "userId") @NotNull String userId,
                                            @RequestParam(name = "projectId") @NotNull String projectId);

    @GetMapping(value = "/findOneByUserId", produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskDTO findOneByUserId(@RequestParam(name = "userId") @NotNull String userId,
                                   @RequestParam(name = "id") @NotNull String id);

    @PostMapping(value = "/persist", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO persist(@RequestBody @NotNull TaskDTO entity);

    @PostMapping(value = "/merge", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO merge(@RequestBody @NotNull TaskDTO entity);

    @PostMapping(value = "/removeById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO removeById(@RequestParam(name = "id") @NotNull String id);

    @GetMapping(value = "/findOne", produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskDTO findOne(@RequestParam(name = "id") @NotNull String id);

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDTO> findAll();

}
