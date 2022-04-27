package com.utc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "`HotelImage`",catalog = "`UTCDemo`")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class HotelImage implements Serializable {

    @Id
    @Column(name = "`hi_ImageName`",nullable = false)
    private int imageName;

    @ManyToOne
    @JoinColumn(name = "`hi_hId`",referencedColumnName = "`h_Id`")
    private Hotel hotel;
}
