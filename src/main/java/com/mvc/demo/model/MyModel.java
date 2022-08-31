package com.mvc.demo.model;

import java.util.List;

import com.mvc.demo.entity.Employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyModel {
	
	private Integer id;
	private String name;
	private String city;
	
	private Employee employee;
	private List<Employee> employeeList;
}
