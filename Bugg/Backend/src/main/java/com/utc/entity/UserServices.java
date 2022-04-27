package com.utc.entity;

import com.utc.entity.doublepk.UserServiceKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "`UserServices`",catalog = "`UTCDemo`")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserServices implements Serializable {

    @EmbeddedId
    private UserServiceKey key;

    @ManyToOne
    @JoinColumn(name = "`b_Id`")
    private Booking booking;


    @ManyToOne
    @JoinColumn(name = "`hs_Id`")
    private HotelServices hotelServices;
}
