package com.vb.mafia.api;

import com.vb.mafia.core.constant.ConfigConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.util.Arrays;

@EnableAutoConfiguration
@EnableTransactionManagement
@EnableAsync
@ComponentScan({ConfigConstant.REG_BEAN_SCAN})
@SpringBootApplication
public class MafiaApiStartup extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(MafiaApiStartup.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    public static void main(String[] args) {
        log.info("Let's inspect the beans provided by Spring Boot:");
        ApplicationContext ctx = SpringApplication.run(MafiaApiStartup.class, args);
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            log.info("inspect bean {}", beanName);
        }
        log.info("-----------------MAFIA API START UP SUCCESS--------------------");
    }

}
