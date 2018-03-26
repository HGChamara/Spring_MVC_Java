package com.auth.encrypt;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EncryptionTest 
{
	String clearText = "test1";
	String orgEncryptedPassword = "$2a$10$fv5IYIzDyJzgKQEI0KYw0ui8Czi6O1XXBiWmlHh2zaTAdOvIs0AQi";
	Encryptor pwdEncryptor = null;
	
	@Before
	public void setup() 
	{
		pwdEncryptor = new Encryptor();
	}
	
	@Test
	public void testEncryptedPassword() 
	{
		String encryptedPassword = pwdEncryptor.encryptPassword(clearText);
		boolean status = pwdEncryptor.matchPassword(clearText, encryptedPassword);
		System.out.println(status);
		assertEquals(true, status);
	}

}
