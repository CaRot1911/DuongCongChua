package com.vti.finalexam.entiy;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Account",catalog = "FinalExam")
@DynamicInsert
@Data
public class Account implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username",length = 50,nullable = false,unique = true)
    private String userName;

    @Column(name = "password",length = 800,nullable = false)
    private String passWord;

    @Column(name = "first_name",length = 50,nullable = false)
    private String firstName;

    @Column(name = "last_name",length = 50,nullable = false)
    private String lastName;

    @Column(name = "role",nullable = false,columnDefinition = "ENUM('ADMIN','EMPLOYEE','MANAGER') NOT NULL DEFAULT 'EMPLOYEE'")
    @ColumnDefault(value = "EMPLOYEE")
    @Enumerated(EnumType.STRING)

    private Status role;

    @Formula("concat(first_name,' ',last_name)")
    private String fullName;

    @JoinColumn(name = "department_id",referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;

    public Account(String userName, String firstName, String lastName, Status role) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Account() {

    }

    public enum Status{
        ADMIN,EMPLOYEE,MANAGER;

        public static Status toEnum(String clientString){
            for (Status item : Status.values()){
                if (item.toString().equals(clientString)){
                    return item;
                }
            }

            return null;
        }
    }

}
