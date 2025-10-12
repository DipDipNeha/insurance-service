/**
 * @author Dipak
 */

package com.pscs.insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pscs.insurance.entity.Customer;
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

	Customer findByEmail(String string);

}
