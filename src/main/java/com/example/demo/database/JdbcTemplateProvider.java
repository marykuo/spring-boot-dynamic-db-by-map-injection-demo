package com.example.demo.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

@Component
public class JdbcTemplateProvider {
    private final Map<EnvironmentEnum, DataSource> dataSourceMap;

    /**
     * Get all datasource beans by map injection
     */
    public JdbcTemplateProvider(Map<String, DataSource> dataSourceMap) {
        this.dataSourceMap = dataSourceMap.entrySet().stream()
                .collect(java.util.stream.Collectors.toMap(
                        e -> EnvironmentEnum.valueOf(e.getKey()),
                        Map.Entry::getValue
                ));
    }

    /**
     * 1. get data source connect
     * 2. initialize JdbcTemplate object
     */
    public JdbcTemplate getJdbcTemplate(EnvironmentEnum beanName) {
        try {
            DataSource dataSource = dataSourceMap.get(beanName);
            return new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid data source: " + beanName);
        }
    }

    /**
     * 1. get data source connect
     * 2. initialize NamedParameterJdbcTemplate object
     */
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(EnvironmentEnum beanName) {
        try {
            DataSource dataSource = dataSourceMap.get(beanName);
            return new NamedParameterJdbcTemplate(dataSource);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid datasource: " + beanName);
        }
    }

}
