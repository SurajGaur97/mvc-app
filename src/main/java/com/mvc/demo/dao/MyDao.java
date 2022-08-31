package com.mvc.demo.dao;

import com.mvc.demo.model.MyModel;

public interface MyDao {
	
	MyModel save(MyModel myModel);
	
	MyModel findById(Integer id);
	
	MyModel findAll();
	
	String deleteById(Integer id);
	
	String updateById(MyModel myModel);
}
