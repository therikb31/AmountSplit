 package com.globe.controller;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.globe.DAO.AppUserDAO;
import com.globe.DAO.TransactionHistoryDAO;
import com.globe.model.AppUser;
import com.globe.model.Transaction;
import com.globe.model.TransactionHistory;

@Component
public class TransactionController {
	@Autowired
	TransactionHistoryDAO transactionHistoryDAO;

	@Autowired
	AppUserDAO appUserDAO;

	@Autowired
	AppUserController appUserController;

	private double round(double val) {
		DecimalFormat df = new DecimalFormat("#.##");
		val = Double.valueOf(df.format(val));
		return val;
	}

	public void execute(Transaction transaction) {
		List<AppUser> splitTo = transaction.getSplitTo();
		List<Double> weights = transaction.getWeights();
		String owedTo = transaction.getSplitBy().getUserId();
		int userCount = transaction.getUserCount();
		double totalAmount = transaction.getTotalAmount();
		double sum = 0;
		double amountOwed = 0;
		for (int i = 0; i < userCount; i++) {

			String owedBy = splitTo.get(i).getUserId();

			Timestamp timeNow = new Timestamp(System.currentTimeMillis());
			switch (transaction.getSplitType()) {
			case "PERCENT":
				amountOwed = round((weights.get(i) * totalAmount) / 100);
				break;

			case "EQUAL":
				if (i != transaction.getUserCount() - 1) {
					amountOwed = round(totalAmount / userCount);
					sum += amountOwed;
				} else {
					amountOwed = round(totalAmount - sum);
				}
				break;
			case "EXACT":
				amountOwed = weights.get(i);
				break;
			}
			if (!owedTo.equals(owedBy)) {
				TransactionHistory transactionHistory = transactionHistoryDAO.getTransactionHistory(owedTo, owedBy);
				if (transactionHistory == null) {
					transactionHistory = transactionHistoryDAO.getTransactionHistory(owedBy, owedTo);
					Double existingAmountOwed = transactionHistory.getAmountOwed();
					transactionHistory.setAmountOwed(round(existingAmountOwed - amountOwed));
				} else {
					Double existingAmountOwed = transactionHistory.getAmountOwed();
					transactionHistory.setAmountOwed(round(existingAmountOwed + amountOwed));
				}
				transactionHistory.setTime(timeNow);
				transactionHistoryDAO.save(transactionHistory);
			}
		}
	}

	public void showLogic(Iterator<TransactionHistory> iterator) {
		boolean didIter = false;
		for (Iterator<TransactionHistory> iter = iterator; iter.hasNext();) {

			TransactionHistory th = iter.next();
			AppUser user1 = appUserController.getAppUserById(th.getOwedBy());
			AppUser user2 = appUserController.getAppUserById(th.getOwedTo());
			double amountOwed = th.getAmountOwed();

			if (amountOwed < 0) {
				System.out.println(user2.getName() + " owes " + user1.getName() + " : " + Math.abs(amountOwed));
				didIter = true;
			}
			if (amountOwed > 0) {
				System.out.println(user1.getName() + " owes " + user2.getName() + " : " + amountOwed);
				didIter = true;
			}
		}
		if (didIter == false)
			System.out.println("No balances");
	}

	public void show() {
		showLogic(transactionHistoryDAO.findAll().iterator());
	}

	public void show(String userId) {
		showLogic(transactionHistoryDAO.getTransactionHistoryByUser(userId).iterator());
	}

}