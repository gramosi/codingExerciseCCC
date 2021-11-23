package com.app.test;

import java.util.List;

public class Checkout {

	private List<Product> products;
	private List<Extra> extras;
	
	public Checkout(List<Product> products, List<Extra> extras) {
		super();
		this.products = products;
		this.extras = extras;
	}
	public Checkout() {
		
	}
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public List<Extra> getExtras() {
		return extras;
	}
	public void setExtras(List<Extra> extras) {
		this.extras = extras;
	}
	@Override
	public String toString() {
		return "Checkout [products=" + products + ", extras=" + extras + "]";
	}
	public double getTotal() {
		
		if(products == null && extras == null)
			return 0.0;
		
		if(products.isEmpty() && extras.isEmpty())
			return 0.0;
		
		return products.stream().mapToDouble(prod->prod.getPrice()).sum()
				+ extras.stream().mapToDouble(ext->ext.getPrice()).sum();
	}

	
	
}
