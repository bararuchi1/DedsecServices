package com.dedsec.JPARepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dedsec.bean.Product;
import com.dedsec.bean.UserDetails;

public interface ProductJPARepository extends JpaRepository<Product, Long> {
	public List<Product> findAllOrderByProductCodeDesc();
}
