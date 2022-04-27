package com.utc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "`Hotel`",catalog = "`UTCDemo`")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Hotel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`h_Id`")
    private int id;

    @Column(name = "`h_Name`",length = 100,nullable = false,unique = true)
    private String name;

    @Column(name = "`h_EmailAddress`",length = 100,nullable = false,unique = true)
    private String email;

    @Column(name = "`h_Website`",length = 100, nullable = false,unique = true)
    private String website;

    @Column(name = "`h_Description`",length = 100,nullable = false)
    private String description;

    @Column(name = "`h_RoomCount`",nullable = false)
    private int roomCount;

    @ManyToOne
    @JoinColumn(name = "`h_addId`",referencedColumnName = "`add_Id`")
    private Address address;

    @OneToMany(mappedBy = "hotel")
    private List<StarRate> starRate;

    @OneToMany(mappedBy = "hotel")
    private List<HotelImage> hotelImages;

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;

    @OneToMany(mappedBy = "hotel")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "hotel")
    private List<HotelServices> hotelServices;
}
