package com.chatbot.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="image")
public class ProductImages {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Lob
	@Column(name="image" ,columnDefinition = "longblob")
	private byte[] image;
	
	
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	@Transient
	String binaryImage;
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getBinaryImage() {
		return binaryImage;
	}

	public void setBinaryImage(String binaryImage) {
		this.binaryImage = binaryImage;
	}
	
}
