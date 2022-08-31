package com.mvc.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.demo.dao.MyDao;
import com.mvc.demo.entity.Employee;
import com.mvc.demo.model.MyModel;
import com.mvc.demo.service.MyService;

@Service
public class MyServiceImpl implements MyService {

	@Autowired
	private MyDao myDao;
	
	public MyModel setModel(MyModel myModel) {
		return myDao.save(myModel);
	}

	public MyModel getModel(MyModel myModel) {
		return myDao.findById(myModel.getId());
	}

	public MyModel getAll() {
		return myDao.findAll();
	}

	public String deleteData(Integer id) {
		return myDao.deleteById(id);
	}

	public String updateData(Integer id, String name, String city) {
		MyModel myModel = new MyModel();
		myModel.setId(id);
		myModel.setName(name);
		myModel.setCity(city);
		
		return myDao.updateById(myModel);
	}
}
