package com.globe.model;

import java.util.List;

public class Transaction {
	
	private AppUser splitBy;
	private double totalAmount;
	private int userCount;
	private List<AppUser> splitTo;
	private String splitType;
	private List<Double> weights;
	public AppUser getSplitBy() {
		return splitBy;
	}
	public void setSplitBy(AppUser splitBy) {
		this.splitBy = splitBy;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	public List<AppUser> getSplitTo() {
		return splitTo;
	}
	public void setSplitTo(List<AppUser> splitTo) {
		this.splitTo = splitTo;
	}
	public String getSplitType() {
		return splitType;
	}
	public void setSplitType(String splitType) {
		this.splitType = splitType;
	}
	public List<Double> getWeights() {
		return weights;
	}
	public void setWeights(List<Double> weights) {
		this.weights = weights;
	}
	@Override
	public String toString() {
		return "Transaction [splitBy=" + splitBy + ", totalAmount=" + totalAmount + ", userCount=" + userCount
				+ ", splitTo=" + splitTo + ", splitType=" + splitType + ", weights=" + weights + "]";
	}

}