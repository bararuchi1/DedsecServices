package com.dedsec.JPARepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dedsec.bean.ImageModel;

public interface ImageModelJPARepository extends JpaRepository<ImageModel, Long> {

}
