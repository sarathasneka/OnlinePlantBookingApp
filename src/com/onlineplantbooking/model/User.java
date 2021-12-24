package com.onlineplantbooking.model;

import java.util.Objects;

public class User {

		private String name;
		private String emailId;
		private String password;
		private long mobileNumber;
		private String address;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		public long getMobileNumber() {
			return mobileNumber;
		}
		public void setMobileNumber(long mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public User(String name, String emailId, String password, long mobileNumber, String address) {
			super();
			this.name = name;
			this.emailId = emailId;
			this.password = password;
			this.mobileNumber = mobileNumber;
			this.address = address;
		}
		public User(String emailId, String password) {
			super();
			this.emailId = emailId;
			this.password = password;
		}
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "User [name=" + name + ", emailId=" + emailId + ", password=" + password + ", mobileNumber="
					+ mobileNumber + ", address=" + address + "]";
		}
		
}
