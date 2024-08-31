package com.akit.services;

import java.util.List;

import com.akit.entities.User;

public interface UserService {

	public boolean saveUser(User user);
	
	public List<User> getUserList();
	
	public boolean checkCrd(String email, String pwd);
}
