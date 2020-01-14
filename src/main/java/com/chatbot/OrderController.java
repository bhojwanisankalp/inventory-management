package com.chatbot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chatbot.model.Orders;
import com.chatbot.model.Product;
import com.chatbot.model.ProductOrdered;
import com.chatbot.model.User;
import com.chatbot.service.OrderService;

/*
 * Spring Controller to handle HTTP request for Order Management
 * */
@Controller
//@RequestMapping(value= "/orderManagement")
public class OrderController {

	private OrderService orderService;

	@Autowired(required = true)
	@Qualifier(value = "orderService")
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	/*
	 * To get list of orders placed.
	 * @param Model to send data in response
	 * @param ModelAttribute to fetch data coming from request
	 * @return String name of the jsp file to view resolver with the model of order list result.
	 * */
	@RequestMapping(value = "orderManagement")
	public String orders(Model model, @ModelAttribute("error") String msg, @ModelAttribute("success") String success,
			@ModelAttribute("warning") String warning) {

		int start = 0;
		int limit = 10;
		long pageCount = 0;
		List<Orders> orderslist = this.orderService.listPerPage(start, limit);
		// List<Orders> orderslist = this.orderService.listOrders();
		for (Orders r : orderslist) {
			List<ProductOrdered> productOrdereds = r.getProduct_ordered();
			StringBuilder names = new StringBuilder();
			Set<String> productName = new HashSet<>();
			for (ProductOrdered p : productOrdereds) {
				productName.add(this.orderService.getProductNameById(p.getProduct_id()));
				p.setName(this.orderService.getProductNameById(p.getProduct_id()));
				p.setPrice(this.orderService.getPrice(p.getProduct_id(), p.getWeight()));
				System.out.println(">>>>>>>>>>>>"+this.orderService.getPrice(p.getProduct_id(), p.getWeight()));
			}
			for (String name : productName) {
				if (name != null) {
					names.append(name + ", ");
				}
			}
			if(names.length() > 0) {
				names.deleteCharAt(names.length()-2);
			}
			r.setProductNames(names.toString().trim());
			r.setUserName(this.orderService.getUserNameById(r.getUser_id()));
			r.setUserEmail(this.orderService.getUserEmailById(r.getUser_id()));
		}
		long totalcount = this.orderService.totalPageCount();
		pageCount = totalcount / limit;
		if (totalcount % limit > 0) {
			pageCount = pageCount + 1;
		}
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("currentPageNumber", 0);
		model.addAttribute("orderslist", orderslist);
		model.addAttribute("limit", limit);
		model.addAttribute("error", msg);
		model.addAttribute("success", success);
		model.addAttribute("warning", warning);
		return "OrderManagement";
	}

	/*
	 * To get next list of orders placed.
	 * @param Model to send data in response.
	 * @param HttpSession to get current session object.
	 * @param ModelAttribute to fetch data coming from request
	 * @return String name of the jsp file to view resolver with the model of next page result.
	 * */
	@RequestMapping(value = "ordersNextPage")
	public String ordersNextPage(Model model, HttpSession session, @ModelAttribute("msg") String msg,
			HttpServletRequest request) {
		int start = 0;
		int limit = 10;
		long pageCount = 0;
		String role = (String) session.getAttribute("role");
		long totalcount = this.orderService.totalPageCount();

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

		if (request.getParameter("currentPageNumber") != null) {
			model.addAttribute("currentPageNumber", request.getParameter("currentPageNumber"));
		}
		List<Orders> orderslist = this.orderService.listPerPage(start, limit);
		for (Orders r : orderslist) {
			List<ProductOrdered> productOrdereds = r.getProduct_ordered();
			StringBuilder names = new StringBuilder();
			Set<String> productName = new HashSet<>();
			for (ProductOrdered p : productOrdereds) {
				productName.add(this.orderService.getProductNameById(p.getProduct_id()));
				p.setName(this.orderService.getProductNameById(p.getProduct_id()));
			}
			for (String name : productName) {
				if (name != null) {
				names.append(name + ", ");
				}
			}
			if(names.length() > 0) {
				names.deleteCharAt(names.length()-2);
			}
			r.setProductNames(names.toString().trim());
			r.setUserName(this.orderService.getUserNameById(r.getUser_id()));
			r.setUserEmail(this.orderService.getUserEmailById(r.getUser_id()));
		}

		model.addAttribute("limit", limit);
		model.addAttribute("orderslist", orderslist);
		return "OrderManagement";
	}

	/*
	 * To update the order status.
	 * @param Model to send data in response.
	 * @param RequestParam to get specific request parameter.
	 * @return String name of the jsp file to view resolver.
	 * */
	@RequestMapping(value = "OrderStatus")
	public String orderStatus(@RequestParam("orderId") int order_id, @RequestParam("orderStatus") int orderStatus,
			Model model) {
		boolean flag = this.orderService.updateOrderStatus(order_id, orderStatus);
		boolean update = false;
		if (orderStatus == 1) {
			update = this.orderService.orderStatus("Confirmed", order_id);
		} else if (orderStatus == 2) {
			update = this.orderService.orderStatus("Paid", order_id);
		} else if (orderStatus == 3) {
			update = this.orderService.orderStatus("Dispatching", order_id);
		} else if (orderStatus == 4) {
			update = this.orderService.orderStatus("Dispatched", order_id);
		} else if (orderStatus == 5) {
			update = this.orderService.orderStatus("Delivered", order_id);
		}

		if (flag && update) {
			model.addAttribute("success", "Updated Successfully");
			return "redirect:/orderManagement";
		} else {
			model.addAttribute("error", "Something went wrong with the update");
			return "redirect:/orderManagement";
		}
	}

	/*
	 * To update the order status.
	 * @param Model to send data in response.
	 * @param RequestParam to get specific request parameter.
	 * @return String name of the jsp file to view  resolver with model of the search user result.
	 * */
	@RequestMapping(value = "/searchOrder", method = RequestMethod.GET)
	public String searchOrder(HttpServletRequest request, Model model) {
		String value = request.getParameter("search");
		List<Orders> orderlist = new ArrayList<>();
		if (value != null) {
			orderlist = this.orderService.searchFilter(value);
		}
		int start = 0;
		int limit = 10;
		long pageCount = 0;
		if (orderlist != null) {
			// List<Orders> orderslist = this.orderService.listOrders();
			for (Orders r : orderlist) {
				List<ProductOrdered> productOrdereds = r.getProduct_ordered();
				StringBuilder names = new StringBuilder();
				Set<String> productName = new HashSet<>();
				for (ProductOrdered p : productOrdereds) {
					productName.add(this.orderService.getProductNameById(p.getProduct_id()));
					p.setName(this.orderService.getProductNameById(p.getProduct_id()));
				}
				for (String name : productName) {
					if (name != null) {
						names.append(name + ", ");
					}
				}
				if(names.length() > 0) {
					names.deleteCharAt(names.length()-2);
				}
				r.setProductNames(names.toString().trim());
				r.setUserName(this.orderService.getUserNameById(r.getUser_id()));
				r.setUserEmail(this.orderService.getUserEmailById(r.getUser_id()));
			}
			model.addAttribute("orderslist", orderlist);

			long totalcount = this.orderService.totalPageCount();
			pageCount = totalcount / limit;
			if (totalcount % limit > 0) {
				pageCount = pageCount + 1;
			}
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("currentPageNumber", 0);
			model.addAttribute("limit", limit);
			return "OrderManagement";
		} else {
			model.addAttribute("warning", "Can't find record");
			return "redirect:/orderManagement";
		}

	}
}
