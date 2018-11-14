package com.sit.int202.backend.Address;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAddressList() {
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(long id) {
        return addressRepository.findById(id);
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public long delete(long id) {
        addressRepository.deleteById(id);
        return id;
    }
}
