package com.chatbot.model;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@JsonIgnore
	@Column(name= "category_id",columnDefinition ="int default 0")
	private int productId;

	@Column(name="name")
	private String name;
	
	@JsonIgnore
	@CreationTimestamp
	private Date createdAt;
	
	@JsonIgnore
	@UpdateTimestamp
	private Date updateAt;
	
	@JsonIgnore
	@Column(name="is_active")
	private int isActive;
	
	@Column(length=9500)
	String description;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean deleted;
	
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ProductAttribute> attribute;
	
	@JsonIgnore
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<ProductImages> image;
	
	@Column(name="created_by")
	private int created_by;
	
	@JsonIgnore
	@Transient
	private long totalQuantity;
	
	
	@Transient
	private String categoryName;
	
	@JsonIgnore
	@Transient
	private String imageBinary;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	
	public List<ProductAttribute> getAttribute() {
		return attribute;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public void setAttribute(List<ProductAttribute> attribute) {
		this.attribute = attribute;
	}

	public long getTotalQuantity() {
		return totalQuantity;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public void setTotalQuantity(long totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getImageBinary() {
		return imageBinary;
	}

	public void setImageBinary(String imageBinary) {
		this.imageBinary = imageBinary;
	}

	public List<ProductImages> getImage() {
		return image;
	}

	public void setImage(List<ProductImages> image) {
		this.image = image;
	}	
}
