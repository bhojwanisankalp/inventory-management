package com.chatbot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="stock_management")
public class StockManagement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	
	@Column(name="current_stock")
	int currentStock;
	
	@Column(name="ordered")
	int orderedStock;
	
	@Column(name= "locked")
	int lockedStock;
	
	@Column(name= "product_id")
	int productId;
	
	@CreationTimestamp
	private Date createdAt;
	
	
	@UpdateTimestamp
	private Date updateAt;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getCurrentStock() {
		return currentStock;
	}


	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
	}


	public int getOrderedStock() {
		return orderedStock;
	}


	public void setOrderedStock(int orderedStock) {
		this.orderedStock = orderedStock;
	}


	public int getLockedStock() {
		return lockedStock;
	}


	public void setLockedStock(int lockedStock) {
		this.lockedStock = lockedStock;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdateAt() {
		return updateAt;
	}


	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}
}
