package com.mvc.demo.config;

import static com.mvc.demo.constant.PackageNameConstants.PREFIX;
import static com.mvc.demo.constant.PackageNameConstants.SUFFIX;
import static com.mvc.demo.constant.PackageNameConstants.MESSAGE_RESOURCE_URL;
import static com.mvc.demo.constant.PackageNameConstants.DEFAULT_ENCODING;
import static com.mvc.demo.constant.PackageNameConstants.COM_MVC_DEMO;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = COM_MVC_DEMO)
public class ServletConfig {
	
	@Bean
	public ViewResolver getViewResolver(){		//for accessing views i the specified folder.
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(PREFIX);
		resolver.setSuffix(SUFFIX);
		return resolver;
	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {		//for adding css files.
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(MESSAGE_RESOURCE_URL);
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding(DEFAULT_ENCODING);
		messageSource.setCacheSeconds(10);
		return messageSource;
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(100000);
	    return multipartResolver;
	}
}
