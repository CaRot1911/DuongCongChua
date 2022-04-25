package com.vti.finalexam.entiy;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Department",catalog = "FinalExam")
@Data
public class Department implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",length = 50,nullable = false,unique = true,updatable = false)
    private String name;

    @Column(name = "total_member",updatable = false)
    private int totalMember;

    @Column(name = "type",nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "created_date",updatable = false)
    @ColumnDefault(value = "DATETIME DEFAULT CURDATE()")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp  // insert tai thoi diem tao doi tuong
    private Date createDate;

    public Department() {
    }

    public Department(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @OneToMany(mappedBy = "department")
    private List<Account> accounts;
    public enum Type{
        Dev,Test,ScrumMaster,PM;

        public static Type toEnum(String clientString){
            for (Type item : Type.values()){
                if(item.toString().equals(clientString)){
                    return item;
                }
            }
            return null;
        }

    }


}
