package com.dedsec.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dedsec.DAO.AssociateHelper;
import com.dedsec.JPARepo.AssociateJPARepository;
import com.dedsec.JPARepo.ImageModelJPARepository;
import com.dedsec.JPARepo.ProductJPARepository;
import com.dedsec.bean.Associates;
import com.dedsec.bean.ImageModel;
import com.dedsec.bean.Product;
import com.dedsec.common.CommonServices;
import com.dedsec.common.ValidationAssociate;

@Service
public class AssociateServices {

	@Autowired
	AssociateHelper associateHelper;
	@Autowired
	AssociateJPARepository associateJPARepository;
	@Autowired
	ProductJPARepository productJPARepository;
	@Autowired
	ValidationAssociate validationAssociate;
	@Autowired
	ImageModelJPARepository imageModelJPARepository;
	private static Logger LOGGER = LoggerFactory.getLogger(ProductServices.class);

	public Associates addAssociate(Associates associates) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			// Set Foreign Key Value
			// associates.setProductCode(associates.getProduct().getProductCode());
			// Check Unique AdhaarNumber

			// Product tempProduct =
			// productJPARepository.findByProductCode(associates.getProduct().getProductCode());
			// tempProduct.getAssociateDetails().add(associates);
			// associates.setProduct((Product)
			// productJPARepository.getOne(associates.getProduct().getProductCode()));
			// productJPARepository.save(tempProduct);

			List<String> validationError = ValidationAssociate.ValidateAssociate(associates, associateJPARepository);
			if (validationError != null && !validationError.isEmpty()) {
				if (validationError.contains("NONUNIQUEADHAAR")) {
					associates.setErrorCode("101");// NONUNIQUE
					associates.setErrorMessage("Adhaar Number already mapped with another Associate.");

				}
			} else {
				associateJPARepository.save(associates);
				associates.setErrorCode("0");
				associates.setErrorMessage("SUCCESS");
				// Success
			}

			// tempProduct.setErrorCode("0");
			// tempProduct.setErrorMessage("SUCCESS");
		} catch (Exception e) {
			associates.setErrorCode("-1");
			associates.setErrorMessage("Internal Server Error");
			e.printStackTrace();
		} finally {

		}
		return associates;
	}

	public Map<String, String> uploadImage(MultipartFile file, String adhaarNumber) {
		Map<String, String> map = new HashMap<String, String>();
		try {

			associateJPARepository.updateProfilePicture(CommonServices.compressBytes(file.getBytes()), adhaarNumber);
			map.put("ErrorCode", "0");
			map.put("ErrMessage", "SUCCESS");
		} catch (Exception e) {
			map.put("ErrorCode", "-1");
			map.put("ErrMessage", "Internal Server Error");
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}

		return map;
	}

	@Autowired
	ImageModel imageModel;

	public Map<String, String> dummyInamgeSaveSerivice(MultipartFile file) {
		Map<String, String> returnMap = new HashMap<String, String>();

		try {
			imageModel.setImageFileName(file.getName());
			imageModel.setImageFile(CommonServices.compressBytes(file.getBytes()));
			imageModelJPARepository.save(imageModel);
			returnMap.put("ErrorCode", "0");
			returnMap.put("ErrMessage", "SUCCESS");
		} catch (Exception e) {
			returnMap.put("ErrorCode", "-1");
			returnMap.put("ErrMessage", "Internal Server Error");
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}
		return returnMap;
	}

}
