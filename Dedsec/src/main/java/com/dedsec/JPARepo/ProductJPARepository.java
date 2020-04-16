package com.dedsec.JPARepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dedsec.bean.Product;

public interface ProductJPARepository extends JpaRepository<Product	, Long> {

}
