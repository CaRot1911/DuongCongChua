package com.utc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "`RoomRateDiscount`",catalog = "`UTCDemo`")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class RoomRateDiscount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`rrd_Id`")
    private int id;

    @Column(name = "`rrd_Rate`",nullable = false)
    private float rate;

    @Column(name = "`rrd_StartMonth`",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startMonth;

    @Column(name = "`rrd_EndMonth`",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endMonth;

    @ManyToOne
    @JoinColumn(name = "`rrd_rtId`",referencedColumnName = "`rt_Id`")
    private RoomType roomType;
}
