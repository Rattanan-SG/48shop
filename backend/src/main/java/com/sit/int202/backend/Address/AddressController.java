package com.sit.int202.backend.Address;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/addresses")
    public ResponseEntity<List<Address>> getAddressList() {
        return new ResponseEntity<List<Address>>(addressService.getAddressList(), HttpStatus.OK);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable long id) {
        return new ResponseEntity<Address>(addressService.getAddressById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/address")
    public ResponseEntity<Address> createAddress(@Valid @RequestBody Address address) {
        return new ResponseEntity<Address>(addressService.save(address), HttpStatus.CREATED);
    }

    @PutMapping("/address")
    public ResponseEntity<Address> updateAddress(@Valid @RequestBody Address address) {
        return new ResponseEntity<Address>(addressService.save(address), HttpStatus.OK);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<Long> deleteAddress(@PathVariable long id) {
        return new ResponseEntity<Long>(addressService.delete(id), HttpStatus.OK);
    }

}
