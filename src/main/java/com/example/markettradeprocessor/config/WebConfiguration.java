package com.example.markettradeprocessor.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        Refill refill = Refill.greedy(10, Duration.ofMinutes(1)); // after 6s add 1 token
        Bandwidth limit = Bandwidth.classic(10, refill); // maximum capacity is 10, first initial capacity is 10
        Bucket bucket = Bucket.builder().addLimit(limit).build();
        // each time request to this path will consume 1 token, the number of consume token depend on you
        registry.addInterceptor(new RateLimitInterceptor(bucket, 1)).addPathPatterns("/message");
    }
}
