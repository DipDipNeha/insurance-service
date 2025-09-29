/**
 * 
 */
package com.pscs.insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pscs.insurance.entity.Partner;

/**
 * 
 */
@Repository
public interface PartnerRepo extends JpaRepository<Partner,Long> {

	Partner findByPartnerCodeAndPartnerName(String partnerCode, String partnerName);

}
