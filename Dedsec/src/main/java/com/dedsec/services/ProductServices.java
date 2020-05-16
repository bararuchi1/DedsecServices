package com.dedsec.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dedsec.DedsecApplication;
import com.dedsec.DAO.ProductHelper;
import com.dedsec.JPARepo.ProductJPARepository;
import com.dedsec.JPARepo.UserDetailRepository;
import com.dedsec.bean.Product;
import com.dedsec.bean.UserDetails;

@Service
public class ProductServices {
	private static Logger LOGGER = LoggerFactory.getLogger(ProductServices.class);
	@Autowired
	ProductHelper helperDao;
	@Autowired
	ProductJPARepository productRepo;
	@Autowired
	UserDetailRepository userDetailRepo;

	public void prductInsertionService(Product product) {

		try {
			// helperDao.productInsertion(product);
			productRepo.save(product);
		} catch (DataIntegrityViolationException e) {
			product.setErrorCode("101");
			product.setErrorMessage("Product Code " + product.getProductCode()
					+ " already present. Please Provide another product Code.");
		} catch (Exception e) {

			LOGGER.info("-----Exception " + e.getMessage());
			// e.printStackTrace();
			product.setErrorCode("0");
			product.setErrorMessage(e.getMessage());

		}
	}

	public List<Product> getAllProductDetails() {
		List<Product> product = null;
		try {
			product = productRepo.findAll();
		} catch (Exception e) {
			LOGGER.info("-----Exception " + e.getMessage());
			e.printStackTrace();
		}

		return product;
	}

	public UserDetails getLoginDetails(UserDetails userDetails) {
		String status = "INITIATE";
		UserDetails userDetail = null;
		try {
			userDetail = userDetailRepo.findByUserNameAndUserPasswordAndUserRole(userDetails.getUserName(),
					userDetails.getUserPassword(), userDetails.getUserRole());
			LOGGER.info("USER DETAIL LOGGED IN " + userDetails);
			if (userDetail != null) {
				userDetails = userDetail;
				status = "SUCCESS";
			} else {
				status = "NODATA";
			}
		} catch (Exception e) {
			status = "EXCEPTION";

			return userDetails;
		}
		userDetails.setLoginStatus(status);
		return userDetails;
	}

	public Map<String, String> getProductCodeList() {
		Map<String, String> productCodeList = new HashMap<String, String>();
		List<Product> prodList = productRepo.findAllOrderByProductCodeDesc();
		for (Product prod : prodList) {
			productCodeList.put(prod.getProductCode(), prod.getProductCode() + "~~" + prod.getProductName());
		}
		return productCodeList;
	}

}
