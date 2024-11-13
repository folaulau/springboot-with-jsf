package com.folau.jsf.entity.address;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
@Slf4j
public class AddressDAOImp implements AddressDAO {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Address> getByUuid(String uuid) {
        return addressRepository.findByUuid(uuid);
    }

    @Override
    public Address save(Address address) {
        return addressRepository.saveAndFlush(address);
    }

    @Override
    public Optional<Address> findByUuid(String uuid) {
        return addressRepository.findByUuid(uuid);
    }

    @Override
    public Optional<Long> getCustomerIdByUuid(String uuid) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT customer_id ");
        sql.append("FROM addresses ");
        sql.append("WHERE uuid = ?");

        Long customerId = null;

        try {
            customerId = jdbcTemplate.queryForObject(sql.toString(), new Object[]{uuid}, Long.class);
        } catch (Exception e) {
            log.error("Error getting customer id by address uuid: {}", uuid, e);
        }

        return Optional.ofNullable(customerId);
    }

    @Override
    public Set<Address> findByCustomerId(Long customerId) {
        return addressRepository.findByCustomerId(customerId);
    }

    @Override
    public Set<Address> findByCustomerIdAndType(Long customerId, AddressType type) {
        return addressRepository.findByCustomerIdAndType(customerId, type);
    }
}
