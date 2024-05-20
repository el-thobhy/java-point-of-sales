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
@Table(name="Tbl_T_Order_Header")
public class OrderHeader {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private long id;

    @Column(name = "trx_code", nullable = false, unique = true)
    private String trxCode;
    @Column(name = "customer_id", nullable = false)
    private long customerId;
    @Column(name = "amount")
    private double amount;
    @Column(name = "total_qty")
    private int totalQty;
    @Column(name = "is_checked_out")
    private boolean isCheckedOut;

    
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
