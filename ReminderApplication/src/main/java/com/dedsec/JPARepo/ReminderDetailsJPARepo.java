package com.dedsec.JPARepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dedsec.bean.ReminderDetails;

public interface ReminderDetailsJPARepo extends JpaRepository<ReminderDetails, Long> {

	@Query(value = "SELECT u FROM ReminderDetails u WHERE u.eventDate between :startDate and :endDate and mailSent='N' order by eventDate")
	List<ReminderDetails> findAllRemniderBetweenThePeriod(@Param("startDate") LocalDateTime startDate,
			@Param("endDate") LocalDateTime endDate);

	@Query(value = "update ReminderDetails r where r.messageSentStatus=:messageSentStatus and r.id=:id")
	void updateMessageSentStatus(@Param("messageSentStatus") String messageSentStatus, @Param("id") String id);
	/*
	 * @Query(value =
	 * "SELECT u FROM ReminderDetails u WHERE u.eventDate between :startDate and :endDate"
	 * ) List<ReminderDetails> findAllRemniderBetweenThePeriod(@Param("startDate")
	 * Date startDate,
	 * 
	 * @Param("endDate") Date endDate);
	 */

}
