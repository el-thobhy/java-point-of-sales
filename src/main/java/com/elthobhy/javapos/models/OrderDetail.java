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
@Table(name="Tbl_T_Order_Detail")
public class OrderDetail {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private long id;
    
	@Column(name = "order_headerid", nullable = false)
    private long orderHeaderId;
	@Column(name = "product_id", nullable = false)
    private long productId;
	@Column(name = "qty")
    private long qty;
	@Column(name = "price")
    private long price;


    
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
