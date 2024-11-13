package com.folau.jsf.entity.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByUuid(String uuid);

    Set<Address> findByCustomerIdAndType(Long customerId, AddressType type);

    Set<Address> findByCustomerId(Long customerId);
}
