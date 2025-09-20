package com.zerohero.RestApis.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Simple Api

@RestController
@RequestMapping("/")
public class HelloController {

	private MessageSource messageSource;
	
	public HelloController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@GetMapping("hello")
	public String greet()
	{
		return "Hello world";
	}
	
	@GetMapping("helloBean")
	public HelloWorldBean hello()
	{
		return new HelloWorldBean("Hello world");
	}
	

	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale );
	}
}
