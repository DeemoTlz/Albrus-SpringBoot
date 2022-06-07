package com.deemo.hello.controller;

import com.deemo.hello.bean.Cat;
import com.deemo.hello.bean.Dog;
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

	@GetMapping("/age")
	public String age(@RequestParam Integer age, @RequestParam Long money) {
		System.out.println("age: " + age + ", money: " + money);

		return age + "岁就有￥" + money + "钱了！";
	}

	@GetMapping("/dog")
	public Dog dog(@RequestParam Dog dog) {
		System.out.println("My dog: " + dog);

		return dog;
	}

	/*
	public Cat cat(@RequestParam Cat cat) {
	不能添加 @RequestParam 注解：
	@RequestParam: 使用 RequestParamMethodArgumentResolver 解析参数
	不加注解: 使用 ServletModelAttributeMethodProcessor 解析参数

	@RequestParam 可以搭配自定义 Converter 解析参数
	 */
	@GetMapping("/cat")
	public Cat cat(Cat cat) {
		System.out.println("My cat: " + cat);

		return cat;
	}

}
