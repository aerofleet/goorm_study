package com.mission.m260303.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

	@GetMapping("/list")
	public String productList() {
		return "product/list";
	}

	@GetMapping("/add")
	public String productAdd() {
		return "product/add";
	}

	@GetMapping("/userlist")
	public String userList() {
		return "product/userlist";
	}

	@GetMapping("/detail")
	public String productDetail() {
		return "product/detail";
	}
}
