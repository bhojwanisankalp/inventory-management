package com.chatbot;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.chatbot.model.Product;
import com.chatbot.model.ProductAttribute;
import com.chatbot.model.ProductCategory;
import com.chatbot.model.ProductImages;
import com.chatbot.model.User;
import com.chatbot.service.ProductService;
import com.chatbot.util.ProductAttributeUtil;

@Controller
public class ProductController {

	private ProductService productService;

	@Autowired(required = true)
	@Qualifier(value = "productService")
	public void setUserService(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "/product")
	public String product(Model model, @ModelAttribute("error") String error,
			@ModelAttribute("success") String success,@ModelAttribute("warning") String warning) {

		int start = 0;
		int limit = 10;
		long pageCount = 0;
		long totalcount = this.productService.totalPageCount();
		pageCount = totalcount / limit;
		if (totalcount % limit > 0) {
			pageCount = pageCount + 1;
		}
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("currentPageNumber", 0);
		List<Product> list = this.productService.listPerPage(start, limit);
		
		list.forEach(product -> {
			product.setCategoryName(this.productService.getCategoryById(product.getProductId()).getName());
//			final long quantity = this.productService.getTotalQuantity(product.getId());
//			if (quantity <= 10) {
//				product.setIsActive(1);
//				this.productService.updateProductStatus(product.getId(), 1);
//			}
		});
		model.addAttribute("limit", limit);
		model.addAttribute("productlist", list);
		model.addAttribute("success", success);
		model.addAttribute("error", error);
		model.addAttribute("warning",warning);
		return "Product Management";
	}

	@RequestMapping(value = "/productNextPage")
	public String productNextPage(Model model, HttpSession session, @ModelAttribute("warning") String warning,
			HttpServletRequest request,@ModelAttribute("error")String error) {
		int start = 0;
		int limit = 10;
		long pageCount = 0;

		long totalcount = this.productService.totalPageCount();

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
		model.addAttribute("limit", limit);
		List<Product> list = this.productService.listPerPage(start, limit);
		list.forEach(product -> {
			product.setCategoryName(this.productService.getCategoryById(product.getProductId()).getName());
			final long quantity = this.productService.getTotalQuantity(product.getId());
			if (quantity <= 10 && product.getIsActive()!=1) {
				product.setIsActive(1);
				this.productService.updateProductStatus(product.getId(), 1);
			}
		});
		model.addAttribute("productlist", list);
		model.addAttribute("error",error);
		
		return "Product Management";
	}

	@RequestMapping(value = "/addProduct")
	public String addProduct(Model model) {
		List<ProductCategory> categories = this.productService.listCategories();
		model.addAttribute("categorylist", categories);

		return "AddProduct";
	}

	@RequestMapping(value = "/testModal")
	public String testModal(Model model) {
		model.addAttribute("listproducts", this.productService.listProducts());
		return "TestModal";
	}

	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	public String saveProduct(HttpServletRequest request, @RequestParam CommonsMultipartFile[] pic, Model model, HttpSession session) {
		if (pic != null && pic.length > 0) {
			String[] weights = request.getParameterValues("weight[]");
			String[] prices = request.getParameterValues("price[]");
			String[] quantity = request.getParameterValues("quantity[]");
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			Product product = new Product();
			product.setName(request.getParameter("name"));
			product.setProductId(categoryId);
			product.setCreated_by((Integer)session.getAttribute("userId"));
			List<ProductAttribute> attributelist = ProductAttributeUtil.addParameters(weights, prices, quantity,
					product);
			product.setAttribute(attributelist);
			List<ProductImages> imageList = ProductAttributeUtil.addImages(pic, product);
			if(!imageList.isEmpty()) {
				product.setImage(imageList);
			}
			product.setDescription(request.getParameter("description"));
			this.productService.addProduct(product);
			model.addAttribute("success", "Product added successfully");
		}
		return "redirect:/product";
	}

	@RequestMapping("/removeProduct/{id}")
	public String removePerson(@PathVariable("id") int id, Model model) {
		try {
			this.productService.removeProduct(id);
			model.addAttribute("error", "Product successfully deleted");
		}catch(Exception e ) {
			model.addAttribute("error", "Can't remove this product");
		}
		return "redirect:/product";
	}

