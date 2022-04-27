package com.utc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "`StarRate`",catalog = "`UTCDemo`")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class StarRate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`sr_Id`")
    private int id;

    @Column(name = "`sr_Image`",length = 100)
    private String image;

    @ManyToOne
    @JoinColumn(name = "`sr_hId`",referencedColumnName = "`h_Id`")
    private Hotel hotel;
}
