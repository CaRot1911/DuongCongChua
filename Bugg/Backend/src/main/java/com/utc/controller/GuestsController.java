package com.utc.controller;

import com.utc.dto.GuestsDTO;
import com.utc.entity.Guests;
import com.utc.form.create.GuestsCreateForm;
import com.utc.form.filter.GuestsFilter;
import com.utc.form.update.GuestsUpdateForm;
import com.utc.service.IGuestsService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/UTCDemo/guests")
@Transactional
public class GuestsController {

    @Autowired
    private IGuestsService guestsService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> listGuests(){
        List<Guests> list = guestsService.findAllGuests();
        List<GuestsDTO> guestsDTOList = modelMapper.map(list,new TypeToken<List<GuestsDTO>>(){}.getType());
        return new ResponseEntity<List<GuestsDTO>>(guestsDTOList, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findAllGuestsByPage(@RequestParam(name = "search",required = false) String search, GuestsFilter filter, Pageable pageable){
        Page<Guests> page = guestsService.findAllByPage(search, pageable, filter);
        List<GuestsDTO> guestsDTOList = modelMapper.map(page.getContent(),new TypeToken<List<GuestsDTO>>(){}.getType());
        Page<GuestsDTO> guestsDTOPage = new PageImpl<>(guestsDTOList,pageable,page.getTotalElements());

        return new ResponseEntity<Page<GuestsDTO>>(guestsDTOPage,HttpStatus.OK);
    }

    @PostMapping("/createGuests")
    public ResponseEntity<?> createGuests(GuestsCreateForm form){
        guestsService.createGuests(form);
        return new ResponseEntity<>("Create Success!!!",HttpStatus.OK);
    }

    @PutMapping("/updateGuests")
    public ResponseEntity<?> updateGuests(@RequestParam(name = "id") int id, GuestsUpdateForm form){
        guestsService.updateGuests(id,form);
        return new ResponseEntity<>("Update Success!!!",HttpStatus.OK);
    }

    @DeleteMapping("/deleteGuests")
    public ResponseEntity<?> deleteGuests(@RequestParam(name = "id") int id){
        guestsService.deleteGuests(id);
        return new ResponseEntity<>("Delete Success!!!",HttpStatus.OK);
    }

}
