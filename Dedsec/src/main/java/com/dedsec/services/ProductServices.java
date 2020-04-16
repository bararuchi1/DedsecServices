package com.dedsec.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dedsec.DedsecApplication;
import com.dedsec.DAO.ProductHelper;
import com.dedsec.JPARepo.ProductJPARepository;
import com.dedsec.bean.Product;

@Service
public class ProductServices {
	private static Logger LOGGER = LoggerFactory.getLogger(ProductServices.class);
	@Autowired
	ProductHelper helperDao;
	@Autowired
	ProductJPARepository productRepo;

	public void prductInsertionService(Product product) {

		try {
			helperDao.productInsertion(product);
		} catch (DataIntegrityViolationException e) {
			product.setErrorCode("101");
			product.setErrorMessage("Product Code "+product.getProductCode()+" already present. Please Provide another product Code.");
		} catch (Exception e) {

			LOGGER.info("-----Exception " + e.getMessage());
			e.printStackTrace();
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
}
