package com.chatbot.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chatbot.dao.ProductDao;
import com.chatbot.model.Product;
import com.chatbot.model.ProductAttribute;
import com.chatbot.model.ProductCategory;
import com.chatbot.model.ProductImages;

@Service
public class ProductServiceImpl implements ProductService{
	
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Override
	@Transactional
	public void addProduct(Product add){
		this.productDao.addProduct(add);
	}
	
	@Override
	@Transactional
	public void updateProduct(Product update){
		this.productDao.updateProduct(update);
	}
	
	@Override
	@Transactional
	public List<Product> listProducts(){
		return this.productDao.listProducts();
	}
	
	@Override
	@Transactional
	public Product getProductById(int id){
		return this.productDao.getProductById(id);
	}
	
	@Override
	@Transactional
	public void removeProduct(int id){
		this.productDao.removeProduct(id);
	}
	
	@Override
	@Transactional
	public boolean removeProductAttribute(int id ) {
		return this.productDao.removeProductAttribute(id);
	}
	
	@Override
	@Transactional
	public long getTotalQuantity(int id) {
		return this.productDao.getTotalQuantity(id);
	}
	
	@Override
	@Transactional
	public int getCurrentQuantity(int id) {	
		return this.productDao.getCurrentQuantity(id);
	}

	@Override
	@Transactional
	public long getAvailableQuantity(int id) {
		return this.productDao.getAvailableQuantity(id);
	}

	@Override
	@Transactional
	public List<ProductCategory> listCategories(){
		return this.productDao.listCategories();
	}
	@Override
	@Transactional
	public ProductCategory getCategoryById(int id) {
		return this.productDao.getCategoryById(id);
	}
	
	@Override
	@Transactional
	public boolean updateProductStatus(int id,int isActive) {
		return this.productDao.updateProductStatus(id, isActive);
	}
	@Override
	@Transactional
	public List<ProductImages> getImages(int product_id){
		return this.productDao.getImages(product_id);
	}
	@Override
	@Transactional
	public long totalPageCount() {
		return this.productDao.totalPageCount();
	}
	@Override
	@Transactional
	public List<Product> listPerPage(int start,int limit){
		return this.productDao.listPerPage(start, limit);
	}
	@Override
	@Transactional
	public boolean deleteImage(int id) {
		return this.productDao.deleteImage(id);
	}
	
	@Override
	@Transactional
	public List<Product> searchFilter(String name){
		return this.productDao.searchFilter(name);
	}
	
	@Override
	@Transactional
	public boolean addCategory(ProductCategory add) {
		return this.productDao.addCategory(add);
	}
	@Override
	@Transactional
	public ProductAttribute getProductQuantityByProductId(int productId, float weight) {
		return this.productDao.getProductQuantityByProductId(productId, weight);
	}

	@Override
	@Transactional
	public boolean updateProductQuantity(ProductAttribute productAttribute) {
		return this.productDao.updateProductQuantity(productAttribute);
	}

	@Override
	@Transactional
	public boolean saveStock(int productId, int currentQuantity) {
		return this.productDao.saveStock(productId, currentQuantity);
	}

	@Override
	@Transactional
	public boolean deleteCategory(int id) {
		return this.productDao.deleteCategory(id);
	}

	@Override
	@Transactional
	public boolean updateCategory(ProductCategory category) {
		return this.productDao.updateCategory(category);
	}

}
