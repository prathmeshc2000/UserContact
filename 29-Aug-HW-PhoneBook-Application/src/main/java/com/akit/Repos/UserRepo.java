package com.akit.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akit.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	public User findByEmailAndPwd(String email,String pwd);


}
