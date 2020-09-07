package com.jiao.order.parse;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(
        basePackages = "com.jiao.order.parse" )
@EntityScan("com.jiao.order.parse.entity")
public class JiaoServiceConfiguration {

}
