package com.ticketing.app.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ticketing.app.dao.DataAccess;
import com.ticketing.app.model.User;

public class MySqlDataAccess implements DataAccess {
	
	@Autowired
	private SessionFactory sessionFactory;
	
//	public MySqlDataAccess() {
		// create session factory instance
//	}
	
//	public MySqlDataAccess(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}

	public Long write(User user) {
		// TODO Auto-generated method stub
		
		Session session = null;
		
		try {
			session = sessionFactory.openSession();				
			Long result = (Long) session.save(user);
			return result;	
		} finally {
			session.close();
		}	
	}

	public User read(Long id) throws Exception {
		Session session = null;
		
		try {
			session = sessionFactory.openSession();				
			User result = (User) session.get(User.class, id);
			return result;	
		} finally {
			session.close();
		}	
	}

	public Long update(User user) throws Exception {
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			User record = (User) session.get(User.class, user.getId());		
			session.update(record);				
			return user.getId();
		} finally {
			session.close();
		}	
	}

	public void delete(Long id) {
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			User u1 = (User) session.get(User.class, id);
			session.delete(u1);			
		} finally {
			session.close();
		}	
	}

//	public void write() {
//		System.out.println(getClass().getName() + "-write");
//		
//	}
//
//	public String read() {
//		return getClass().getName() + "-read";
//		
//	}
//
//	public void update() {
//		System.out.println(getClass().getName() + "-update");
//		
//	}
//
//	public void delete() {
//		System.out.println(getClass().getName() + "-delete");
//		
//	}

}
