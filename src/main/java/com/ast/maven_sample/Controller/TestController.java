package com.ast.maven_sample.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView sayHello () {
		System.out.println("hii");
		ModelAndView model = new ModelAndView();
		model.addObject("name", "");
        model.setViewName("index");
        return model; 
    }

}
