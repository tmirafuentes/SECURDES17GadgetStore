package edu.dlsu.securdeproject.classes;

import javax.persistence.*;

@Entity
public class Product {
	private Long productId;
	private String productName;
	private double productPrice;
	private int productQuantity;
	private String productDescription;
	private Brand productBrand;
	private Type productType;

	public Product() {}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "brand_id")
	public Brand getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(Brand productBrand) {
		this.productBrand = productBrand;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "type_id")
	public Type getProductType() {
		return productType;
	}

	public void setProductType(Type productType) {
		this.productType = productType;
	}
}