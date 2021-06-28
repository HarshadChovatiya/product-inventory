package com.report.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.report.model.UserReport;
import com.report.service.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {

	Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	@Autowired
	private ReportService reportService;
	
	/**
	 * 
	 * @param username a string contains the name of the logged in user
	 * @return will return the object of ResponseEntity containing data about
	 * the purchase report of the logged in user and the status code
	 * @throws Exception if any exception will be thrown by the subsequent method
	 * call will be thrown from this method
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/purchase-report")
	public ResponseEntity<UserReport> fetchUserReport(@RequestHeader("UserName") String username) throws Exception {
		logger.info("call made to report-repository from report-controller");
		if(username == null) {
			username = "Dummy";
		}
		return new ResponseEntity<UserReport>(
				reportService.fetchUserReport(username), HttpStatus.OK);
	}
}
