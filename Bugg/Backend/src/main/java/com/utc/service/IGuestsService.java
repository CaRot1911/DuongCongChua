package com.utc.service;

import com.utc.entity.Guests;
import com.utc.form.create.GuestsCreateForm;
import com.utc.form.filter.GuestsFilter;
import com.utc.form.update.GuestsUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface IGuestsService {

    public List<Guests> findAllGuests();

    public Page<Guests> findAllByPage(String search, Pageable pageable, GuestsFilter filter);

    public void createGuests(GuestsCreateForm form);

    public void updateGuests(int id,GuestsUpdateForm form);

    public void deleteGuests(int id);
}
