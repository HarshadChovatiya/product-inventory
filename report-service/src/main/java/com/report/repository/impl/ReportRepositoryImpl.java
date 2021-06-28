package com.report.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.report.model.UserReport;
import com.report.repository.ReportRepository;

@Repository
public class ReportRepositoryImpl implements ReportRepository {

	Logger logger = LoggerFactory.getLogger(ReportRepositoryImpl.class);
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserReport getPurchaseReport(String username) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		try {
			UserReport userReport = mongoTemplate.findOne(query, UserReport.class);
			if(userReport == null) {
				UserReport emptyReport = new UserReport();
				emptyReport.setUsername(username);
				logger.info("empty user report fetched");
				return emptyReport;
			}
			logger.info("user report fetched");
			return userReport;
		} catch(Exception e) {
			logger.error("error while fetching user report"
					+ "from report repository"
					+ e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

}
