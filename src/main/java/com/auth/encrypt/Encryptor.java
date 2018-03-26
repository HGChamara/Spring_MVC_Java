package com.auth.encrypt;

import org.mindrot.jbcrypt.BCrypt;

public class Encryptor 
{
	public static void main(String[] args) 
	{
		String originalPassword = "test1";
	}
	
	public String encryptPassword(String originalPassword) 
	{
		String encryptedPassword = "";
		encryptedPassword = BCrypt.hashpw(originalPassword, BCrypt.gensalt(10));
		return encryptedPassword;
	}
	
	public boolean matchPassword(String originalPassword, String encryptedPassword)  
	{
		boolean matchedStatus = false;
		matchedStatus = BCrypt.checkpw(originalPassword, encryptedPassword);
		return matchedStatus;
	}
}
