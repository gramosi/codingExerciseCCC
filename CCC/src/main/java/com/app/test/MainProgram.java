package com.app.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainProgram {

	// Add Validation to prevent from gettin string instead integer on input

	static Scanner sc = new Scanner(System.in);
	static double total = 0.0;
	static int qty = 0;
	static List<Product> products = new ArrayList<Product>();
	static List<Extra> extras = new ArrayList<Extra>();
	static Checkout checkout = new Checkout();
	static List<Product> orderedProduct = new ArrayList<Product>();
	static List<Extra> orderedExtra = new ArrayList<Extra>();
	static Receipt receipt;
	
	static void initData() {

		// Add data
		products.add(new Product(1, "Coffee-S", 2.5, "beverage"));
		products.add(new Product(2, "Coffee-M", 3.0, "beverage"));
		products.add(new Product(3, "Coffee-L", 3.5, "beverage"));
		products.add(new Product(4, "Bacon Roll", 4.5, "meal"));
		products.add(new Product(5, "Orange Juice", 3.95, "beverage"));
		products.add(new Product(6, "Snickers", 0.8, "snack"));
		products.add(new Product(7, "Schoko-Bons", 2.5, "snack"));

		extras.add(new Extra(1, "Extra Milk", 0.3));
		extras.add(new Extra(2, "Foamed Milk", 0.5));
		extras.add(new Extra(3, "Special Roast Coffee", 0.9));

	}

	public static void main(String args[]) {

		initData();

		while (true) {

			mainMenu();

			int mainMenuPick = sc.nextInt();

			if (mainMenuPick == 1) {

				System.out.println("Please pick size: ");
				System.out.println("1-Small");
				System.out.println("2-Medium");
				System.out.println("3-Large");

				int coffeeSizePick = sc.nextInt();

				if (coffeeSizePick == 1) {
					total += 2.5;
					orderedProduct.add(products.get(0));
				} else if (coffeeSizePick == 2) {
					total += 3;
					orderedProduct.add(products.get(1));
				} else if (coffeeSizePick == 3) {
					total += 3.5;
					orderedProduct.add(products.get(2));
				}
				System.out.println("Do you want anything Extra Y/N ?");
				String ynExtra = sc.next();
				if (ynExtra.toLowerCase().contains("y")) {
					extraMenu();

					int extraMenuPick = sc.nextInt();

					if (extraMenuPick == 1) {

						total += 0.3;
						orderedExtra.add(extras.get(0));

					} else if (extraMenuPick == 2) {

						total += 0.5;
						orderedExtra.add(extras.get(1));
					} else if (extraMenuPick == 3) {

						total += 0.9;
						orderedExtra.add(extras.get(2));
					} else {

					}
				}
			} else if (mainMenuPick == 2) {

				total += 4.5;
				orderedProduct.add(products.get(3));

			} else if (mainMenuPick == 3) {
				total += 3.95;
				orderedProduct.add(products.get(4));

			} else if (mainMenuPick == 4) {

				snackMenu();
				int snackMenuPick = sc.nextInt();

				if (snackMenuPick == 1) {

					total += 0.8;
					orderedProduct.add(products.get(5));

				} else if (snackMenuPick == 2) {

					total += 2.5;
					orderedProduct.add(products.get(6));
				}

			} else if (mainMenuPick == 5) {

				long countBeverage = orderedProduct.stream().filter(p -> "beverage".equals(p.getType())).count();
				long countSnack = orderedProduct.stream().filter(p -> "snack".equals(p.getType())).count();
				long countExtra = orderedExtra.stream().count();
				if (countBeverage >= 5) {
					
					//get the number of iteration, in order to remove every 5th beverage
					// example 17 ordered beverage => 17 / 5 ~ 3 iterations(3 beverages to be removed) 
					int getNumberOfItemsToRemove = (int) (countBeverage / 5);

					for (int i = 0; i < getNumberOfItemsToRemove; i++) {
						Product lastBeverage = orderedProduct.stream().filter(p -> "beverage".equals(p.getType()))
								.skip(countBeverage - 1).findFirst().get();
						orderedProduct.remove(lastBeverage);
						total -= lastBeverage.getPrice();
					}
				} else if (countBeverage > 0 && countSnack > 0) {

					// check if there is an extra, if yes remove one, if no do nothing
					if (countExtra > 0) {

						Extra extraToBeRemoved = orderedExtra.stream().findAny().get();
						orderedExtra.remove(extraToBeRemoved);
						total -= extraToBeRemoved.getPrice();
					}

				}
				checkout.setProducts(orderedProduct);
				checkout.setExtras(orderedExtra);
				//printReceipt(checkout);
				receipt = new Receipt(checkout, total);
				System.out.println(receipt);
				System.exit(0);
			}
		}

	}

	private static void mainMenu() {

		System.out.println("Pick your order:");
		System.out.println("1-Coffee");
		System.out.println("2-Bacon Roll");
		System.out.println("3-Freshly Squeezed orange juice(0.25l)");
		System.out.println("4-Snacks");
		System.out.println("5-Checkout");

	}

	private static void extraMenu() {

		System.out.println("Extras:");
		System.out.println("1-Extra Milk");
		System.out.println("2-Foamed Milk");
		System.out.println("3-Special Roast Coffee");

	}

	private static void snackMenu() {

		System.out.println("Snacks:");
		System.out.println("1-Snickers");
		System.out.println("2-Schoko-Bons");

	}

}
