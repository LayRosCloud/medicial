package by.betrayal.visitingservice;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class VisitingServiceApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(VisitingServiceApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

}
