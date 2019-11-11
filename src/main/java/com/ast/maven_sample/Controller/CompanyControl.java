package com.ast.maven_sample.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ast.maven_sample.Service.CompanyService;
import com.ast.maven_sample.pojo.CompanyPojo;

@Controller

public class CompanyControl {
@Autowired
CompanyService companyService;
 @RequestMapping(value = "/companydil", method= RequestMethod.POST)
 
 public  ModelAndView companydil(HttpServletRequest req, 
		 HttpServletResponse response	 ) {
	HttpSession session= req.getSession(false);
	ModelAndView mav = new ModelAndView();
	List<CompanyPojo> com = companyService.getAllCompany();
	
	System.out.println("this dada from databace"+ com);
	
	 int id;String comapny,location,note,date;
	System.out.println("comapny data"+ com);
	for (CompanyPojo hi : com) {
		//System.out.println("thid id"+hi.getId());
//		mav.addObject("Aid",hi.getId());
//		mav.addObject("Acomapny",hi.getCompany());
//		mav.addObject("Alocation",hi.getLocation());
//		mav.addObject("Anote",hi.getNote());
//		mav.addObject("Adate",hi.getDelivaryDate());
		mav.addObject("alldil",com);
		
		mav.setViewName("Comlist");
	}
	return mav;
 }
 
 
 @RequestMapping(value = "/comapnyRegi1", method = RequestMethod.POST)
 public ModelAndView comapnyRegi(HttpServletRequest req, HttpServletResponse res,
		  
		 @ModelAttribute("companyRegi") CompanyPojo companyPojo  ) {	
	 System.out.println("ender company controler (regi)");
	 HttpSession httpSession = req.getSession();  
	 ModelAndView mav = new ModelAndView();
	 CompanyPojo com = companyService.companyRegi(companyPojo);
	 System.out.println(" control regi"+com);
	 
	 
		 mav.addObject("done"," successfully stored company query");
	 mav.setViewName("Comlist");
	return mav;
 }

	@RequestMapping(value = "/comapnyRegi", method = RequestMethod.GET)
	  public ModelAndView showTest(HttpServletRequest request, HttpServletResponse response) {
	
		ModelAndView mav = new ModelAndView();
	   mav.setViewName("comapnyRegi22");
	  
	    return mav;
	  }
 
 
 
 
 
 
}
