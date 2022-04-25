package com.onuryalcin.repo;

import com.onuryalcin.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressReposityory extends JpaRepository<Address, Long> {
}
