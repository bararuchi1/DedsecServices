package com.dedsec.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dedsec.DAO.AssociateHelper;
import com.dedsec.JPARepo.AssociateJPARepository;
import com.dedsec.JPARepo.ProductJPARepository;
import com.dedsec.bean.Associates;
import com.dedsec.bean.Product;

@Service
public class AssociateServices {

	@Autowired
	AssociateHelper associateHelper;
	@Autowired
	AssociateJPARepository associateJPARepository;
	@Autowired
	ProductJPARepository productJPARepository;

	public Map<String, String> addAssociate(Associates associates) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			// Set Foreign Key Value
			// associates.setProductCode(associates.getProduct().getProductCode());
			Product tempProduct = productJPARepository.getOne(associates.getProduct().getProductCode());
			tempProduct.getAssociateDetails().add(associates);
			// associates.setProduct((Product)
			// productJPARepository.getOne(associates.getProduct().getProductCode()));
			productJPARepository.save(tempProduct);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;
	}

}
