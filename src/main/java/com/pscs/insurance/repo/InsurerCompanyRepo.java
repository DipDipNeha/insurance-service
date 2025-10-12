/**
 * @author Dipak
 */

package com.pscs.insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pscs.insurance.entity.Insurer;
@Repository
public interface InsurerCompanyRepo extends JpaRepository<Insurer, Long> {

	Insurer findByContactEmail(String string);

}
