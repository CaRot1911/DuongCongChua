package com.utc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "`RoomBook`",catalog = "`UTCDemo`")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class RoomBook implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`rb_Id`")
    private int id;

    @ManyToOne
    @JoinColumn(name = "`rb_rId`",referencedColumnName = "`r_Id`")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "`rb_bId`",referencedColumnName = "`b_Id`")
    private Booking booking;
}
