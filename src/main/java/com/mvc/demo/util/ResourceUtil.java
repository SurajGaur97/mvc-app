package com.mvc.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ResourceUtil {
	
	public void setResource() {
		Resource resource = new ClassPathResource("ApplicationLabel.properties");
		InputStream resourceInputStream = null;
		Properties prop = new Properties();
		
		try {
			resourceInputStream = resource.getInputStream();
			prop.load(resourceInputStream);
		} catch (IOException e) {
			System.out.println("Problem in getting input stream");
			e.printStackTrace();
		} catch (Exception e) {
            System.out.println("Resource loader cannot be loaded");
            e.printStackTrace();
        }
	}

}
