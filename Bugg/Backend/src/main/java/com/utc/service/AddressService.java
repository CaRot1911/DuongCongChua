package com.utc.service;

import com.utc.entity.Address;
import com.utc.form.create.AddressInsertForm;
import com.utc.form.filter.AddressFilter;
import com.utc.form.update.AddressUpdateForm;
import com.utc.repository.IAddressRepository;
import com.utc.specification.AddressSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AddressService implements IAddressService{

    @Autowired
    private IAddressRepository addressRepository;


    @Override
    public List<Address> findAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Page<Address> findAllAddressByPage(Integer search, Pageable pageable, AddressFilter filter) {
        Specification<Address> where = AddressSpecification.buildWhere(search,filter);
        return addressRepository.findAll(where,pageable);
    }

    @Override
    public Address getAddressByCity(String city) {
        return addressRepository.findAddressByCity(city);
    }

    @Override
    public Address getAddressByCountry(String country) {
        return addressRepository.findAddressByCountry(country);
    }

    @Override
    public boolean getAddressByCityIsExits(String city) {
        return addressRepository.existsAddressByCity(city);
    }

    @Override
    public boolean getAddressByCountryIsExits(String country) {
        return addressRepository.existsAddressByCountry(country);
    }

    @Override
    public void createAddress(AddressInsertForm form) {
        Address address = new Address();
        address.setCountry(form.getCountry());
        address.setCity(form.getCity());

        addressRepository.save(address);
    }

    @Override
    public void updateAddress(int id,AddressUpdateForm form) {
        Address address = addressRepository.findById(id).get();
        address.setCity(form.getCity());
        address.setCountry(form.getCountry());

        addressRepository.save(address);
    }

    @Override
    public void deleteAddress(int id) {
        Address address = addressRepository.findById(id).get();

        addressRepository.delete(address);
    }

    @Override
    public void deleteAllAddress(List<Integer> ids) {
        addressRepository.deleteAllAddress(ids);
    }
}
