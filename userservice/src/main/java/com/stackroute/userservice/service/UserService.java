package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistException;
import com.stackroute.userservice.exception.UserNotFoundException;

public interface UserService {

	public boolean saveUser(User user)throws UserAlreadyExistException;
	
	public User findByUserIdAndPassword(String userId,String password)throws UserNotFoundException;
}
