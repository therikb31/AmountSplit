package com.globe.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.globe.DAO.AppUserDAO;
import com.globe.DAO.TransactionHistoryDAO;
import com.globe.model.AppUser;
import com.globe.model.TransactionHistory;

@Controller
@Component
public class AppUserController {
	
	@Autowired
	AppUserDAO repo;
	
	@Autowired
	TransactionHistoryDAO transactionHistoryDAO;
	public AppUser addUser(AppUser appUser){
		List<AppUser> list = getAppUsers();
		for(AppUser existingUser : list) {
			TransactionHistory transactionHistory = new TransactionHistory();
			transactionHistory.setAmountOwed(0.0);
			transactionHistory.setOwedBy(appUser.getUserId());
			transactionHistory.setOwedTo(existingUser.getUserId());
			transactionHistory.setSettled(false);
			transactionHistory.setTime(new Timestamp(System.currentTimeMillis()));
			transactionHistoryDAO.save(transactionHistory);	
		}
		repo.save(appUser);
		return appUser;
	}

	public AppUser getAppUserById(String userId) {
		return repo.findById(userId).orElse(null);
	}
	
	public List<AppUser> getAppUsers(){
		return repo.findAll();
	}

}
