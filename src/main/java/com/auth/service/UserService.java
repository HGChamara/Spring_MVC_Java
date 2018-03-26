package com.auth.service;

import com.auth.model.Login;
import com.auth.model.User;

public interface UserService 
{
	void register(User user);
	User validateUser(Login login);
}
