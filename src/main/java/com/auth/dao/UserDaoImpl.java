package com.auth.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.auth.encrypt.Encryptor;
import com.auth.model.Login;
import com.auth.model.User;

public class UserDaoImpl implements UserDao
{
	@Autowired
	DataSource datasource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	Encryptor encryptor;
	
	
	public void register(User user) 
	{
		Encryptor pwdEncryptor = new Encryptor();
		String encryptedPassword = pwdEncryptor.encryptPassword(user.getPassword());
		
		String sql = "insert into user values(?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, 
				new Object[] {user.getUsername(),
						encryptedPassword,
						user.getFirstname(),
						user.getLastname(),
						user.getEmail(),
						user.getAddress(),
						user.getPhone(),user.getType()});
		System.out.println("USER REGISTERED - "+user.getUsername());
	}

	public User validateUser(Login login) 
	{
		System.out.println("asdasdsdas");
		boolean authorizedUser = false;
		String sql = "select * from user where username='" + login.getUsername() + "'";
		List<User> users = jdbcTemplate.query(sql, new UserMapper());
		 
		if(users.size()>0) 
		{
			String encryptedPassword = users.get(0).getPassword();
			System.out.println("encryptedPassword-"+encryptedPassword);
			System.out.println("login.getPassword()-"+login.getPassword());
			authorizedUser = encryptor.matchPassword(login.getPassword(),encryptedPassword);
			System.out.println("authorizedUser-"+authorizedUser);
		}
		else 
		{
			authorizedUser = false;
		}
		
		if(authorizedUser) 
		{
			return users.get(0);
		}
		else 
		{
			return null;
		}
		
	}

}	

class UserMapper implements RowMapper<User>
{

	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
	    user.setUsername(rs.getString("username"));
	    user.setPassword(rs.getString("password"));
	    user.setFirstname(rs.getString("firstname"));
	    user.setLastname(rs.getString("lastname"));
	    user.setEmail(rs.getString("email"));
	    user.setAddress(rs.getString("address"));
	    user.setPhone(rs.getString("phone"));
	    return user;
	}
}

