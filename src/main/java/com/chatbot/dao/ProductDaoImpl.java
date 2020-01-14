package com.chatbot.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.chatbot.model.Product;
import com.chatbot.model.ProductAttribute;
import com.chatbot.model.ProductCategory;
import com.chatbot.model.ProductImages;
import com.chatbot.model.StockManagement;
import com.chatbot.model.User;

@Repository
public class ProductDaoImpl implements ProductDao{

	private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addProduct(Product add){
		Session session = this.sessionFactory.getCurrentSession();
		int productId = (int)session.save(add);
		int currentQuantity = 0;
		 for(ProductAttribute attribute : this.getProductById(productId).getAttribute()){
			 int quantity = this.getCurrentQuantity(attribute.getId());
			 currentQuantity += quantity;
		 };
		 this.saveStock(productId,currentQuantity);
		logger.info("Product added successfully, Product details: "+add);
	}
	
	@Override
	public void updateProduct(Product update){
		Session session=this.sessionFactory.getCurrentSession();
		int currentQuantity = 0;
		session.update(update);
		 for(ProductAttribute attribute : update.getAttribute()){
			 int quantity = this.getCurrentQuantity(attribute.getId());
			 currentQuantity += quantity;
		 };
		this.updateStock(update.getId(), currentQuantity);
		logger.info("Product updated successfully");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> listProducts(){
		Session session = this.sessionFactory.getCurrentSession();
		List<Product> productsList = session.createQuery("from Product").list();
		for(Product p : productsList){
			logger.info("Person List::"+p);
		}
		return productsList;
	}

	
	@Override
	public Product getProductById(int id){
		Session session = this.sessionFactory.getCurrentSession();	
		return (Product) session.createCriteria(Product.class)
				.add(Restrictions.eq("id", id))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.uniqueResult(); 
	}
	
	@Override
	public void removeProduct(int id){
		Session session = this.sessionFactory.getCurrentSession();
		Product p = (Product) session.load(Product.class, new Integer(id));
		p.setDeleted(true);
		if(null != p){
			session.update(p);
		}
		logger.info("Product deleted successfully, product details="+p);		
	}
	@Override
	public boolean removeProductAttribute(int id ){
		Session session =this.sessionFactory.getCurrentSession();
		//Query query=session.createQuery("DELETE ProductAttribute WHERE product_id=:id");
		Query query =  session.createQuery("SELECT c.id FROM ProductAttribute c WHERE c.product.id=:id");
		query.setParameter("id", id);
		List<Integer> attribute= query.list();
		attribute.forEach(a->{
			Query deleteQuery = session.createQuery("DELETE FROM ProductQuantity WHERE attribute_id=:id");
			deleteQuery.setParameter("id", a);
			deleteQuery.executeUpdate();
		});
		query = session.createQuery("DELETE FROM ProductAttribute WHERE product.id=:id");
		query.setParameter("id", id);
		int i=0;
		i=query.executeUpdate();
		if(i>0){
		return true;
		}else {
			return false;
		}
	}
	@Override
	public long getTotalQuantity(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		//Query query=session.createQuery("SELECT sum(productQuantity) FROM ProductAttribute WHERE product_id=:id");
		Query query=session.createQuery("SELECT c.currentStock FROM StockManagement c WHERE c.productId=:id");
		query.setParameter("id", id);
		int totalQuantity= (int) query.uniqueResult();
		return totalQuantity;
	}
	@Override
	public int getCurrentQuantity(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query=session.createQuery("SELECT c.quantity FROM ProductQuantity c WHERE c.attribute.id=:id");
		query.setParameter("id", id);
		int totalQuantity=(int)query.uniqueResult();
		return totalQuantity;
	}
	@Override
	public long getAvailableQuantity(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query=session.createQuery("FROM StockManagement WHERE productId=:id");
		query.setParameter("id", id);
		StockManagement inventory=(StockManagement)query.uniqueResult();
		int ordered = inventory.getOrderedStock() + inventory.getLockedStock();
		long totalQuantity = inventory.getCurrentStock() - ordered ;
		return totalQuantity;
	}
	@Override	
	public List<ProductCategory> listCategories(){
		Session session = this.sessionFactory.getCurrentSession();
		List<ProductCategory> productCategories = session.createQuery("from ProductCategory WHERE deleted=false").list();
		for(ProductCategory p : productCategories){
			logger.info("ProductCategory List::"+p);
		}
		return productCategories;
	}
	
	@Override
	public ProductCategory getCategoryById(int id){
		Session session = this.sessionFactory.getCurrentSession();		
		ProductCategory p = (ProductCategory) session.load(ProductCategory.class, new Integer(id));
		logger.info("Product loaded successfully, Product details="+p);
		return p; 
	}
	@Override
	public boolean updateProductStatus(int id,int isActive) {
		Session session = this.sessionFactory.getCurrentSession();		
		Query orderStatusQuery = session.createQuery("UPDATE Product set isActive=:isActive"+" WHERE id=:id");
		orderStatusQuery.setParameter("id",id);
		orderStatusQuery.setParameter("isActive",isActive);
		int i=0;
		 i= (int)orderStatusQuery.executeUpdate();
		if(i >0) {
		return true;
		}else {
			return false;
		}
	}
	
	@Override
	public long totalPageCount() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteriaCount = session.createCriteria(Product.class);
		criteriaCount.setProjection(Projections.rowCount());
		return  (Long) criteriaCount.uniqueResult();
	}
	
