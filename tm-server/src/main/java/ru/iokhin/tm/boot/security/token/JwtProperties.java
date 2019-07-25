package ru.iokhin.tm.boot.security.token;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

    private String secretKey = "$QWEz22484dlasdxoorjtknjdfvncdh^55%asvojansro^ainjscv#";

    //validity in milliseconds
    private long validityInMs = 3600000; // 1h
}