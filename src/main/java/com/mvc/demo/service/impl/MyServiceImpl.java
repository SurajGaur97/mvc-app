package com.mvc.demo.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mvc.demo.dao.MyDao;
import com.mvc.demo.model.MyModel;
import com.mvc.demo.service.MyService;

@Service
public class MyServiceImpl implements MyService {

	@Autowired
	private MyDao myDao;
	
	/**
	 * @author suraj.gaur
	 * @return MyModel
	 * @apiNote This will save the model in database and return the same data whatever it save in database.
	 * @param MyModel
	 */
	public MyModel setModel(MyModel myModel) {
		return myDao.save(myModel);
	}

	/**
	 * @author suraj.gaur
	 * @return MyModel
	 * @apiNote For getting a single model by providing id.
	 * @param id
	 */
	public MyModel getModel(Integer id) {
		return myDao.findById(id);
	}

	/**
	 * @author suraj.gaur
	 * @return MyModel
	 * @apiNote This will return a MyMpdel object.
	 */
	public MyModel getAll() {
		return myDao.findAll();
	}

	/**
	 * @author suraj.gaur
	 * @return String
	 * @apiNote This will delete the data by getting Id.
	 * @param id
	 */
	public String deleteData(Integer id) {
		return myDao.deleteById(id);
	}

	/**
	 * @author suraj.gaur
	 * @return String
	 * @apiNote This Service will update the parameter by Id.
	 * @param id
	 * @param name
	 * @param city name
	 */
	public String updateData(Integer id, String name, String city) {
		MyModel myModel = new MyModel();
		myModel.setId(id);
		myModel.setName(name);
		myModel.setCity(city);
		
		return myDao.updateById(myModel);
	}

	/**
	 * @author suraj.gaur
	 * @return string
	 * @apiNote This service will save the a file to the location where you give the address.
	 * @param fileName
	 * @param contextPath
	 * @param file
	 */
	public String saveFile(String fileName, String contextPath, MultipartFile file) {
		
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				
				File dir = new File(contextPath + File.separator + "ImageFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName + ".jpg");
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				//logger.info("Server File Location=" + serverFile.getAbsolutePath());
				return "You successfully uploaded file = " + fileName;
				
			} catch (Exception e) {
				return "You failed to upload " + fileName + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + fileName + " because the file was empty.";
		}
	}
}
