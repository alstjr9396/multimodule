package me.minseok.multimodule;

import me.minseok.multimodule.api.config.security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AppProperties.class)
@SpringBootApplication
public class MultimoduleApplication {

  public static void main(String[] args) {
    SpringApplication.run(MultimoduleApplication.class, args);
  }

}
