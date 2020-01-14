package com.chatbot.dao;

import java.util.List;

import com.chatbot.ProductQuantityController;
import com.chatbot.model.Product;
import com.chatbot.model.ProductAttribute;
import com.chatbot.model.ProductCategory;
import com.chatbot.model.ProductImages;
import com.chatbot.model.User;

public interface ProductDao {
	public void addProduct(Product add);
	public void updateProduct(Product update);
	public List<Product> listProducts();
	public Product getProductById(int id);
	public void removeProduct(int id);
	public boolean removeProductAttribute(int id );
	public long getTotalQuantity(int id);
	public int getCurrentQuantity(int id);
	public long getAvailableQuantity(int id);
	public List<ProductCategory> listCategories();
	public ProductCategory getCategoryById(int id);
	public boolean updateProductStatus(int id,int isActive);
	public long totalPageCount();
	public List<Product> listPerPage(int start,int limit);
	public List<ProductImages> getImages(int product_id);
	public boolean deleteImage(int id);
	public List<Product> searchFilter(String name);
	public boolean addCategory(ProductCategory add);
	public ProductAttribute getProductQuantityByProductId(int productId,float weight);
	public boolean updateProductQuantity(ProductAttribute productAttribute);
	public boolean saveStock(int productId,int currentQuantity);
	public boolean deleteCategory(int id);
	public boolean updateCategory(ProductCategory category);
}
