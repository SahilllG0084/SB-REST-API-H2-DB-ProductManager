package com.cjc.app.entity;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Products {
     
	private List<Product> productlist;

	public List<Product> getProductlist() {
		return productlist;
	}

	public void setProductlist(List<Product> productlist) {
		this.productlist = productlist;
	}
}
