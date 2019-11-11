package com.ast.maven_sample.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.maven_sample.dao.LoginDemoDao;
import com.ast.maven_sample.pojo.LoginDemoPojo;



@Service
public class LoginDemoService {
	@Autowired
	LoginDemoDao dao;
public LoginDemoPojo Validateuser(String userNmae , String password) {
	System.out.println("in service"+userNmae+""+password);
	return dao.ValidateUser(userNmae, password);
}



}
