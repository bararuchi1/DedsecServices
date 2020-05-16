package com.dedsec.JPARepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dedsec.bean.Associates;

public interface AssociateJPARepository extends JpaRepository<Associates, Long> {

}
