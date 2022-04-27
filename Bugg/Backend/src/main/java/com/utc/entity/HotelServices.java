package com.utc.entity;

import jdk.internal.dynalink.linker.LinkerServices;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "`HotelServices`",catalog = "`UTCDemo`")
@Entity
@Getter
@Setter
public class HotelServices implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`hs_Id`")
    private int id;

    @Column(name = "`hs_Name`",length = 100,nullable = false)
    private String name;

    @Column(name = "`hs_Cost`",nullable = false)
    private float cost;

    @ManyToOne
    @JoinColumn(name = "`hs_hId`",referencedColumnName = "`h_Id`")
    private Hotel hotel;

    @OneToMany(mappedBy = "hotelServices")
    private List<UserServices> userServices;
}
