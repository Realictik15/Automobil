package com.automobil.backend.configs;


import com.automobil.backend.api.CacheSupport;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheCongig {
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager() {
            @Override
            protected Cache createConcurrentMapCache(String name) {
                return new ConcurrentMapCache(
                    name,
                    CacheBuilder.newBuilder()
                        .maximumSize(10)
                        .expireAfterWrite(2, TimeUnit.DAYS)
                        .build().asMap(),
                    false);
            }
        };
    }

}
