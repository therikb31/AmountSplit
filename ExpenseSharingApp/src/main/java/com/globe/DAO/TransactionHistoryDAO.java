package com.globe.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.globe.model.TransactionHistory;

public interface TransactionHistoryDAO extends JpaRepository<TransactionHistory, Integer>{
	@Query(value="SELECT * FROM Transaction_History WHERE owed_to = ?1 AND owed_by = ?2",nativeQuery = true)
	TransactionHistory getTransactionHistory(String owedTo,String owedBy);
	
	@Query(value="SELECT * FROM Transaction_History WHERE owed_to = ?1 OR owed_by = ?1",nativeQuery = true)
	List<TransactionHistory> getTransactionHistoryByUser(String owedTo);

}
