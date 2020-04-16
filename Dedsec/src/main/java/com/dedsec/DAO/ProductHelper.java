package com.dedsec.DAO;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.dedsec.bean.Product;

@Repository
public class ProductHelper {
	private static Logger LOGGER = Logger.getLogger(ProductHelper.class.getName());
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void productInsertion(Product product) {
		long id = 0;
		entityManager.persist(product);
		id = product.getProductId();
		LOGGER.info("ProductHelper ID GENERATED=" + id);

	}
}
