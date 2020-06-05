package com.dedsec.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
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
		//	LOGGER.info("-----" + productRepo.findByProductCode(product.getProductCode()));
			if (productRepo.findByProductCode(product.getProductCode()) != null) {
				Product tempProduct = productRepo.findByProductCode(product.getProductCode());
				product.setErrorCode("101");
				product.setErrorMessage(
						"Product Code " + product.getProductName() + " already present and Mapped with '"
								+ tempProduct.getProductName() + "'. Please Provide another product Code.");
				// Get the mapped product

			} else {
				productRepo.save(product);
				// Success
				product.setErrorCode("0");
				product.setErrorMessage("SUCCESS");
			}

		} catch (DataIntegrityViolationException e) {

		} catch (Exception e) {

			LOGGER.info("-----Exception " + e.getMessage());
			e.printStackTrace();
			product.setErrorCode("-1");
			product.setErrorMessage(e.getMessage());

		}

	}

	public List<Product> getAllProductDetails() {
		List<Product> product = null;
		Product prod=null;
		try {
			//product = productRepo.findAll();
			prod= productRepo.findByProductCode("1221");
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
		List<Product> prodList = productRepo.findAllByOrderByProductCodeDesc();
		for (Product prod : prodList) {
			if (prod.getProductCode() != null || prod.getProductCode() != "")
				productCodeList.put(prod.getProductCode(), prod.getProductCode() + "~~" + prod.getProductName());
		}
		return productCodeList;
	}

}
