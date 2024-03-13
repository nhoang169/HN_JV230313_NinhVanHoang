package com.example.api.student.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student extends BaseEntity {
    private String studentName;
    private String address;
    private Date birthday;
    private String imageUrl;
    private String phone;
    private int sex;
}
