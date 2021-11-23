package com.app.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt {

	Checkout checkout = new Checkout();
	
	private double total;
	
	public Receipt(Checkout checkout,double total) {
		this.checkout = checkout;
		this.total = total;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n\n\n");
		sb.append("\n\t\tCharlene's Coffee Corner\n\n");
		sb.append("\n\tAddress: 123 Corner Street");
		sb.append("\n\tTel: +(00) 1234567 89");
		sb.append(String.format("\n\t%s\t\t\t%s", LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")),
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
		sb.append("\n\t-----------------------------------------");
		checkout.getProducts().forEach(prod -> {
			sb.append(String.format("\n\t%s\t\t\t%.2f CHF", prod.getName(), prod.getPrice()));
		});
		checkout.getExtras().forEach(ext -> {
			sb.append(String.format("\n\t%s\t\t\t%.2f CHF", ext.getName(), ext.getPrice()));
		});
		sb.append("\n\t-----------------------------------------");
		sb.append(String.format("\n\n\tVAT:\t\t\t\t%.2f CHF", (total * 0.18)));
		sb.append(String.format("\n\n\tTOTAL:\t\t\t\t%.2f CHF", total));
		sb.append("\n\n\t\t THANK YOU");
		sb.append("\n\n\n");
		
		return sb.toString(); 
	}
	
}
