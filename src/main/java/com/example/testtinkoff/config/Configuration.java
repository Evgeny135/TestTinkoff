package com.example.testtinkoff.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.concurrent.Executor;

@org.springframework.context.annotation.Configuration
@EnableAsync
public class Configuration {

    @Value("${api}")
    private String API;

    @Value("${folderId}")
    private String folderId;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String dataSourceDriver;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(dataSourceDriver);
        dataSource.setUrl(dataSourceUrl);

        return dataSource;
    }

    @Bean
    public String getAPI() {
        return API;
    }

    @Bean
    public String getFolderId() {
        return folderId;
    }
}
