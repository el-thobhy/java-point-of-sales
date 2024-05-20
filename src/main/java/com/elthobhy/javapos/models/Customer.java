package com.elthobhy.javapos.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Tbl_M_Customer")
public class Customer {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "email", unique = true, nullable = true)
    private String email;
    @Column(name = "password", nullable = false, length = 50)
    private String password;
    @Column(name = "address", nullable = true)
    private String address;
    @Column(name = "phone", nullable = true, length = 50)
    private String phone;
    @Column(name = "role_id", nullable = false)
    private int roleId;
    
    @Column(name = "is_delete")
    private boolean isDeleted;
    @Column(name = "create_by")
    private int createBy;
    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;
    @Column(name = "update_by")
    private int updateBy;
    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
