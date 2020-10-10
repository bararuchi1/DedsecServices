package com.dedsec.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dedsec.JPARepo.AssociateJPARepository;
import com.dedsec.bean.Associates;
import com.dedsec.bean.Product;

@Component
public class ValidationAssociate {
	private static List<String> listErrors = new ArrayList<String>();
	/*
	 * Erorr Messages
	 * 
	 * "NONUNIQUEADHAAR"
	 */

	public static List<String> ValidateAssociate(Associates associate, AssociateJPARepository associateJPARepository) {
		listErrors.clear();

		// Check Unique Associate

		Associates associates = associateJPARepository.findSelectedActiveProductAndAssociate(
				associate.getProduct().getProductCode(), associate.getAssociateAdhaarNo());

		if (associates != null) {
			listErrors.add("NONUNIQUEADHAAR");
		}

		return listErrors;
	}
}
