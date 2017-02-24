package com.ticketing.app;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import com.ticketing.app.dao.DataAccess;
import com.ticketing.app.dao.impl.InMemoryDataAccess;
import com.ticketing.app.dao.impl.MySqlDataAccess;

@SpringBootApplication
@PropertySource("classpath:ticket.app.properties")
public class App {
	
	@Value("${datastore.type}")
	private String datastoreType;
	
	@Bean	
	public DataAccess getDataAccess() {
		if ("inmemory".equalsIgnoreCase(datastoreType)) {
			return new InMemoryDataAccess();
		} else {
			return new MySqlDataAccess();
		}
	}
	
	@Bean
	public SessionFactory buildSessionFactory() {
		try {	     
	    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();	    	
	        return sessionFactory;
	    }
	    catch (Throwable ex) {
	        // Make sure you log the exception, as it might be swallowed
	        System.err.println("Initial SessionFactory creation failed." + ex);
	        throw new ExceptionInInitializerError(ex);
	    }		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
