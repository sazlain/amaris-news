package com.news.amaris.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.news.amaris.exceptions.AmarisNewsException;
import com.news.amaris.services.impl.SpaceFlightNewsService;

@Configuration
@EnableScheduling
public class ScheduledConfig implements SchedulingConfigurer {
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduledConfig.class);

	@Autowired
	SpaceFlightNewsService spaceFlightNewsService;
	
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
      logger.info("ScheduledConfig success loaded");
    }
    
    @Scheduled(fixedRate = 60000)
    public void updateNews() {
        try {
			spaceFlightNewsService.getNews();
		} catch (AmarisNewsException e) {
			logger.error("Get news from spaceFlightNews failed. Error Message: {}", e.getError().getMessage());
		}
    }
}