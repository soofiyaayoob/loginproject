package com.example.demo.condroller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.UserMakbig;

import com.example.demo.service.makbigUserService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class homeCondroller {
	@Autowired
	makbigUserService makbigUserservice;
	


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserMakbig());
        return "register"; 
    }

  
    @PostMapping("/register")
    public String addUser(@ModelAttribute UserMakbig user) {
    	boolean isRegistered= makbigUserservice.addUser(user);
    	if (isRegistered) {
            return "redirect:/home"; 
        } else {
           
            return "register"; 
        }
    }

   
    @GetMapping("/login")
    public String showLoginForm() {
        
        return "login"; 
    }

    
    @PostMapping("/perform_login")
    public String login(@ModelAttribute UserMakbig user) {
        boolean isValidUser = makbigUserservice.verify(user);
        if (isValidUser) {
          return "redirect:/home";
        } else {
           return "login";
        }
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName(); 

            model.addAttribute("username", username); 
        }
        return "home"; 
    }
  
    @GetMapping("/alluser")
    @ResponseBody
    public  List<UserMakbig> allusers() {
        return makbigUserservice.users();
    }
//    @PutMapping("/alluser")
//	@ResponseBody
//	public void updateProduct(@RequestBody UserMakbig user) {
//		System.out.println("iam here");
//		makbigUserService.update(user);
//	}
    
  }

