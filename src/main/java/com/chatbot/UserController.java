package com.chatbot;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chatbot.model.Encryption;
import com.chatbot.model.User;
import com.chatbot.service.UserService;
import com.chatbot.util.StatusUpdateResponse;
import com.chatbot.util.UserUtil;


/*
 * Spring Controller to handle HTTP request for User Management
 * */
@Controller
public class UserController {

	private UserService userService;

	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService ps) {
		this.userService = ps;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard() {
//		HttpSession session = request.getSession(true);
//		System.out.println(session.getAttribute("name"));
//		if(session.getAttribute("name") != null) {
//		User user = this.userService.getUserByEmail((String)session.getAttribute("email"));
//		model.addAttribute("user",user);
		return "dashboard";
//		}else {
//			session.invalidate();
//			return "redirect:/invaliduser";
//		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		return "register";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		return "UpdateUser";
	}

	@RequestMapping(value = "/invaliduser", method = RequestMethod.GET)
	public String error(Model model) {
		model.addAttribute("error", " Login to continue");
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		model.addAttribute("msg", " Successfully logged out");
		return "login";
	}

	@RequestMapping(value = "/users")
	public String listUsers(Model model, HttpSession session, @ModelAttribute("msg") String msg,
			HttpServletRequest request, @ModelAttribute("success") String success,
			@ModelAttribute("error") String error) {
		String role = (String) session.getAttribute("role");
		if (role == null) {
			return "redirect:/invaliduser";
		}
		int start = 0;
		int limit = 10;
		long pageCount = 0;

		if (role.equals("superadmin")) {
			model.addAttribute("listUsers", this.userService.listPerPage(start, limit));
			long totalcount = this.userService.totalPageCount();
			pageCount = totalcount / limit;
			if (totalcount % limit > 0) {
				pageCount = pageCount + 1;
			}
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("currentPageNumber", 0);
		} else if (role.equals("admin")) {
			model.addAttribute("listUsers", this.userService.listPerPageUsersForAdmin(start, limit));
			long totalcount = this.userService.totalPageCountForAdmin();
			pageCount = totalcount / limit;
			if (totalcount % limit > 0) {
				pageCount = pageCount + 1;
			}
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("currentPageNumber", 0);
		}
		model.addAttribute("msg", msg);
		model.addAttribute("limit", limit);
		// model.addAttribute("listUsers", this.userService.listUsers());
		model.addAttribute("success", success);
		model.addAttribute("error", error);
		return "UserManagement";
	}

	@RequestMapping(value = "/usersNextPage")
	public String usersNextPage(Model model, HttpSession session, @ModelAttribute("msg") String msg,
			HttpServletRequest request) {
		int start = 0;
		int limit = 10;
		long pageCount = 0;
		String role = (String) session.getAttribute("role");
		long totalcount = 0;
		if (role.equals("superadmin")) {
			totalcount = this.userService.totalPageCount();
		} else if (role.equals("admin")) {
			totalcount = this.userService.totalPageCountForAdmin();
		}
		if (request.getParameter("startpage") != null) {
			start = Integer.parseInt(request.getParameter("startpage"));
		}
		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
			pageCount = totalcount / limit;
			if (totalcount % limit > 0) {
				pageCount = pageCount + 1;
				model.addAttribute("pageCount", pageCount);
				start = start * limit;
			}
		}
//		if(request.getParameter("pageCount") != null) {
//			 pageCount=Integer.parseInt(request.getParameter("pageCount"));
//			model.addAttribute("pageCount",pageCount);
//		}
		if (request.getParameter("currentPageNumber") != null) {
			model.addAttribute("currentPageNumber", request.getParameter("currentPageNumber"));
		}
		if (role.equals("superadmin")) {
			model.addAttribute("listUsers", this.userService.listPerPage(start, limit));
		} else if (role.equals("admin")) {
			model.addAttribute("listUsers", this.userService.listPerPageUsersForAdmin(start, limit));
		}
		model.addAttribute("limit", limit);
		return "UserManagement";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String dashboard(HttpServletRequest request, HttpServletResponse response, Model model,
			HttpSession session) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (userService.authenticate(email.trim(), password.trim())) {
			User user = this.userService.getUserByEmail(email);
			if(!user.isAccountStatus()) {
				model.addAttribute("error", "This account is disabled please contact adminitration department to enable it");
				return "login";
			}else if(user.isDeleted()) {
				model.addAttribute("error", "No records found");
				return "login";
			}
			this.addUserInSession(user, session);
			return "dashboard";
		} else {
			model.addAttribute("error", "Please check email or password");
			return "login";
		}

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setContact(request.getParameter("contact"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(Encryption.getMd5(request.getParameter("password")));
		user.setRole(request.getParameter("role"));
		user.setAddress(request.getParameter("address"));
		try {
			Date dob = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("dob"));
			user.setDob(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (this.userService.isContactExist(user.getContact())) {
			model.addAttribute("errorContact", "Contact Already exist");
			model.addAttribute("userAdd", user);
			return "UpdateUser";
		} else if (this.userService.isEmailExist(user.getEmail())) {
			model.addAttribute("errorEmail", "Email Already exist");
			model.addAttribute("userAdd", user);
			return "UpdateUser";
		} else {
			try {
				userService.addUser(user);
				model.addAttribute("success", "Record added successfully");
				return "redirect:/users";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("error", "Could not add records");
				return "redirect:/users";
			}

		}
	}

	@RequestMapping("/remove/{id}")
	public String removePerson(@PathVariable("id") int id, Model model) {
		try {
			this.userService.removeUser(id);
			model.addAttribute("error", "Record Deleted");
		} catch (Exception e) {
			model.addAttribute("error", "Can't remove this user");
		}
		return "redirect:/users";
	}

	@RequestMapping("/edit/{id}")
	public String editPerson(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", this.userService.getUserById(id));
		return "UpdateUser";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String editUser(@PathVariable("id") int id, HttpServletRequest request, Model model) {
		User user = this.userService.getUserById(id);
		user = UserUtil.setUser(request, user);
		this.userService.updateUser(user);
		model.addAttribute("success", "Record updated successfully for : " + user.getName());
		return "redirect:/users";
	}

	public void addUserInSession(User user, HttpSession session) {
		session.setAttribute("name", user.getName());
		session.setAttribute("userId", user.getId());
		session.setAttribute("email", user.getEmail());
		session.setAttribute("role", user.getRole());
		if (!user.getRole().equals("admin")) {
			session.setAttribute("newUsersCount", this.userService.newUserCount());
		} else {
			session.setAttribute("newUsersCount", this.userService.newUserCountForAdmin());
		}
		session.setAttribute("paymentPending", this.userService.paymentPending());
		session.setAttribute("newOrders", this.userService.newOrders());
		session.setAttribute("productOutOfStock", this.userService.productsOutOfStock());
	}

	// @ResponseBody
	@RequestMapping(value = "/searchUser", method = RequestMethod.GET, produces = "application/json")
	public String searchUser(@RequestBody String val, HttpServletRequest request, Model model, HttpSession session) {
		String value = request.getParameter("search");
		if (value != null) {
			String role = (String) session.getAttribute("role");
			int start = 0;
			int limit = 10;
			long pageCount = 0;

			if (role.equals("superadmin")) {
				List<User> userlist = this.userService.searchFilter(value);
				if (!userlist.isEmpty()) {
					model.addAttribute("listUsers", userlist);
				} else {
					model.addAttribute("listUsers", this.userService.listPerPage(start, limit));
					model.addAttribute("warning", "No records found for your search");
				}
				long totalcount = this.userService.totalPageCount();
				pageCount = totalcount / limit;
				if (totalcount % limit > 0) {
					pageCount = pageCount + 1;
				}
				model.addAttribute("pageCount", pageCount);
				model.addAttribute("currentPageNumber", 0);
			} else if (role.equals("admin")) {
				List<User> userlist = this.userService.serachforAdmin(value);
				if (!userlist.isEmpty()) {
					model.addAttribute("listUsers", userlist);
				} else {
					model.addAttribute("listUsers", this.userService.listPerPageUsersForAdmin(start, limit));
					model.addAttribute("warning", "No records found for your search");
				}
				long totalcount = this.userService.totalPageCountForAdmin();
				pageCount = totalcount / limit;
				if (totalcount % limit > 0) {
					pageCount = pageCount + 1;
				}
				model.addAttribute("pageCount", pageCount);
				model.addAttribute("currentPageNumber", 0);
			}
			// model.addAttribute("msg",msg);
			model.addAttribute("limit", limit);
			model.addAttribute("search", value);
		}
		// model.addAttribute("listUsers", this.userService.listUsers(
		return "UserManagement";
	}
	
	
	@RequestMapping(value = "/userStatusUpdate", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<StatusUpdateResponse> updateUserStatus( HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		User user = userService.getUserById(id);
		boolean status = Boolean.parseBoolean(request.getParameter("update"));
		user.setAccountStatus(status);
		userService.updateUser(user);
		if(status) {
			return new ResponseEntity<StatusUpdateResponse>(new StatusUpdateResponse(id, user.getName()+" Enabled Successfully!", status),HttpStatus.OK);
		}else { 
			return new ResponseEntity<StatusUpdateResponse>(new StatusUpdateResponse(id, user.getName()+" Disabled Successfully!", status),HttpStatus.OK);
		}

	}
}
