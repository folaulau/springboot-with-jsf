package com.folau.jsf.entity.address;

import java.util.Optional;
import java.util.Set;

public interface AddressDAO {

    Optional<Address> getByUuid(String uuid);

    Address save(Address address);

    Optional<Address> findByUuid(String uuid);

    Optional<Long> getCustomerIdByUuid(String uuid);

    Set<Address> findByCustomerId(Long customerId);

    Set<Address> findByCustomerIdAndType(Long customerId, AddressType type);
}
