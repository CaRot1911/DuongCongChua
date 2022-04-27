package com.utc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "`Booking`",catalog = "`UTCDemo`")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`b_Id`")
    private int id;

    @Column(name = "`b_Date`",nullable = false,columnDefinition = "DEFAULT NOW()")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date date;

    @Column(name = "`b_DurationOfStay`")
    private int timeLive;

    @Column(name = "`b_CheckInDate`",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkIn;

    @Column(name = "`b_CheckOutDate`",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkOut;

    @Column(name = "`b_TypePayment`",nullable = false,columnDefinition = "DEFAULT 'DIRECT'")
    private PaymentType type;

    @Column(name = "`b_TotalRoom`",nullable = false,columnDefinition = "DEFAULT 1")
    private int totalRoom;

    @ManyToOne
    @JoinColumn(name = "`b_hId`",referencedColumnName = "`h_Id`")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "`b_gId`",columnDefinition = "g_Id")
    private Guests guests;

    @Column(name = "`b_TotalAmount`")
    private float amount;

    @Column(name = "`b_Status`",nullable = false,columnDefinition = "DEFAULT 'UNPAID'")
    @Enumerated(EnumType.STRING)
    private bStatus status;

    @OneToMany(mappedBy = "booking")
    private List<RoomBook> roomBooks;

    @OneToMany(mappedBy = "booking")
    private List<UserServices> userServices;
    public enum PaymentType{
        BAKING,DIRECT;
        public static PaymentType toEnum(String strClient){
            for (PaymentType item : PaymentType.values()){
                if (item.toString().equalsIgnoreCase(strClient)){
                    return item;
                }
            }
            return null;
        }
    }

    public enum bStatus{
        UNPAID,PAID;

        public static bStatus toEnum(String strClient){
            for (bStatus item : bStatus.values()){
                if (item.toString().equalsIgnoreCase(strClient)){
                    return item;
                }
            }
            return null;
        }
    }
}
