package com.auth.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.auth.model.User;
import com.auth.service.UserService;

@Controller
public class UserRegisterController 
{
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("register");
		model.addObject("user", new User());
		return model;
	}
	
	@RequestMapping(value="/registerProcess", method=RequestMethod.POST)
	public ModelAndView adduser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user) 
	{
		userService.register(user);
		return new ModelAndView("welcome", "firstname", user.getFirstname());
	}
	
	
	@ModelAttribute("type")
	public ArrayList<String> userTypeList()
	{
		ArrayList<String> userTypeList = new ArrayList<String>();
		userTypeList.add("Admin");
		userTypeList.add("cashier");
		userTypeList.add("delivery");
		return userTypeList;
	}
}
