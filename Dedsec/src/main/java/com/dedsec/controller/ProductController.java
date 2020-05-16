package com.dedsec.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dedsec.bean.Associates;
import com.dedsec.bean.Product;
import com.dedsec.bean.UserDetails;
import com.dedsec.config.LoggingDetails;
import com.dedsec.services.AssociateServices;
import com.dedsec.services.ProductServices;

@RestController

public class ProductController {
	private static Logger LOGGER = Logger.getLogger(ProductController.class.getName());
	@Autowired
	ProductServices productServices;
	@Autowired
	AssociateServices associateServices;

	@PostMapping("/login")
	public UserDetails loggedInUser(@RequestBody UserDetails userDetails) {
		userDetails = productServices.getLoginDetails(userDetails);
		LOGGER.info("Logged in USER DETAILS :" + userDetails);
		return userDetails;
	}

	@PostMapping("/addProduct")
	public Map<String, String> addProductDetails(@RequestBody Product product) {
		LOGGER.info("addProduct Product Details :" + product.toString());
		Map<String, String> result = new HashMap<String, String>();
		/*
		 * for(Associates as: product.getAssociateDetails() ) { as.setProduct(product);
		 * }
		 */
		productServices.prductInsertionService(product);
		result.put("Success", "101");
		return result;
	}

	@GetMapping("/getAllProducts")
	public List<Product> getAllProductDetails() {
		List<Product> productList = null;

		productList = productServices.getAllProductDetails();

		return productList;
	}

	@GetMapping("/addAssociate")
	public Map<String, String> addAssociate() {
		Map<String, String> returnMessage = null;
		Associates associates = new Associates();
		returnMessage = associateServices.addAssociate(associates);

		return returnMessage;
	}

	@GetMapping("/prodcutCodeList")
	public Map<String, String> getProductCodeList() {
		Map<String, String> productCodeList = null;
		productCodeList = productServices.getProductCodeList();
		return productCodeList;
	}

}
