package com.report.repository;

import com.report.model.UserReport;

public interface ReportRepository {

	/**
	 * 
	 * @param username a string containing the name of the user
	 * @return an object of the class UserReport
	 * @throws Exception
	 */
	UserReport getPurchaseReport(String username) throws Exception;
	
}
