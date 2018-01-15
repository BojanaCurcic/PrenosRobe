package com.prenosrobe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.prenosrobe.controller.UserController;

@SpringBootApplication
public class App
{
	public static void main(String[] args)
	{
		SpringApplication.run(App.class, args);
		
		
//		UserController controller = new UserController();
//		controller.addUser("", "Mikic", "Mikic", "mika", "ask@stackoverflow.com", "06323223");
	}
}
