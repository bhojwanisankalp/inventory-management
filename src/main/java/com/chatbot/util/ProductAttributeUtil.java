package com.chatbot.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import com.chatbot.model.Product;
import com.chatbot.model.ProductAttribute;
import com.chatbot.model.ProductImages;
import com.chatbot.model.ProductQuantity;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ProductAttributeUtil {
	public static List<ProductAttribute> addParameters(String[] weights, String[] prices,String[] quantity,Product product){
			List<ProductAttribute> list=new ArrayList<>();
			 for(int i=0;i<weights.length;i++) {
             	ProductAttribute attribute=new ProductAttribute();
             	ProductQuantity productQuantity = new ProductQuantity();
             	attribute.setWeight(Float.parseFloat(weights[i]));
             	attribute.setPrice(Float.parseFloat(prices[i]));
             	//attribute.setProductQuantity(Integer.parseInt(quantity[i]));
             	productQuantity.setQuantity(Integer.parseInt(quantity[i]));
             	productQuantity.setAttribute(attribute);
             	attribute.setQuantity(productQuantity);
             	productQuantity.setAttribute(attribute);
             	attribute.setProduct(product);
             	list.add(attribute);
             }
			return list;
	}
	
	public static List<ProductImages> addImages(CommonsMultipartFile[] pic, Product product) {
		List<ProductImages> imageList = new ArrayList<>();
    	for (CommonsMultipartFile image : pic){
    		if(image.getBytes().length > 0) {
    		ProductImages productImages=new ProductImages();
    		productImages.setImage(image.getBytes());		
    		productImages.setProduct(product);
    		imageList.add(productImages);
    		}
    	}
    	return imageList;
	}
	
	public static List<ProductImages> convertIntoBinaryString(List<ProductImages> productImages){
		//List<String> binaryImages = new ArrayList<>();
		for(ProductImages p : productImages) {
			p.setBinaryImage(DatatypeConverter.printBase64Binary(p.getImage()));
		}
		return productImages;
	}
	//public static Map<int,int> updateQuantity(String [])
}
