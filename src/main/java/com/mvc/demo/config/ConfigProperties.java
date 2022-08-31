package com.mvc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class ConfigProperties {
	private static final String DATABASE_RESOUCE_URI = "database.properties";	//first properties file name.
//	private static final String MAIL_RESOUCE_URI = "mail.properties";	//second properties file name.
	
	@Bean
	public PropertySourcesPlaceholderConfigurer getPropertyPlaceholderConfigurer() {
		final PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		ClassPathResource[] resources = new ClassPathResource[] { new ClassPathResource(DATABASE_RESOUCE_URI) };
//		ClassPathResource[] resources = new ClassPathResource[] { new ClassPathResource(DATABASE_RESOUCE_URI), new ClassPathResource(MAIL_RESOUCE_URI) };	//for multiple properties file.
		propertyPlaceholderConfigurer.setLocations(resources);
		propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
		return propertyPlaceholderConfigurer;
	}
}
