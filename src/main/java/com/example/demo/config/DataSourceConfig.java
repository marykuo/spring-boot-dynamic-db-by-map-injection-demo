package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Bean name must be EnvironmentEnum.name()
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "TENANT1")
    @ConfigurationProperties(prefix = "datasource.tenant1")
    public DataSource dataSourceTenant1() {
        return DataSourceBuilder.create().build();
    }

    /**
     * must have one primary datasource
     */
    @Primary
    @Bean(name = "TENANT2")
    @ConfigurationProperties(prefix = "datasource.tenant2")
    public DataSource dataSourceTenant2() {
        return DataSourceBuilder.create().build();
    }

}
