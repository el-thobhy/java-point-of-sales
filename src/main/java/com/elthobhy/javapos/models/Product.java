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
@Table(name = "Tbl_M_Product")
public class Product {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private long id;
    @Column(name = "variant_id")
    private long variantId;
    @Column(name = "name", unique=true, length=100, nullable = false)
    private String name;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "is_delete")
    private boolean deleted;
    @Column(name = "price")
    private double price;
    @Column(name = "stock")
    private int stock;
    @Column(name = "image")
    private String image;
    @Column(name = "create_by")
    private int createBy;
    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;
    @Column(name = "update_by")
    private int updateBy;
    @Column(name = "update_date")
    private LocalDateTime updateDate;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getVariantId() {
        return variantId;
    }
    public void setVariantId(long variantId) {
        this.variantId = variantId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isDeleted() {
        return deleted;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getCreateBy() {
        return createBy;
    }
    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    public int getUpdateBy() {
        return updateBy;
    }
    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

}
