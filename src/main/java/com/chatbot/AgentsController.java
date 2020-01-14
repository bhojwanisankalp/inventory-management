package com.chatbot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chatbot.model.Agents;
import com.chatbot.model.Encryption;
import com.chatbot.model.User;
import com.chatbot.service.AgentsService;
import com.chatbot.service.ApiService;
import com.chatbot.util.AgentUtil;
import com.chatbot.util.EmailSender;
import com.chatbot.util.StatusUpdateResponse;

/*
 * Spring Controller to handle HTTP request for Agents Management
 * */
@Controller
public class AgentsController {

	private AgentsService agentsService;

	@Autowired(required = true)
	@Qualifier(value = "agentsService")
	public void setAgentsService(AgentsService agentsService) {
		this.agentsService = agentsService;
	}

	@RequestMapping(value = "agents")
	public String agents(Model model, HttpSession session, @ModelAttribute("success") String success,
			@ModelAttribute("msg") String msg, @ModelAttribute("error") String error) {
		String role = (String) session.getAttribute("role");
		if (role == null || role.equals("admin")) {
			return "redirect:/invaliduser";
		}
		int start = 0;
		int limit = 10;
		long pageCount = 0;
		if (role.equals("superadmin")) {
			model.addAttribute("listUsers", this.agentsService.listPerPage(start, limit));
			long totalcount = this.agentsService.totalPageCount();
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
		return "AgentsManagement";
	}

	@RequestMapping(value="agentsNextPage")
	public String nextAgentsList(Model model, HttpSession session,HttpServletRequest request) {
		int start = 0;
		int limit = 10;
		long pageCount = 0;
		String role = (String) session.getAttribute("role");
		long totalcount = 0;
		if (role.equals("superadmin")) {
			totalcount = this.agentsService.totalPageCount();
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
			model.addAttribute("listUsers", this.agentsService.listPerPage(start, limit));
		} 
		model.addAttribute("limit", limit);
		return "AgentsManagement";
	}
	
	@RequestMapping(value = "addAgentForm")
	public String agentForm() {
		//TODO
		return "AddOrUpdateAgent";
	}
	
	@RequestMapping(value = "addAgent")
	public String addAgent(HttpServletRequest request, Model model, HttpSession session) {
		Agents user = AgentUtil.allocateAgentParameters(request, session,null);
		if (this.agentsService.isContactExist(user.getContact())) {
			model.addAttribute("errorContact", "Contact Already exist");
			model.addAttribute("userAdd", user);
			return "AddOrUpdateAgent";
		} else if (this.agentsService.isEmailExist(user.getEmail())) {
			model.addAttribute("errorEmail", "Email Already exist");
			model.addAttribute("userAdd", user);
			return "AddOrUpdateAgent";
		} else {
			try {
				agentsService.addAgent(user);
				EmailSender.sendMail("sankalp.b@deeplogix.tech", user.getEmail(), "Password",
						"<h2>Your password is: " + user.getUnEncriptedPassword() + "</h2>");
				model.addAttribute("success", "Record added successfully");
				return "redirect:/agents";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("error", "Could not add records");
				return "redirect:/agents";
			}

		}
	}

	@RequestMapping(value = "/removeAgent/{id}")
	public String agentDelete(@PathVariable("id") int id, Model model) {
		try {
			this.agentsService.removeAgent(id);
			model.addAttribute("error", "Record Deleted");
		} catch (Exception e) {
			model.addAttribute("error", "Can't remove this user");
		}
		return "redirect:/agents";
	}
	
	@RequestMapping(value="/editAgent/{id}")
	public String editAgentForm(Model model,@PathVariable("id") int id) {
		model.addAttribute("user", this.agentsService.getAgentById(id));
		return "AddOrUpdateAgent";
	}
	
	@RequestMapping(value= "/updateAgent/{id}")
	public String updateAgents(@PathVariable("id") int id,HttpServletRequest request,HttpSession session,Model model) {
		Agents user = this.agentsService.getAgentById(id);
		user = AgentUtil.allocateAgentParameters(request, session,user);
		this.agentsService.updateAgent(user);
		model.addAttribute("success", "Record updated successfully for : " + user.getName());
		return "redirect:/agents";
	}
	
	@RequestMapping(value = "/agentStatusUpdate", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<StatusUpdateResponse> updateUserStatus( HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		Agents user = agentsService.getAgentById(id);
		boolean status = Boolean.parseBoolean(request.getParameter("update"));
		user.setAccountStatus(status);
		agentsService.updateAgent(user);
		if(status) {
			return new ResponseEntity<StatusUpdateResponse>(new StatusUpdateResponse(id, user.getName()+" Enabled Successfully!", status),HttpStatus.OK);
		}else { 
			return new ResponseEntity<StatusUpdateResponse>(new StatusUpdateResponse(id, user.getName()+" Disabled Successfully!", status),HttpStatus.OK);
		}

	}
}
