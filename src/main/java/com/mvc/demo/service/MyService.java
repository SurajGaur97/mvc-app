package com.mvc.demo.service;

import com.mvc.demo.model.MyModel;

public interface MyService {
	MyModel setModel(MyModel mymodel);
	
	MyModel getModel(MyModel myModel);
	
	MyModel getAll();
	
	String deleteData(Integer id);
	
	String updateData(Integer id, String name, String city);
}
