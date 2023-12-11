package com.jtspringproject.JtSpringProject.services;

import com.jtspringproject.JtSpringProject.models.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtspringproject.JtSpringProject.dao.userDao;
import com.jtspringproject.JtSpringProject.models.User;

@Service
public class userService {
	@Autowired
	private userDao userDao;
	
	public List<User> getUsers(){
		return this.userDao.getAllUser();
	}
	
	public User addUser(User user) {
		return this.userDao.saveUser(user);
	}
	
	public User checkLogin(String username,String password) {
		return this.userDao.getUser(username, password);
	}

	public User getUserByUsername(String username)
	{
		return this.userDao.getUserByUsername(username);
	}

	public boolean isUserExists(String username) 
	{
		return this.userDao.isUserExists(username);
        }

	public boolean isEmailExists(String email)
	{
		return this.userDao.isEmailExists(email);
	}
        
	@Transactional
	public User addUser(User user) {
		return this.userDao.saveUser(user);
	}
	
}
