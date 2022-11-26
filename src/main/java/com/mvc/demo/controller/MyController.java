package com.mvc.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.demo.model.MyModel;
import com.mvc.demo.service.MyService;
import com.mvc.demo.util.ResourceUtil;

@Controller
public class MyController {
	
	@Autowired
	MyService myService;
	
	@Autowired
	ResourceUtil util;
	
	//Link for calling this API: http://localhost:8080/mvc-app/ (or) http://localhost:8080/mvc-app/?id=1,2,3
	@RequestMapping(value = "/")
	public String abc(@RequestParam(required = false) List<String> id, Model model){	//this method is worked as onPageLoad event.
		//Setting for spring messages.
		util.setResource();
		
		model.addAttribute("data", "ids are: " + id);
		model.addAttribute("myModel", new MyModel());	//It is important to set MyModel as attribute in Spring model. And order should be maintained mean it should be at last position.
		return "index";
	}
	
	//Link for calling this API: http://localhost:8080/mvc-app/api/call?id=4,5,6
	@RequestMapping(value = "/api/call")
	@ResponseBody	//@ResponseBody is used for returning a simple object(like string, integer.. etc.) not a view.
	public String apiCall(@RequestParam List<String> id) {
	    return "IDs are " + id;
		//return "index";	//if we do this it will not return the index view but here it returns a string: 'index';
	}
	
	//After clicking submit button on index.jsp page.
	@RequestMapping(value = "setMyModel.dispatch", method = RequestMethod.POST) //Here it is mandaratory to write 'setMyModel.dispatch'. if we write '/setMyModel' it will not able to get method.
	public String setMyModel(@ModelAttribute("myModel")MyModel myModel) {
		myService.setModel(myModel);
		return "index";
	}
	
	@RequestMapping(value = "getMyModel.dispatch", method = RequestMethod.GET)
	public String getMyModel(@ModelAttribute("myModel")MyModel myModel, Model model) {
		model.addAttribute("getModel", myService.getModel(myModel));
		model.addAttribute("myModel", new MyModel());
		return "index";
	}
	
	@RequestMapping(value = "getMyModelList.dispatch", method = RequestMethod.GET)
	public String getMyModelList(Model model) {
		model.addAttribute("getModelList", myService.getAll());
		model.addAttribute("myModel", new MyModel());
		return "index";
	}
	
	@RequestMapping(value = "deleteData.dispatch", method = RequestMethod.POST)
	@ResponseBody
	public String deleteData(@RequestParam Integer id) {
		return myService.deleteData(id);
	}
	
	@RequestMapping(value = "updateData.dispatch", method = RequestMethod.POST)
	@ResponseBody
	public String updateData(@RequestParam Integer id, @RequestParam String name, @RequestParam String city) {
		return myService.updateData(id, name, city);
	}
	
	@RequestMapping(value = "getModelFromFront.dispatch", method = RequestMethod.POST)
	@ResponseBody
	public String getModelFromFront(MyModel myModel) {
		System.out.println("Id = " + myModel.getId());
		System.out.println("Name = " + myModel.getName());
		System.out.println("City = " + myModel.getCity());
		if(myModel.getId() == null) {
			return "Did not get Data!";
		}
		return "Got Data";
	}
}