	@Override
	public List<Product> listPerPage(int start,int limit){
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.addOrder(Order.asc("name"));
		criteria.add(Restrictions.eq("deleted", false));
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		return criteria.list();
	}
	@Override
	public List<ProductImages> getImages(int product_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query productImagesListQuery = session.createQuery("from ProductImages p where p.product.id=:product_id");
		productImagesListQuery.setParameter("product_id", product_id);
		List<ProductImages> images = productImagesListQuery.list();
		return images;

	}
	@Override
	public boolean deleteImage(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query productImagesDeleteQuery = session.createQuery("delete from ProductImages p where p.id=:id");
		productImagesDeleteQuery.setParameter("id", id);
		int deleted=0;
		deleted = productImagesDeleteQuery.executeUpdate();
		
		if(deleted > 0){
			logger.info("Product Image deleted successfully");
			return true;
		}else {
			return false;
		}		
	}
	@Override
	public List<Product> searchFilter(String name){
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.like("name", name + "%"));
		return criteria.list();
	}
	@Override
	public boolean addCategory(ProductCategory add) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.persist(add);
			logger.info("Product added successfully, Product details: "+add);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public ProductAttribute getProductQuantityByProductId(int productId,float weight) {
		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query productNameQuery = session.createQuery("SELECT FROM ProductAttribute c WHERE c.product.id=:id"+" and c.weight=:weight");
			productNameQuery.setParameter("id", productId);
			productNameQuery.setParameter("weight", weight);
			return (ProductAttribute)productNameQuery.uniqueResult();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateProductQuantity(ProductAttribute productAttribute) {
		try{
			Session session = this.sessionFactory.getCurrentSession();
			session.update(productAttribute);
			logger.info("Product updated successfully, Product Details=" + productAttribute);
			return true;
		}catch(Exception e) {
		    e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean saveStock(int productId, int currentQuantity) {
		try {
			StockManagement inventory = new StockManagement();
			inventory.setProductId(productId);
			inventory.setCurrentStock(currentQuantity);
			inventory.setOrderedStock(0);
			inventory.setLockedStock(0);
			Session session = this.sessionFactory.getCurrentSession();
			session.persist(inventory);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public StockManagement getStockByProductId(int productId) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(StockManagement.class);
		criteria.add(Restrictions.eq("productId", productId));
		return (StockManagement)criteria.uniqueResult();
	}
	public boolean updateStock(int productId, int currentQuantity) {
		try {
			StockManagement inventory = this.getStockByProductId(productId);
			inventory.setCurrentStock(currentQuantity);
			Session session = this.sessionFactory.getCurrentSession();
			session.update(inventory);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteCategory(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		ProductCategory p = (ProductCategory) session.load(ProductCategory.class, new Integer(id));
		p.setDeleted(true);
		try{
			session.update(p);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCategory(ProductCategory category) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(category);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
