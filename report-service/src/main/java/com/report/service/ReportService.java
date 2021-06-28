package com.report.service;

import com.report.model.UserReport;

public interface ReportService {

	/**
	 * 
	 * @param username a string containing the name of the user
	 * @return an object of the class UserReport
	 * @throws Exception
	 */
	UserReport fetchUserReport(String username) throws Exception;
	
}
