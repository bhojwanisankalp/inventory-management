package com.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.chatbot.model.ImagesWithDescription;
import com.chatbot.model.Orders;
import com.chatbot.model.Product;
import com.chatbot.model.ProductAttribute;
import com.chatbot.model.ProductCategory;
import com.chatbot.model.ProductImages;
import com.chatbot.model.ProductOrdered;
import com.chatbot.model.User;
import com.chatbot.service.ApiService;
import com.chatbot.util.Customer;
import com.chatbot.util.EmailSender;
import com.chatbot.util.OrdersUtil;
import com.chatbot.util.ProductAttributeUtil;
import com.chatbot.util.UserUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Spring Controller to handle HTTP request for API Management
 * */
@RestController
@RequestMapping("/v1")
public class ApiController {
	private ApiService apiService;

	@Autowired(required = true)
	@Qualifier(value = "apiService")
	public void setApiService(ApiService apiService) {
		this.apiService = apiService;
	}

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public @ResponseBody List<ProductCategory> getProductApi(@RequestParam("key") String key) {
		if (key.equals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")) {
			return this.apiService.listCategories();
		} else {
			return new ArrayList<ProductCategory>();
		}
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public @ResponseBody List<Product> getProduct(@RequestParam("id") String category_id,
			@RequestParam("key") String key) {
		List<Product> productList = this.apiService.listProducts(Integer.parseInt(category_id));
		// String imageBinary = DatatypeConverter.printBase64Binary(product.getImage());
		// product.setImageBinary(imageBinary);
		if (key.equals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")) {
			return productList;
		} else {
			return new ArrayList<Product>();
		}
	}

	@RequestMapping(value = "/attributes", method = RequestMethod.GET)
	public @ResponseBody List<ProductAttribute> getAttributes(@RequestParam("id") String id,
			@RequestParam("key") String key) {
		List<ProductAttribute> productAttributes = this.apiService.getAttribute(Integer.parseInt(id));
		// String imageBinary = DatatypeConverter.printBase64Binary(product.getImage());
		// product.setImageBinary(imageBinary);
		return productAttributes;
	}

	@RequestMapping(value = "/images", method = RequestMethod.GET)
	public @ResponseBody ImagesWithDescription getImages(@RequestParam("id") String id,
			@RequestParam("key") String key) {
		List<ProductImages> images = this.apiService.getImages(Integer.parseInt(id));
		//List<String> binaryImage = ProductAttributeUtil.convertIntoBinaryString(images);
		ImagesWithDescription imagesWithDescription = new ImagesWithDescription();
		imagesWithDescription.setDescription(this.apiService.getProductDescriptionById(Integer.parseInt(id)));
		//imagesWithDescription.setImages(binaryImage);
		return imagesWithDescription;
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity addOrder(@RequestBody String json, @RequestParam("key") String key)
			throws JsonParseException, JsonMappingException, IOException {
		if (key.equals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")) {
			ObjectMapper mapper = new ObjectMapper();
			Customer cutomer = mapper.readValue(json, Customer.class);
			Map<String, String> attribute = cutomer.getAttribute();
			Map<String, String> user = cutomer.getUser();
			if (!this.apiService.isEmailExist(user.get("email").trim())) {
				User userEntity = UserUtil.getUser(user);
				this.apiService.addUser(userEntity);
			}
			Orders order=OrdersUtil.order(this.apiService.getUserId(user.get("email")), attribute, cutomer.getTotalamount(), this.apiService);
			EmailSender.sendMail("sankalp.b@deeplogix.tech", user.get("email"), "API Testing", "<h1>Your order for blue dreams is recieved please proceed for payment</h1>");
			this.apiService.addOrder(order);
			return new ResponseEntity(HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.LOCKED);
		}
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> test() {
		Map<String, String> map = new HashMap<>();
		map.put("1", "First Entry");
		map.put("2", "Second Entry");
		map.put("3", "Third Entry");
		map.put("4", "Forth Entry");
		map.put("5", "Fifth Entry");
		// String imageBinary = DatatypeConverter.printBase64Binary(product.getImage());
		// product.setImageBinary(imageBinary);
		return map;
	}

	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public ResponseEntity emailConfirmation(@RequestParam("key") String key, @RequestParam("order_id") String id) {
		try {
			if (key != null && id != null) {
				if (key.equals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")) {
					Orders order = this.apiService.getOrderById(Integer.parseInt(id));
					User user = this.apiService.getUserById(order.getUser_id());
					StringBuilder productName = new StringBuilder();
					ProductOrdered ordered =new ProductOrdered();
					List<ProductOrdered> listorder = order.getProduct_ordered();
					listorder.forEach(p -> {
						p.setName(this.apiService.getProductNameById(p.getProduct_id()));
						productName.append(p.getName() + " ,");
						ordered.setName(p.getName());
						p.setPrice(this.apiService.getPrice(p.getProduct_id(), p.getWeight()));
//						ProductAttribute attribute  = this.apiService.getProductQuantityByProductId(p.getProduct_id(), p.getWeight());
//						int quantiy = attribute.getProductQuantity();
//						quantiy -= 1;
//						attribute.setProductQuantity(quantiy);
						//boolean flag = this.apiService.updateProductQuantity(attribute);
//						if(flag) {
//							long totalQuantity = this.apiService.getTotalQuantity(p.getProduct_id());
//							if(totalQuantity <= 10) {
//								this.apiService.updateProductStatus(p.getProduct_id(), 1);
//							}
//						}
					});
					
					EmailSender.sendMail("sankalp.b@deeplogix.tech", user.getEmail(), "Order Confirmed",
							 EmailSender.htmlInvoice(user, order, listorder));
				}

			}
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			String msg = "Something went wrong";
			e.printStackTrace();
			return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
		}
	}

}
