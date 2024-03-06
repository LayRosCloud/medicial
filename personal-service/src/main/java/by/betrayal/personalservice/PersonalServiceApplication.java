package by.betrayal.personalservice;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PersonalServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(PersonalServiceApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.run(args);
	}

}
