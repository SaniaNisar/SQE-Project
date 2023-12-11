package com.jtspringproject.JtSpringProject.controller;

import com.jtspringproject.JtSpringProject.models.Cart;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.models.User;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;


import com.jtspringproject.JtSpringProject.services.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.jtspringproject.JtSpringProject.services.userService;
import com.jtspringproject.JtSpringProject.services.productService;
import com.jtspringproject.JtSpringProject.services.cartService;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class UserController{
	
	@Autowired
	private userService userService;

	@Autowired
	private productService productService;

	public UserController(userService userService) {
		this.userService = userService;
	}

	@GetMapping("/register")
	public String registerUser()
	{
		return "register";
	}

	@GetMapping("/buy")
	public String buy()
	{
		return "buy";
	}

	@GetMapping("/PasswordError")
	public String passwordError() {
		return "PasswordError";
	}

	@GetMapping("/ExistsUsernameError")
	public String ExistsUsernameError() {
		return "ExistsUsernameError";
	}

	@GetMapping("/BlankUsername")
	public String BlankUsername() {
		return "BlankUsername";
	}

	@GetMapping("/ExistsEmailError")
	public String ExistsEmailError(Model model) {

		return "ExistsEmailError";
	}

	/*@GetMapping("/user/profile")
	public ModelAndView getUserDetails(HttpServletRequest req) {
		// Assuming you have a way to get the current username from the session or authentication context
		String currentUsername = //;

				// Use the userService to get the user details by username
		User currentUser = userService.checkLogin(currentUsername, null);

		ModelAndView modelAndView = new ModelAndView("Profile");
		modelAndView.addObject("username", currentUser.getUsername());
		modelAndView.addObject("email", currentUser.getEmail());
		modelAndView.addObject("address", currentUser.getAddress());

		return modelAndView;
	}
	 */

	@GetMapping("/user/profile")
	public ModelAndView getUserDetails(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String currentUsername = (String) session.getAttribute("currentUsername");
		String currentpass = (String) session.getAttribute("currentpass");

		if (currentUsername == null || currentpass == null) {
			return new ModelAndView("redirect:/login"); // Redirect to login if session attributes are missing
		}

		User currentUser = userService.checkLogin(currentUsername, currentpass);
		if (currentUser == null) {
			return new ModelAndView("redirect:/login"); // Redirect to login if user is not found
		}

		ModelAndView modelAndView = new ModelAndView("profile");
		modelAndView.addObject("user", currentUser); // Adding the entire user object to the model
		return modelAndView;
	}


	@GetMapping("/")
	public String userlogin(Model model) {
		
		return "userLogin";
	}

	/*@RequestMapping(value = "userloginvalidate", method = RequestMethod.POST)
	public ModelAndView userlogin( @RequestParam("username") String username, @RequestParam("password") String pass,Model model,HttpServletResponse res) {
		
		System.out.println(pass);
		User u = this.userService.checkLogin(username, pass);
		System.out.println(u.getUsername());
		if(u.getUsername().equals(username)) {	
			
			res.addCookie(new Cookie("username", u.getUsername()));
			ModelAndView mView  = new ModelAndView("index");	
			mView.addObject("user", u);
			List<Product> products = this.productService.getProducts();

			if (products.isEmpty()) {
				mView.addObject("msg", "No products are available");
			} else {
				mView.addObject("products", products);
			}
			return mView;

		}else {
			ModelAndView mView = new ModelAndView("userLogin");
			mView.addObject("msg", "Please enter correct email and password");
			return mView;
		}
		
	}
	 */
	
	@RequestMapping(value = "userloginvalidate", method = RequestMethod.POST)
	public ModelAndView userlogin(@RequestParam("username") String username, @RequestParam("password") String pass, HttpServletResponse res, HttpServletRequest req) {

		User u = this.userService.checkLogin(username, pass);

		if (u != null && u.getUsername().equals(username)) {
			HttpSession session = req.getSession();
			session.setAttribute("currentUsername", username);
			session.setAttribute("currentpass", pass);

			res.addCookie(new Cookie("username", u.getUsername()));
			ModelAndView mView = new ModelAndView("index");
			mView.addObject("user", u);
			List<Product> products = this.productService.getProducts();

			if (products.isEmpty()) {
				mView.addObject("msg", "No products are available");
			} else {
				mView.addObject("products", products);
			}
			return mView;

		} else {
			ModelAndView mView = new ModelAndView("userLogin");
			mView.addObject("msg", "Please enter correct email and password");
			return mView;
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		// Clear the cookies if any
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}

		return "redirect:/"; // Redirect to login page or home page as per your requirement
	}
	
	
	@GetMapping("/user/products")
	public ModelAndView getproduct() {

		ModelAndView mView = new ModelAndView("uproduct");

		List<Product> products = this.productService.getProducts();

		if(products.isEmpty()) {
			mView.addObject("msg","No products are available");
		}else {
			mView.addObject("products",products);
		}

		return mView;
	}

	@RequestMapping(value = "newuserregister", method = RequestMethod.POST)
	public ModelAndView newUseRegister(@ModelAttribute User user, RedirectAttributes redirectAttributes) {

		if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
			redirectAttributes.addFlashAttribute("error", "Username cannot be empty or contain only white spaces.");
			return new ModelAndView("redirect:/BlankUsername");
		}

		if (userService.isUserExists(user.getUsername())) {
			redirectAttributes.addFlashAttribute("error", "Username already exists. Please choose a different username.");
			return new ModelAndView("redirect:/ExistsUsernameError");
		}
		String passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$";

		if (!user.getPassword().matches(passwordPattern)) {
			redirectAttributes.addFlashAttribute("error", "Password must be at least 8 characters long and include both alphabets and numbers.");
			return new ModelAndView("redirect:/PasswordError");
		}

		if(userService.isEmailExists(user.getEmail()))
		{
			redirectAttributes.addFlashAttribute("error", "Username already exists. Please choose a different username.");
			return new ModelAndView("redirect:/ExistsEmailError");
		}

		System.out.println(user.getEmail());
		user.setRole("ROLE_NORMAL");
		this.userService.addUser(user);

		// Redirect to userLogin on successful registration
		return new ModelAndView("redirect:/userLogin1");
	}


	//for Learning purpose of model
		@GetMapping("/test")
		public String Test(Model model)
		{
			System.out.println("test page");
			model.addAttribute("author","jay gajera");
			model.addAttribute("id",40);
			
			List<String> friends = new ArrayList<String>();
			model.addAttribute("f",friends);
			friends.add("xyz");
			friends.add("abc");
			
			return "test";
		}
		
		// for learning purpose of model and view ( how data is pass to view)
		
		@GetMapping("/test2")
		public ModelAndView Test2()
		{
			System.out.println("test page");
			//create modelandview object
			ModelAndView mv=new ModelAndView();
			mv.addObject("name","jay gajera 17");
			mv.addObject("id",40);
			mv.setViewName("test2");
			
			List<Integer> list=new ArrayList<Integer>();
			list.add(10);
			list.add(25);
			mv.addObject("marks",list);
			return mv;
			
			
		}


//	@GetMapping("carts")
//	public ModelAndView  getCartDetail()
//	{
//		ModelAndView mv= new ModelAndView();
//		List<Cart>carts = cartService.getCarts();
//	}
	  
}
