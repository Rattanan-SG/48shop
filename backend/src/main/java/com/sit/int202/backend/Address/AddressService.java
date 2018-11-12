package com.sit.int202.backend.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class AddressService {
    @Autowired
    private AddressRepository addressRepository;
}