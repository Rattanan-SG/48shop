package com.sit.int202.backend.Address;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
interface AddressRepository extends JpaRepository<Address, Long>{

}