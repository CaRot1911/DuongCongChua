package com.utc.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Table(name = "`UserType`")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ut_Id`")
    private int id;

    @Column(name = "`ut_Name`" ,nullable = false, columnDefinition = "DEFAULT 'GUESTS'")
    @Enumerated(EnumType.STRING)
    private Type type;

//    @OneToMany(mappedBy = "userType")
//    private List<Guests> guests;

    public enum Type{
        GUESTS,ADMIN;

        public static Type toEnum(String strClient){
            for (Type item : Type.values()){
                if (item.toString().equalsIgnoreCase(strClient)){
                    return item;
                }
            }
            return null;
        }
    }

}
