/**
 * @author Dipak
 */

package com.pscs.insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pscs.insurance.entity.InsuranceCompany;
@Repository
public interface InsuranceCompanyRepo extends JpaRepository<InsuranceCompany, Long> {

}
