/**
 * 
 */
package com.atlassian.tutorial.myweb.jira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * @author Administrator
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class MyWebApplication {

	public static void main(String[] args) throws Exception {
        SpringApplication.run(MyWebApplication.class, args);
    }
	
}
