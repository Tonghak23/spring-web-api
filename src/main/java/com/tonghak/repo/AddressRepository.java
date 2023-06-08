package com.tonghak.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tonghak.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

    
}
