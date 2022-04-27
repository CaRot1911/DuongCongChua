package com.utc.service;

import com.utc.entity.Address;
import com.utc.entity.Hotel;
import com.utc.form.create.HotelCreateForm;
import com.utc.form.filter.HotelFilterForm;
import com.utc.form.update.HotelUpdateForm;
import com.utc.repository.IAddressRepository;
import com.utc.repository.IHotelRepository;
import com.utc.specification.HotelSpecification;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HotelService implements IHotelService{

    @Autowired
    private IHotelRepository hotelRepository;

    @Autowired
    private IAddressRepository addressRepository;


    @Override
    public List<Hotel> findAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Page<Hotel> findAllHotelByCity(String city, Pageable pageable) {
        return hotelRepository.getHotelByAddress_City(city,pageable);
    }

    @Override
    public Page<Hotel> findAllHotelByPage(String search, Pageable pageable, HotelFilterForm form) {
        Specification<Hotel> where = HotelSpecification.buildWhere(search, form);
        return hotelRepository.findAll(where,pageable);
    }

    @Override
    public void createHotel(HotelCreateForm form) {
        Hotel hotel = new Hotel();

        hotel.setName(form.getName());
        hotel.setEmail(form.getEmail());
        hotel.setWebsite(form.getWebsite());
        hotel.setDescription(form.getDescription());
        hotel.setRoomCount(form.getRoomCount());

        Address address = addressRepository.getAddressByCityAndAndCountry(form.getCity(),form.getCountry());
        if (address == null){
            Address addressNew = new Address();
            addressNew.setCity(form.getCity());
            addressNew.setCountry(form.getCountry());

            addressRepository.save(addressNew);

            hotel.setAddress(addressNew);
            hotelRepository.save(hotel);
        }else {
            hotel.setAddress(address);
            hotelRepository.save(hotel);
        }
    }

    @Override
    public void updateHotel(int id,HotelUpdateForm form) {
        Hotel hotel = hotelRepository.findById(id).get();

        hotel.setWebsite(form.getWebsite());
        hotel.setDescription(form.getDescription());
        hotel.setRoomCount(form.getRoomCount());

        hotelRepository.save(hotel);
    }
}
