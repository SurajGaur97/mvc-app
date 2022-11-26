package com.mvc.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.mvc.demo.model.MyModel;

public interface MyService {
	MyModel setModel(MyModel mymodel);
	
	MyModel getModel(Integer id);
	
	MyModel getAll();
	
	String deleteData(Integer id);
	
	String updateData(Integer id, String name, String city);
	
	String saveFile(String fileName, String contextPath, MultipartFile file);
}
