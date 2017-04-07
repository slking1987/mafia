package com.mafia.core.test;

import com.mafia.core.constant.ConfigConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@EnableAutoConfiguration
@EnableTransactionManagement
@SpringBootApplication
@ComponentScan({ConfigConstant.REG_BEAN_SCAN})
public class MafiaStartupForTest {
    private static final Logger log = LoggerFactory.getLogger(MafiaStartupForTest.class);

    public static void main(String[] args) {
        log.info("Let's inspect the beans provided by Spring Boot:");
        ApplicationContext ctx = SpringApplication.run(MafiaStartupForTest.class, args);
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            log.info("inspect bean {}", beanName);
        }
        log.info("-----------------MAFIA API START UP SUCCESS[TEST]--------------------");
    }
}
