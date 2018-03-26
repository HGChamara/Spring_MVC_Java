package com.auth.dao;

import com.auth.model.Login;
import com.auth.model.User;

public interface UserDao 
{
	void register(User user);
	User validateUser(Login login);
}
