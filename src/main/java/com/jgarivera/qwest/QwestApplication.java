package com.jgarivera.qwest;

import com.jgarivera.qwest.auth.infrastructure.AvatarSettings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AvatarSettings.class)
public class QwestApplication {

	public static void main(String[] args) {
		SpringApplication.run(QwestApplication.class, args);
	}

}
