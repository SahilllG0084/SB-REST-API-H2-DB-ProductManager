package com.cjc.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.DynamicUpdate;

@XmlRootElement
@Entity
@DynamicUpdate
@Table(name = "PRODUCT_DTLS")
public class Product {
      
	 @Id
	 @Column(name = "PRODUCT_ID")
	 private Integer id;
	 
	 @Column(name = "PRODUCT_NAME")
	 private String name;
	 
	 @Column(name = "PRODUCT_CATEGORY")
	 private String category;
	 
	 @Column(name = "PRODUCT_BRAND")
	 private String brandname;
	 
	 @Column(name = "PRODUCT_STOCK_QUANTITY")
	 private Integer stockquantity;
	 
	 @Column(name = "PRODUCT_PRICE")
	 private Double price;
	 
	 public Product() {
		// TODO Auto-generated constructor stub
	 }

	public Product(Integer id, String name, String category, String brandname, Integer stockquantity, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.brandname = brandname;
		this.stockquantity = stockquantity;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public Integer getStockquantity() {
		return stockquantity;
	}

	public void setStockquantity(Integer stockquantity) {
		this.stockquantity = stockquantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", brandname=" + brandname
				+ ", stockquantity=" + stockquantity + ", price=" + price + "]";
	}
}
