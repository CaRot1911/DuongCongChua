package com.utc.service;

import com.utc.entity.Address;
import com.utc.form.create.AddressInsertForm;
import com.utc.form.filter.AddressFilter;
import com.utc.form.update.AddressUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAddressService {

    public List<Address> findAllAddress();

    public Page<Address> findAllAddressByPage(Integer search, Pageable pageable, AddressFilter filter);

    public Address getAddressByCity(String city);

    public Address getAddressByCountry(String country);

    public boolean getAddressByCityIsExits(String city);

    public boolean getAddressByCountryIsExits(String country);

    public void createAddress(AddressInsertForm form);

    public void updateAddress(int id,AddressUpdateForm form);

    public void deleteAddress(int id);

    public void deleteAllAddress(List<Integer> ids);
}
