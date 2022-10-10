package com.cloud.parking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrimeiroControlle 
{

	@GetMapping("/")
	public String helloWorld()
	{
		return "Hello, World!";
	}
}
