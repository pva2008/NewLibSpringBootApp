package com.vpdev.spring.newlibspringbootapp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
//сканирует пакеты приложений, чтобы найти все Spring Beans, декорированные методами @Scheduled, и устанавливает график их выполнения.
@EnableScheduling
//отключить планирование во время выполнения тестов.
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
@PropertySource("classpath:scheduler.properties")
public class SchedulerConfig {
}
