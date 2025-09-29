package com.pscs.insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pscs.insurance.entity.CoverConnectLogin;

@Repository
public interface CoverConnectLoginRepo extends JpaRepository<CoverConnectLogin, Long> {

	CoverConnectLogin findByUserNameAndPassword(String username, String password);

	CoverConnectLogin findByUserName(String string);

}
