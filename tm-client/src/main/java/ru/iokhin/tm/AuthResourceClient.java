package ru.iokhin.tm;

import feign.Feign;
import feign.okhttp.OkHttpClient;
import okhttp3.JavaNetCookieJar;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.support.SpringDecoder;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ru.iokhin.tm.api.AuthResource;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class AuthResourceClient {
    public static AuthResource getInstance(final String baseUrl) {
        MappingJackson2HttpMessageConverter converterJackson = new MappingJackson2HttpMessageConverter();
        converterJackson.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        final HttpMessageConverters converters = new HttpMessageConverters(converterJackson);
        final ObjectFactory<HttpMessageConverters> objectFactory = () -> converters;
        final CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        final okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient().newBuilder();
        builder.cookieJar(new JavaNetCookieJar(cookieManager));
        return Feign.builder().client(new OkHttpClient(builder.build())).contract(new SpringMvcContract()).encoder(new SpringEncoder(objectFactory)).decoder(new SpringDecoder(objectFactory)).target(AuthResource.class, baseUrl);
    }

    public static void main(String[] args) {
        final AuthResource authResource = getInstance("http://localhost:8080/");
        System.out.println(authResource.login("user", "user").isSuccess());
        System.out.println(authResource.profile());
        System.out.println(authResource.logout());
    }
}

