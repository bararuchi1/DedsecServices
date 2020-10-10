package com.dedsec.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dedsec.JPARepo.ReminderDetailsJPARepo;
import com.dedsec.bean.ReminderDetails;
import com.dedsec.config.MailConfig;

@Service
public class ReminderServices {
	private static Logger LOGGER = LoggerFactory.getLogger(ReminderServices.class);

	@Autowired
	JavaMailSenderImpl javaMail;
	@Autowired
	MailConfig emailConfig;
	@Autowired
	ReminderDetailsJPARepo reminderJPA;

	static int i = 0;// For First Try
	static int j = 0;// For Retry List

	private static List<ReminderDetails> reminderList = new ArrayList<ReminderDetails>();
	private static List<ReminderDetails> retryMailList = new ArrayList<ReminderDetails>();

	@Scheduled(fixedDelay = 5 * 60 * 1000L)
	public void fetchMailDetails() {
		Calendar date = Calendar.getInstance();
		long miliSecond = date.getTimeInMillis();
		Date currentDate = new Date();
		Date afterTenMin = new Date(miliSecond + (10 * 60 * 1000));

		LocalDateTime startDate = getLocalDateTime(currentDate);
		LocalDateTime endDate = getLocalDateTime(afterTenMin);

		LOGGER.info("Start Date :" + currentDate + " End Date :" + afterTenMin);

		reminderList.addAll(reminderJPA.findAllRemniderBetweenThePeriod(startDate, endDate));

	}

	@Scheduled(fixedDelay = 10 * 1000L)
	public void sendMailFirstTry() {
		LOGGER.info("Current Time" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute());
		LOGGER.info("Reminder List :" + reminderList);
		for (ReminderDetails reminderDetail : reminderList) {
			// LOGGER.info("Current Time" + LocalDateTime.now().getHour() + ":" +
			// LocalDateTime.now().getMinute());

			if ("N".equalsIgnoreCase(reminderDetail.getMailSent())) {
				reminderDetail.setMailSent("I"); // Initiate
				if (ReminderServices.compareLocalDateDate(reminderDetail.getEventDate())) {
					try {
						LOGGER.info("Event Id" + reminderDetail.getEventDate() + "Event Date :"
								+ reminderDetail.getEventDate());
						sendMail(reminderDetail);
						LOGGER.info("Mail Sent " + (++i) + "\n Mail Id :" + reminderDetail.getId());
						reminderDetail.setMailSent("Y"); // Success
					} catch (Exception e) {
						LOGGER.info("Error While Sendig Mail" + e.getMessage());
						reminderDetail.setMailSent("F"); // Failed
						retryMailList.add(reminderDetail);
					}

				}

				// Update Table Status
				reminderJPA.updateMessageSentStatus(reminderDetail.getMailSent(), reminderDetail.getEmailId());
				boolean objectRemovalStatus = reminderList.remove(reminderDetail);
				LOGGER.info("Object Removal Status First Try List :" + objectRemovalStatus);

			}
		}
	}

	@Scheduled(fixedDelay = 60 * 1000L)
	public void sendMailRetry() {
		LOGGER.info("Retry Mail  List :" + retryMailList);
		try {
			LOGGER.info("Current Time" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute());
			LOGGER.info("Reminder List :" + retryMailList);
			for (ReminderDetails retryDetail : retryMailList) {
				retryDetail.setMailSent("RI"); // Re Initiate
				try {
					sendMail(retryDetail);
					LOGGER.info("Mail Sent Again " + (++j) + "\n Mail Id :" + retryDetail.getId());
					retryDetail.setMailSent("Y"); // Success
				} catch (Exception e) {
					LOGGER.info("Error While Sendig Mail" + e.getMessage());
					retryDetail.setMailSent("FF"); // Failed FOREVER

				}
				reminderJPA.updateMessageSentStatus(retryDetail.getMailSent(), retryDetail.getEmailId());
				boolean objectRemovalStatus = reminderList.remove(retryDetail);
				LOGGER.info("Object Removal Status First Try List :" + objectRemovalStatus);

			}

		} catch (Exception e) {

		} finally {

		}
	}

	public static boolean compareLocalDateDate(LocalDateTime localDate) {
		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println("" + currentTime.getHour() + " \n" + localDate.getHour() + " \n" + currentTime.getMinute()
				+ " \n" + localDate.getMinute());
		if (currentTime.getMinute() == localDate.getMinute()) {
			return true;
		}
		return false;
	}

	public LocalDateTime getLocalDateTime(Date dateUtil) {
		return dateUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public List<ReminderDetails> getReminderList() {

		return null;
	}

	public void sendMail(ReminderDetails reminder) {

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(reminder.getEmailId());
		mailMessage.setSubject(reminder.getOccassion());
		mailMessage.setTo(reminder.getEmailId());
		mailMessage.setText(reminder.getWishMessage());
		// mailMessage.setSentDate(new Date(2020, 9, 16, 22, 12, 00));

		javaMail.send(mailMessage);
	}

}
