package com.ast.maven_sample.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ast.maven_sample.pojo.LoginDemoPojo;



@Repository
public class LoginDemoDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	public LoginDemoPojo ValidateUser(String userName,String password) {
System.out.println("USSER DAO CALLED"+userName);
String  check="SELECT * FROM USERLOGIN WHERE USERNAME='"+userName+"'and PASSWORD='"+password+"'";
System.out.println("sql reply"+ check);
List<LoginDemoPojo> logina = jdbcTemplate.query(check, new Usermapper());
System.out.println("get data? =>"+logina.size());
return logina.size() >0 ? logina.get(0) : null;

		
	}

}
class Usermapper implements RowMapper<LoginDemoPojo>{

	@Override
	public LoginDemoPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
	String userName= rs.getString("username");
	String  password=rs.getString("Password");
	return new LoginDemoPojo (userName,password);
	}
	
}
