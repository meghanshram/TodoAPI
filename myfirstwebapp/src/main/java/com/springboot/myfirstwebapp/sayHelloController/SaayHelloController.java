package com.springboot.myfirstwebapp.sayHelloController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SaayHelloController {
	
	@GetMapping("say-jsp")
	public String sayHelloJsp()
	{
		return "sayHello";
	}
	
}
