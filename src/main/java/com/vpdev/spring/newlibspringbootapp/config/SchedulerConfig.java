package com.vpdev.spring.newlibspringbootapp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

//https://habr.com/ru/articles/580062/
@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
@PropertySource("classpath:scheduler.properties")
public class SchedulerConfig {
}
