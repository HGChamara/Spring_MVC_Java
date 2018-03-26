package com.auth.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.auth.dao.UserDao;
import com.auth.model.Login;
import com.auth.model.User;

public class UserServiceImpl  implements UserService
{

	@Autowired
	  public UserDao userDao;

	  public void register(User user) {
	    userDao.register(user);
	  }

	  public User validateUser(Login login) {
	    return userDao.validateUser(login);
	  }

}