	@RequestMapping("/editProduct/{id}")
	public String editPerson(@PathVariable("id") int id, Model model) {
		Product product = this.productService.getProductById(id);
		ProductCategory productCategory = this.productService.getCategoryById(product.getProductId());
		product.setCategoryName(productCategory.getName());
		model.addAttribute("categorylist", this.productService.listCategories());
		model.addAttribute("product", product);

		// List<String> binaryImage =
		// ProductAttributeUtil.convertIntoBinaryString(this.productService.getImages(id));
		List<ProductImages> binaryImage = ProductAttributeUtil
				.convertIntoBinaryString(this.productService.getImages(id));
		model.addAttribute("pics", binaryImage);
		return "UpdateProduct";
	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST, consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public String updateProduct(HttpServletRequest request, @RequestParam CommonsMultipartFile[] pic, Model model) {

		if (pic != null && pic.length > 0) {
			int id = Integer.parseInt(request.getParameter("id"));
			boolean flag = this.productService.removeProductAttribute(id);
			// if(flag) {
			String categoryId = request.getParameter("categoryId");
			String[] quantity = request.getParameterValues("quantity[]");
			String[] weights = request.getParameterValues("weight[]");
			String[] prices = request.getParameterValues("price[]");
			Product product = this.productService.getProductById(id);
			String description = request.getParameter("description");
			product.setName(request.getParameter("name"));
			product.setProductId(Integer.parseInt(categoryId));
			List<ProductAttribute> attributelist = ProductAttributeUtil.addParameters(weights, prices, quantity,
					product);
			product.setAttribute(attributelist);
			List<ProductImages> imageList = ProductAttributeUtil.addImages(pic, product);
			if(!imageList.isEmpty()) {
			product.setImage(imageList);
			}
			product.setDescription(description);
			this.productService.updateProduct(product);
			model.addAttribute("success", "Product updated successfully");
		}
		return "redirect:/product";
	}

	@RequestMapping(value = "/deleteImage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity deleteImage(@RequestBody String json, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			this.productService.deleteImage(id);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {

			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	// @ResponseBody
	@RequestMapping(value = "/searchProduct", method = RequestMethod.GET, produces = "application/json")
	public String searchUser(@RequestBody String val, HttpServletRequest request, Model model) {
		String value = request.getParameter("search");

		List<Product> productlist = new ArrayList<>();
		if (value != null) {
			productlist = this.productService.searchFilter(value);
			if (!productlist.isEmpty()) {
				productlist.forEach(product -> {
					product.setCategoryName(this.productService.getCategoryById(product.getProductId()).getName());

				});
				model.addAttribute("productlist", productlist);
				int start = 0;
				int limit = 10;
				long pageCount = 0;
				long totalcount = this.productService.totalPageCount();
				pageCount = totalcount / limit;
				if (totalcount % limit > 0) {
					pageCount = pageCount + 1;
				}
				model.addAttribute("pageCount", pageCount);
				model.addAttribute("currentPageNumber", 0);
				model.addAttribute("limit", limit);

			}
			return "Product Management";
		} else {
			model.addAttribute("warning", "Can't find record");
			return "redirect:/product";
		}

	}
	@RequestMapping(value="/category", method=RequestMethod.GET)
	public String categoryPage(Model model) {
		model.addAttribute("categorylist",this.productService.listCategories());
		return "Category";
	}
	
	@RequestMapping(value="/saveCategory",method=RequestMethod.POST)
	public String addCategory(HttpServletRequest request, Model model) {
		//model.addAttribute("categorylist",);
		ProductCategory category = new ProductCategory();
		if(request.getParameter("name") != null) {
			category.setName(request.getParameter("name"));
		}
		boolean flag = this.productService.addCategory(category);
		if(flag) {
			model.addAttribute("success","Category add successfully");
		}else {
			model.addAttribute("error","Something went wrong");
		}
		return "redirect:/product";
	}
	@RequestMapping(value="/deleteCategory",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity deleteCategory(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		try{
			this.productService.deleteCategory(id);
			return new ResponseEntity(HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
