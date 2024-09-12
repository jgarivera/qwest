package com.jgarivera.qwest.shared.application;

import com.jgarivera.qwest.QwestApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ConfigurationPropertiesScan(basePackageClasses = QwestApplication.class)
@EnableAsync
class ApplicationConfiguration {
}
