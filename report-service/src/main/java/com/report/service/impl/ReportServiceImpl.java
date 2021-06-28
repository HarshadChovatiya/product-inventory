package com.report.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.report.model.UserReport;
import com.report.repository.ReportRepository;
import com.report.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
	
	@Autowired
	private ReportRepository reportRepo;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserReport fetchUserReport(String username) throws Exception{
		logger.info("calling report repository from report service");
		return reportRepo.getPurchaseReport(username);
	}

}
