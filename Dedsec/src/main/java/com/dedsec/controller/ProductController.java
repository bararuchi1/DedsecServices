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

import com.dedsec.bean.Product;
import com.dedsec.config.LoggingDetails;
import com.dedsec.services.ProductServices;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {
	private static Logger LOGGER = Logger.getLogger(ProductController.class.getName());
	@Autowired
	ProductServices productServices;

	@PostMapping("/addProduct")
	public Product addProductDetails(@RequestBody Product product) {
		LOGGER.info("addProduct Product Details :" + product.toString());

		productServices.prductInsertionService(product);

		return product;
	}

	@GetMapping("/getAllProducts")
	public List<Product> getAllProductDetails() {
		List<Product> productList = null;

		productList = productServices.getAllProductDetails();

		return productList;
	}

}
