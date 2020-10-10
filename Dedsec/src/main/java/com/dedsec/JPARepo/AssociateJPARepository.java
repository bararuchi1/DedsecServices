package com.dedsec.JPARepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dedsec.bean.Associates;
import com.dedsec.bean.Product;

public interface AssociateJPARepository extends JpaRepository<Associates, Long> {
	@Query("SELECT v FROM Product u,Associates v WHERE u.productCode = :id and v.associateAdhaarNo=:associateAdhaarNo")
	Associates findSelectedActiveProductAndAssociate(@Param("id") String id,
			@Param("associateAdhaarNo") String associateAdhaarNo);

	@Transactional
	@Modifying
	@Query("update Associates u set u.profileDetails = :profileDetails where u.associateAdhaarNo =:adhaarNo")
	void updateProfilePicture(@Param("profileDetails") byte[] profileDetails, @Param("adhaarNo") String adhaarNo);

}
