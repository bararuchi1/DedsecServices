package com.dedsec.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dedsec.bean.Associates;
import com.dedsec.bean.ImageModel;
import com.dedsec.bean.Product;
import com.dedsec.bean.UserDetails;
import com.dedsec.config.LoggingDetails;
import com.dedsec.services.AssociateServices;
import com.dedsec.services.ProductServices;

@RestController
@MultipartConfig(maxFileSize = 1024*1024*1024, maxRequestSize = 1024*1024*1024)
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
	public Product addProductDetails(@RequestBody Product product) {
		LOGGER.info("addProduct Product Details :" + product.toString());

		Map<String, String> result = new HashMap<String, String>();

		productServices.prductInsertionService(product);
		// result.put("product",product);
		return product;
	}

	@GetMapping("/getAllProducts")
	public List<Product> getAllProductDetails() {
		List<Product> productList = null;

		productList = productServices.getAllProductDetails();

		return productList;
	}

	@GetMapping("/getAllAssociate")
	public List<Associates> getAllAssocaiteDetails() {
		List<Associates> associateList = null;

//associateList = productServices.getAllAssociateList();

		return associateList;
	}

	@PostMapping("/addAssociate")
	public Associates addAssociate(@RequestBody Associates associate) {
		// associate.setProduct(product);
		LOGGER.info(associate.toString());

		// Associates associates = new Associates();
		Associates returnedAssociate = null;

		returnedAssociate = associateServices.addAssociate(associate);

		return returnedAssociate;
	}

	@GetMapping("/prodcutCodeList")
	public Map<String, String> getProductCodeList() {
		Map<String, String> productCodeList = null;
		productCodeList = productServices.getProductCodeList();
		return productCodeList;
	}

	/*
	 * @PostMapping("/uploadProfielImage") public Map<String, String>
	 * uploadImage(@RequestBody Associates asssociate){ Map<String, String> map=new
	 * HashMap<String, String>(); map.put("100", "Success"); return map; }
	 */
	@PostMapping("/uploadProfielImage")
	public Map<String, String> uploadImage(@RequestParam("profileImage") MultipartFile file,
			@RequestParam("associateDetails") String associateAdhaarNumber) {

		/*
		 * System.out.println("Original Image Byte Size - " + file.getBytes().length);
		 * ImageModel img = new ImageModel(file.getOriginalFilename(),
		 * file.getContentType(), compressBytes(file.getBytes()));
		 * imageRepository.save(img); return ResponseEntity.status(HttpStatus.OK);
		 */
		Map<String, String> responseMap = null;

		responseMap = associateServices.uploadImage(file, associateAdhaarNumber);

		return responseMap;
	}

	@PostMapping("/uploadImageDummy")
	
	public Map<String, String> uploadImageDummy(@RequestParam("imageFile") MultipartFile file) {

		/*
		 * System.out.println("Original Image Byte Size - " + file.getBytes().length);
		 * ImageModel img = new ImageModel(file.getOriginalFilename(),
		 * file.getContentType(), compressBytes(file.getBytes()));
		 * imageRepository.save(img); return ResponseEntity.status(HttpStatus.OK);
		 */
		Map<String, String> returnMap = null;
		// dummyInamgeSaveSerivice

		LOGGER.info(file.getName());
		returnMap = associateServices.dummyInamgeSaveSerivice(file);
		return returnMap;
	}

}
