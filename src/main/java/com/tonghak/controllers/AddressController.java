package com.tonghak.controllers;

import org.springframework.http.HttpStatus;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tonghak.dtos.req.CreateAddressDto;
import com.tonghak.dtos.req.UpdateAddressDto;
import com.tonghak.models.Address;
import com.tonghak.repo.AddressRepository;
import com.tonghak.utils.CreateSlugUrl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;
    // @Autowired 
    private CreateSlugUrl createSlugUrl;

    @PostMapping("/address")
    public Address create(@Valid @RequestBody CreateAddressDto createAddressDto) {

        Address address = new Address();
        address.setName(createAddressDto.getName());
        address.setSlug(createSlugUrl.urlSlug(createAddressDto.getName()));
        address.setNote(createAddressDto.getNote());
        address.setTag(createAddressDto.getTag());
        address.setStatus(createAddressDto.getStatus());
        address.setLat(createAddressDto.getLat());
        address.setLng(createAddressDto.getLng());

        return addressRepository.save(address);

    }

    @GetMapping("/address/{id}")
    public Optional<Address> findOne(@PathVariable Long id) {

        if(!addressRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
        }
        return addressRepository.findById(id);
    }

    @PutMapping("/address/{id}")
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateAddressDto updateAddressDto) {

        Address address = addressRepository.findById(id).orElse(null);
        address.setName(updateAddressDto.getName());
        address.setSlug(updateAddressDto.getSlug());
        address.setNote(updateAddressDto.getNote());
        address.setTag(updateAddressDto.getTag());
        address.setStatus(updateAddressDto.getStatus());
        address.setLat(updateAddressDto.getLat());
        address.setLng(updateAddressDto.getLng());
        addressRepository.save(address);
    
    }

    @DeleteMapping("/address/{id}")
    public void delete(@PathVariable Long id) {

        if(!addressRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        }

        addressRepository.deleteById(id);
    }
}
