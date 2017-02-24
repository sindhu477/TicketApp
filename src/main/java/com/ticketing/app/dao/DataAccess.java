package com.ticketing.app.dao;

import com.ticketing.app.model.User;

public interface DataAccess {

	public Long write(User user);
	public User read(Long id) throws Exception;
	public Long update(User user) throws Exception;
	public void delete(Long id);
	
}
