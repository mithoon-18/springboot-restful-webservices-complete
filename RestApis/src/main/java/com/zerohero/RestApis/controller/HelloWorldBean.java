package com.zerohero.RestApis.controller;

public class HelloWorldBean {

	private String hello;

	public HelloWorldBean(String hello) 
	{
		this.hello=hello;
	}

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}
	
	
}
