package com.chatbot;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.chatbot.model.Product;
import com.chatbot.model.ProductAttribute;
import com.chatbot.service.ProductService;
import com.chatbot.util.ProductAttributeUtil;

/*
 * Spring Controller to handle HTTP request for Product Quantity Management
 * */
@Controller
public class ProductQuantityController {

	private ProductService productService;
	
	@Autowired(required=true)
	@Qualifier(value="productService")
	public void setUserService(ProductService productService){
		this.productService = productService;
	}

	@RequestMapping(value= "/productQuantity")
	public String product(Model model,@ModelAttribute("success") String msg) {
		int start = 0 ;
		int limit=10;
		long pageCount=0;
		long totalcount= this.productService.totalPageCount();
		pageCount =  totalcount / limit;
		if(totalcount % limit > 0) {
			pageCount = pageCount+1;
		}
		model.addAttribute("pageCount",pageCount);
		model.addAttribute("currentPageNumber",0);
		List<Product> list=this.productService.listPerPage(start, limit);
		for(Product p: list) {
		try{
			p.setCategoryName(this.productService.getCategoryById(p.getProductId()).getName());
			p.setTotalQuantity(this.productService.getAvailableQuantity(p.getId()));
		}catch(NullPointerException e) {
			p.setTotalQuantity(0);
			}
		}
		model.addAttribute("limit", limit);
		model.addAttribute("productlist",list );
		model.addAttribute("success",msg);
		return "ProductQuantity";
	}
	@RequestMapping(value= "/productQuantityNextPage")
	public String productQuantityNextPage(Model model,HttpSession session,@ModelAttribute("msg") String msg,HttpServletRequest request) {
		int start = 0 ;
		int limit=10;
		long pageCount=0;
		
		long totalcount= this.productService.totalPageCount();
		
		if(request.getParameter("startpage")!=null) {
			 start =Integer.parseInt(request.getParameter("startpage"));
		}
		if(request.getParameter("limit") != null) {
			 limit=Integer.parseInt(request.getParameter("limit"));
			 pageCount =  totalcount / limit;
				if(totalcount % limit > 0) {
					pageCount = pageCount+1;
					model.addAttribute("pageCount",pageCount);
					start = start*limit;
				}
		}
//		if(request.getParameter("pageCount") != null) {
//			 pageCount=Integer.parseInt(request.getParameter("pageCount"));
//			model.addAttribute("pageCount",pageCount);
//		}
		if(request.getParameter("currentPageNumber")!=null) {
			model.addAttribute("currentPageNumber",request.getParameter("currentPageNumber"));
			}
		List<Product> list=this.productService.listPerPage(start, limit);
		for(Product p: list) {
		try{
			p.setCategoryName(this.productService.getCategoryById(p.getProductId()).getName());
			final long quantity = this.productService.getAvailableQuantity(p.getId());
			p.setTotalQuantity(quantity);
			if (quantity <= 10 && p.getIsActive()!=1) {
				p.setIsActive(1);
				this.productService.updateProductStatus(p.getId(), 1);
			}
		}catch(NullPointerException e) {
			p.setTotalQuantity(0);
		}
		}
		model.addAttribute("limit", limit);
		model.addAttribute("productlist",list );
		return "ProductQuantity";
	}
	//@ResponseBody 
	@RequestMapping(value="/quantity", method = RequestMethod.POST)
    public String getQuantity(HttpServletRequest request, Model model) {
		int id=Integer.parseInt(request.getParameter("productId"));
		String[] quantity=request.getParameterValues("quantityInput[]");
		this.productService.updateProductStatus(id, Integer.parseInt(request.getParameter("isActive")));
		boolean flag = this.productService.removeProductAttribute(id);
        if(flag) {
    		String[] weights=request.getParameterValues("weights[]");
    		String[] prices=request.getParameterValues("prices[]");
        	Product product=this.productService.getProductById(id);
        	List<ProductAttribute> attributelist=ProductAttributeUtil.addParameters(weights, prices,quantity,product);
        	product.setAttribute(attributelist);
        	this.productService.updateProduct(product);
        	model.addAttribute("success","Quantity Updated Successfully for "+product.getName());
        	return "redirect:/productQuantity ";
        }
        else {
        	return "redirect:/productQuantity";
        }
    }
	@RequestMapping(value="/test/images", method=RequestMethod.GET)
	public  String getImages() {
		
		 return "test";
	}
	//@ResponseBody
	@RequestMapping(value="/searchProductQuantity" ,  method = RequestMethod.GET , produces = "application/json")
	public String searchUser( HttpServletRequest request,Model model){
		String value = request.getParameter("search");
		List<Product> productlist = new ArrayList<>();
		if(value != null) {
			productlist = this.productService.searchFilter(value);
		}
		productlist.forEach(product->{
			product.setCategoryName(this.productService.getCategoryById(product.getProductId()).getName());
			try{
				product.setCategoryName(this.productService.getCategoryById(product.getProductId()).getName());
				product.setTotalQuantity(this.productService.getAvailableQuantity(product.getId()));
			}catch(NullPointerException e) {
				product.setTotalQuantity(0);
			}
		});
		int start = 0 ;
		int limit=10;
		long pageCount=0;
		long totalcount= this.productService.totalPageCount();
		pageCount =  totalcount / limit;
		if(totalcount % limit > 0) {
			pageCount = pageCount+1;
		}
		model.addAttribute("pageCount",pageCount);
		model.addAttribute("currentPageNumber",0);
		model.addAttribute("productlist",productlist );
		model.addAttribute("limit", limit);
		return "ProductQuantity";
	}
}
