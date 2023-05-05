package com.globe.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TransactionHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionId;
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	private String owedBy;
	private String owedTo;
	private Double amountOwed;
	private boolean isSettled;
	private Timestamp time;
	public String getOwedBy() {
		return owedBy;
	}
	public void setOwedBy(String owedBy) {
		this.owedBy = owedBy;
	}
	public String getOwedTo() {
		return owedTo;
	}
	public void setOwedTo(String owedTo) {
		this.owedTo = owedTo;
	}
	public Double getAmountOwed() {
		return amountOwed;
	}
	public void setAmountOwed(Double amountOwed) {
		this.amountOwed = amountOwed;
	}
	public boolean isSettled() {
		return isSettled;
	}
	public void setSettled(boolean isSettled) {
		this.isSettled = isSettled;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "TransactionHistory [transactionId=" + transactionId + ", owedBy=" + owedBy + ", owedTo=" + owedTo
				+ ", amountOwed=" + amountOwed + ", isSettled=" + isSettled + ", time=" + time + "]";
	}
	
}
