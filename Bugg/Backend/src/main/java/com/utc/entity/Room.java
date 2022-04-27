package com.utc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "`Room`",catalog = "`UTCDemo`")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`r_Id`")
    private int id;

    @Column(name = "`r_Number`",nullable = false)
    private String number;

    @Column(name = "`r_Status`",nullable = false,columnDefinition = "DEFAULT 'DRUM'")
    @Enumerated(EnumType.STRING)
    private StatusRoom statusRoom;

    @ManyToOne
    @JoinColumn(name = "`r_rtId`",referencedColumnName = "`rt_Id`")
    private RoomType roomType;

    @ManyToOne
    @JoinColumn(name = "`r_hId`",referencedColumnName = "`h_Id`")
    private Hotel hotel;

    @OneToMany(mappedBy = "room")
    private List<RoomBook> roomBooks;
    private enum StatusRoom{
        DRUM,BOOKING;
        public StatusRoom toEnum(String strClient){
            for (StatusRoom item : StatusRoom.values()){
                if (item.toString().equalsIgnoreCase(strClient)){
                    return item;
                }
            }
            return null;
        }
    }
}
