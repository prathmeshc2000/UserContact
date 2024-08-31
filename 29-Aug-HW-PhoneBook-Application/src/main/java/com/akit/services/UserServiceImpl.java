package com.akit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.akit.Repos.UserRepo;
import com.akit.entities.User;

@Service
public class UserServiceImpl implements UserService {

	private UserRepo userRepo;
	
	public UserServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public boolean saveUser(User user) {
		User u = userRepo.save(user);
		if (u.getId() != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<User> getUserList() {
		List<User> userList = userRepo.findAll();
		return userList;
	}

	@Override
	public boolean checkCrd(String email, String pwd) {
		User user = userRepo.findByEmailAndPwd(email, pwd);
		if (user != null && user.getId() != null) {
			return true;
		}
		
		return false;
	}

}
