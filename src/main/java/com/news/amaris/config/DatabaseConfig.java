package com.news.amaris.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;


@Configuration
public class DatabaseConfig {

	
	@Bean(name = "dataSourceAmarisNews")
	@Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource oneappDatasource() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean(name = "jdbcTemplateAmarisNews")
    @Autowired
    public JdbcTemplate amarisNewsJdbcTemplate(@Qualifier("dataSourceAmarisNews") DataSource dataSourceAmarisNews) {
        return new JdbcTemplate(dataSourceAmarisNews);
    }
}
