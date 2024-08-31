package com.akit.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.akit.entities.Contact;
import com.akit.entities.User;
import com.akit.services.ContactService;
import com.akit.services.EmailService;
import com.akit.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	private UserService userService;
	private ContactService contService;
	private EmailService emailService;
	
	public UserController(UserService userService,EmailService emailService,ContactService contService) {
		this.userService = userService;
		this.emailService = emailService;
		this.contService = contService;
	}
	
	@GetMapping("/wlcm")
	public String welcome() {
		
		return "welcome";
	}
	
	@GetMapping("/")
	public ModelAndView userForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "USER RAGISTRATION FORM");
		
		mav.setViewName("index");
		return mav;
	}
	
	@PostMapping("/addUser")
	public ModelAndView addUser(User user) {
		ModelAndView mav = new ModelAndView();
		
		boolean isSave = userService.saveUser(user);
		if (isSave) {
			mav.addObject("smsg","User Added");
			// mail sender
			String body = "Hello "+user.getName()+", \n"+"Registration Successful";
			emailService.sendMail(user.getEmail(), "No-reply-New Registration", body);
			mav.setViewName("login");
		}
		else{
			mav.addObject("smsg","Failed To Add User");
			mav.setViewName("index");
		}
		return mav;
	}
	
	@GetMapping("/login")
	public ModelAndView loginForm() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("login");
		return mav;
	}
	
	
	@PostMapping("/checkcrd")
	public ModelAndView userLogin(HttpServletRequest req, User user) {
		ModelAndView mav = new ModelAndView();
		boolean checkCrd = userService.checkCrd(user.getEmail(), user.getPwd());
		if (checkCrd) {
			mav.addObject("smsg","Logged In successful");
			
			//if login success then save the userId in user_id
			HttpSession session = req.getSession(true);
			session.setAttribute("user_id", user.getId());
			
			mav.setViewName("dt");
		}
		else {
			mav.addObject("smsg","Email or Password is incorrect");
			mav.setViewName("login");
		}
		return mav;
	}
	
	@GetMapping("/dt")
	public ModelAndView getContact(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		
		// to fetch the user_id from session
		Integer userID= (Integer)req.getAttribute("user_id");
		System.out.println(userID);
		List<Contact> contactList = contService.getContactList();
		mav.addObject("cont", contactList);
		mav.setViewName("data");
		return mav;
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		
		//false=to user existing session(no need to create new session)
		HttpSession session = req.getSession(false);
		// remove the user_id from session and invalidate the session
		session.invalidate();
		
		mav.setViewName("login");
		return mav;
	}
	
	
}
