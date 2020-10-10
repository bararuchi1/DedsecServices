package com.dedsec.JPARepo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dedsec.bean.Product;
import com.dedsec.bean.UserDetails;

public interface ProductJPARepository extends JpaRepository<Product, String> {
	public List<Product> findAllByOrderByProductCodeDesc();
	/*
	 * @Query("SELECT t.*  FROM  dedsec_product_details t where t.txt_product_code = :id"
	 * ) public Product findByProductCode(@Param("id") String productCode);
	 */

	public Product findByProductCode(String productCode);

	@Query("SELECT u FROM Product u WHERE u.productCode = :id ")
	Product findSelectedActiveProduct(@Param("id") String id);
	

}
