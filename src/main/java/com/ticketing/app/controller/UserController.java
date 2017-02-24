package com.ticketing.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ticketing.app.dao.DataAccess;
import com.ticketing.app.dao.impl.InMemoryDataAccess;
import com.ticketing.app.json.UserJson;
import com.ticketing.app.model.User;

@RestController
@RequestMapping("/api/1.0/user")
public class UserController {
	
	@Autowired
	private DataAccess dataAccess;
	
//	@Value("${datastore.type}")
//	private String datastoreType;
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Long create(@RequestBody UserJson userJson) {
		
		User modelUser = new User(userJson.getUsername(), userJson.getPassword(),
				userJson.getFirstName(), userJson.getLastName());
		long id = dataAccess.write(modelUser);
	
		return id;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public User read(@PathVariable("id") Long id) throws Exception {		
		return dataAccess.read(id);
		
//		return "{ \"result\": \"" + dataAccess.read() + "\"}";
//		return "{ \"result\": \"" + datastoreType + "\"}";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public Long update(@RequestBody UserJson userJson) {
//	public Long update(@RequestBody List<UserJson> userJson) {
//		for (UserJson h : userJson) {
//			
//		}
		
		User modelUser = new User(userJson.getUsername(), userJson.getPassword(),
				userJson.getFirstName(), userJson.getLastName());
		long id = dataAccess.write(modelUser);
	
		return id;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		dataAccess.delete(id);		
	}
}
