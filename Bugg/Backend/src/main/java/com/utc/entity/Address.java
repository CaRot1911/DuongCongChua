package com.utc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Table(name = "`Address`")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`add_Id`")
    private int id;

    @Column(name = "`add_Country`",length = 100)
    private String country;

    @Column(name = "`add_City`",length = 100,nullable = false)
    private String city;

//    @OneToMany(mappedBy = "g_addId",fetch = FetchType.EAGER)
//    private List<Guests> guests;

    public Address(int id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }
}
