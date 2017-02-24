package com.ticketing.app.dao.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.ticketing.app.dao.DataAccess;
import com.ticketing.app.model.User;

public class InMemoryDataAccess implements DataAccess {

	private Map<Long, User> data = new HashMap<Long, User>();

	private Object lock = new Object();

	// public InMemoryDataAccess() {
	// data = new HashMap<Long, User>();
	// }

	// public synchronized Long write(User user) {
	public Long write(User user) {
		// System.out.println(getClass().getName() + "-write");

		Long id = generateId();
		synchronized (lock) {
			data.put(id, user);
		}
		System.out.format("Inserted user: %s", user);

		return id;
	}

	// public synchronized User read(Long id) throws Exception {
	public User read(Long id) throws Exception {
		synchronized (lock) {
			if (data.containsKey(id)) {
				return data.get(id);
			} else {
				// throw new Exception(String.format("Record with id = %f not
				// found", id));
				throw new Exception("Record with id = " + id + " not found");
			}
		}

		// return getClass().getName() + "-read";
	}

	// public synchronized Long update(User user) throws Exception {
	public Long update(User user) throws Exception {
		// System.out.println(getClass().getName() + "-update");

		long id = user.getId();
		synchronized (lock) {
			if (data.containsKey(id)) {
				data.put(id, user);
			} else {
				throw new Exception("Record not found");
			}
		}

		System.out.format("Update user with id: %f and user: %s", id, user);

		return id;
	}

	public void log() {
		// System.out
	}

	// public synchronized void delete(Long id) {
	public void delete(Long id) {
		// System.out.println(getClass().getName() + "-delete");

		synchronized (lock) {
			if (data.containsKey(id)) {
				data.remove(id);
			} else {
				System.out.format("Record with id = %f already deleted", id);
			}
		}
	}

	private Long generateId() {
		Random random = new Random();
		return (long) random.nextInt(100);
	}

}
