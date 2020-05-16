package com.dedsec.JPARepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dedsec.bean.UserDetails;

public interface UserDetailRepository extends JpaRepository<UserDetails, Long> {
	UserDetails findByUserNameAndUserPassword(String userName, String userPassword);
	UserDetails findByUserNameAndUserPasswordAndUserRole(String userName, String userPassword, String userRole);

}
