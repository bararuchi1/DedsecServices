package com.dedsec.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dedsec.DAO.AssociateHelper;
import com.dedsec.JPARepo.AssociateJPARepository;
import com.dedsec.JPARepo.ProductJPARepository;
import com.dedsec.bean.Associates;

@Service
public class AssociateServices {

	@Autowired
	AssociateHelper associateHelper;
	@Autowired
	AssociateJPARepository associateJPARepository;

	public Map<String, String> addAssociate(Associates associates) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			associateJPARepository.save(associates);
		} catch (Exception e) {

		} finally {

		}
		return null;
	}


}
