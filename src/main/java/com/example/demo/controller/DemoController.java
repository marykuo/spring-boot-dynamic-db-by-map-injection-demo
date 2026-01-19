package com.example.demo.controller;

import com.example.demo.database.EnvironmentEnum;
import com.example.demo.database.JdbcTemplateProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Change different database connection by environment parameter
 */
@RequiredArgsConstructor
@RestController
@Slf4j
public class DemoController {
    private final JdbcTemplateProvider jdbcTemplateProvider;

    @GetMapping("/database")
    public String database(
            @RequestParam(name = "environment") EnvironmentEnum environment
    ) {
        log.debug("Get current database for environment: {}", environment);

        NamedParameterJdbcTemplate jdbcTemplate = jdbcTemplateProvider.getNamedParameterJdbcTemplate(environment);

        return jdbcTemplate.queryForObject(
                "SELECT current_database()",
                new MapSqlParameterSource(),
                String.class
        );
    }

}
