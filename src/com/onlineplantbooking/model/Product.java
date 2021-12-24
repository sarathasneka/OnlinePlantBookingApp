package com.onlineplantbooking.model;

import java.util.Objects;

public class Product {
	
	
	private String plantName;
	private String plantDescription;
	private int plantPrice;
	private String categoryName;
	public String getPlantName() {
		return plantName;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	public String getPlantDescription() {
		return plantDescription;
	}
	public void setPlantDescription(String plantDescription) {
		this.plantDescription = plantDescription;
	}
	public int getPlantPrice() {
		return plantPrice;
	}
	public void setPlantPrice(int plantPrice) {
		this.plantPrice = plantPrice;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Product() {
		super();
		
	}
	public Product(String plantName, String plantDescription, int plantPrice, String categoryName) {
		super();
		this.plantName = plantName;
		this.plantDescription = plantDescription;
		this.plantPrice = plantPrice;
		this.categoryName = categoryName;
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Product [plantName=" + plantName + ", plantDescription=" + plantDescription + ", plantPrice="
				+ plantPrice + ", categoryName=" + categoryName + "]";
	}
}