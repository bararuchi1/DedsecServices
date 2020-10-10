package com.dedsec.Dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.dedsec.Controller.ReminderController;
import com.dedsec.JPARepo.ReminderDetailsJPARepo;
import com.dedsec.bean.ReminderDetails;

@Repository
public class ReminderDao {
	private static Logger LOGGER = LoggerFactory.getLogger(ReminderDao.class);
	@Autowired
	ReminderDetailsJPARepo jpaDao;

	public ReminderDetails saveReminderDetails(ReminderDetails reminder) {
		try {
			LOGGER.info("Saving Details " + reminder);
			jpaDao.save(reminder);
			reminder.setErrorCode(1);
			reminder.setErrorMessage(
					"Data has been saved to the server.You're wish will be sent . 1 -2 min delay may happen.");
		} catch (Exception e) {
			reminder.setErrorCode(0);
			reminder.setErrorMessage(e.getMessage());

			e.printStackTrace();
		}
		return reminder;
	}

}
