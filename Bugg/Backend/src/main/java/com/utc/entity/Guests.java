package com.utc.entity;

import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "`Guests`")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Guests implements Serializable {

    @Id
    @Column(name = "`g_Id`",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "`g_FirstName`",nullable = false,length = 100)
    private String firstName;

    @Column(name = "`g_LastName`",nullable = false,length = 100)
    private String lastName;

    @Column(name = "`g_IdCard`",nullable = false,length = 100,unique = true)
    private String idCard;

    @Column(name = "`g_CreditCard`",nullable = false,length = 100,unique = true)
    private String creditCard;

    @Column(name = "`g_Email`",nullable = false,length = 100,unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "`g_utId`",columnDefinition = "DEFAULT 2",nullable = false)
    private UserType userType;

    @ManyToOne
    @JoinColumn(name = "`g_addId`",nullable = false)
    private Address address;

    @Formula("concat(g_FirstName,' ',g_LastName")
    private String fullName;

    @OneToMany(mappedBy = "guests")
    private List<Booking> bookings;


}
