package com.test.junit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.app.test.Checkout;
import com.app.test.Extra;
import com.app.test.Product;

public class CheckoutTest {

	@Test
	public void totalAmountOfCheckout() {

		Checkout checkout = new Checkout(
				Arrays.asList(new Product(1,"Product 1",5.55,"beverage")),
				Arrays.asList(new Extra(1,"Extra 1",2.25))
				);
		
		assertEquals(7.8, checkout.getTotal(),0.0);
		
	}
	
	@Test
	public void emptyCheckoutNotInitializedLists() {
		
		Checkout checkout = new Checkout();
		assertEquals(0, checkout.getTotal(),0.0);
		
	}
	
	@Test
	public void emptyCheckoutInitilized() {
		Checkout checkout = new Checkout(new ArrayList<Product>(), new ArrayList<Extra>());
		
		assertEquals(0, checkout.getTotal(), 0.0);
		
	}
	
}
