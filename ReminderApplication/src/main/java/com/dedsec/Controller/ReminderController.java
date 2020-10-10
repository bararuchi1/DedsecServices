package com.dedsec.Controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dedsec.Dao.ReminderDao;
import com.dedsec.JPARepo.ReminderDetailsJPARepo;
import com.dedsec.bean.ReminderDetails;
import com.dedsec.config.MailConfig;

@RestController
@RequestMapping("ReminderApplication")
public class ReminderController {
	private static Logger LOGGER = LoggerFactory.getLogger(ReminderController.class);
	@Autowired
	JavaMailSenderImpl javaMail;
	@Autowired
	MailConfig emailConfig;

	@Autowired
	ReminderDao dao;

	@Autowired
	ReminderDetailsJPARepo reminderJPA;

	/*
	 * @Autowired QuartzSchduler schduler;
	 */

	@PostMapping("sendMail")
	public ReminderDetails sendMain(@RequestBody ReminderDetails messageDetails) {

		LOGGER.info("Message Details" + messageDetails);

		try {

			/*
			 * Calendar date = Calendar.getInstance(); long miliSecond =
			 * date.getTimeInMillis(); Date currentDate = new Date(); Date afterTenMin = new
			 * Date(miliSecond + (10 * 60 * 1000));
			 * 
			 * LocalDateTime startDate = getLocalDateTime(currentDate); LocalDateTime
			 * endDate = getLocalDateTime(afterTenMin);
			 * 
			 * LOGGER.info("Start Date :" + startDate + " End Date :" + endDate);
			 * 
			 * List<ReminderDetails> list =
			 * reminderJPA.findAllRemniderBetweenThePeriod(startDate, endDate);
			 * LOGGER.info("List "+list);
			 */
			// reminderList.addAll();

			dao.saveReminderDetails(messageDetails);

		} catch (Exception e) {
			messageDetails.setErrorCode(-1);
			messageDetails.setErrorMessage("Some Error Occured.");
			e.printStackTrace();
		}
		return messageDetails;
	}

	public LocalDateTime getLocalDateTime(Date dateUtil) {
		return dateUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	@RequestMapping("timeCheck")
	public void newCheck(@RequestBody LocalTime time) {

	}

}
