package com.ast.maven_sample.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ast.maven_sample.Service.CompanyService;
import com.ast.maven_sample.Service.LoginDemoService;

import com.ast.maven_sample.pojo.LoginDemoPojo;

@Controller

public class LoginDemoController {
	@Autowired 
CompanyControl companyControl;
	CompanyService companyService;
	LoginDemoService ser;
	@RequestMapping(value = "/userdemoprocess" ,method= RequestMethod.POST)
	public ModelAndView Userlogin(HttpServletRequest req, HttpServletResponse response,
			@ModelAttribute("Login2")  LoginDemoPojo loginpojo) {
		
		LoginDemoPojo loginpojo2 = ser.Validateuser(loginpojo.getUserNmae(),loginpojo.getPassword());
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("USERNAME", loginpojo.getUserNmae());
		System.out.println("from controller "+loginpojo2);
		ModelAndView mav = new ModelAndView();
		if(loginpojo2 != null) {
			// for delvery page
			
			//mav.setViewName("hai");
			// mav.addObject("message", "welcome!!");
			mav =companyControl.companydil(req, response);
			System.out.println("delevey done");
		}
		else {
			 //mav = new ModelAndView();
			    mav.setViewName("Login2");;
			    mav.addObject("message", "Username or Password is wrong!!");
			
		}
		return mav;
		
	}
	
	@RequestMapping(value = {"/","/loginDemo"}, method =RequestMethod.GET)
	 public ModelAndView  loginpage(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("2 urls enterd");
		ModelAndView mmmm = new ModelAndView();
		  mmmm.addObject("login",new LoginDemoPojo());
		mmmm.setViewName("Login2");
		return mmmm;
	}
	
	
	
	
}
