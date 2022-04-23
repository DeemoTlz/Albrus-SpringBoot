package com.deemo.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password) {
		System.out.println("username: " + username + ", password: " + password);

		return "hello: " + username;
	}

}
