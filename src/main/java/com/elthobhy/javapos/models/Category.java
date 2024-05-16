package com.elthobhy.javapos.models;

import java.time.LocalDateTime;

// import jakarta.persistence.Entity;

// @Entity
public class Category {
    private long Id;
    private String Name;
    private String Description;
    private boolean IsDeleted;
    private int CreateBy;
    private LocalDateTime CraeteDate;
    private int UpdateBy;
    private LocalDateTime UpdateDate;
    public long getId() {
        return Id;
    }
    public void setId(long id) {
        Id = id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public boolean isIsDeleted() {
        return IsDeleted;
    }
    public void setIsDeleted(boolean isDeleted) {
        IsDeleted = isDeleted;
    }
    public int getCreateBy() {
        return CreateBy;
    }
    public void setCreateBy(int createBy) {
        CreateBy = createBy;
    }
    public LocalDateTime getCraeteDate() {
        return CraeteDate;
    }
    public void setCraeteDate(LocalDateTime craeteDate) {
        CraeteDate = craeteDate;
    }
    public int getUpdateBy() {
        return UpdateBy;
    }
    public void setUpdateBy(int updateBy) {
        UpdateBy = updateBy;
    }
    public LocalDateTime getUpdateDate() {
        return UpdateDate;
    }
    public void setUpdateDate(LocalDateTime updateDate) {
        UpdateDate = updateDate;
    }

    // public Category(long id, String name, String description, boolean isDeleted, int createBy, LocalDateTime craeteDate,
    //         int updateBy, LocalDateTime updateDate) {
    //     Id = id;
    //     Name = name;
    //     Description = description;
    //     IsDeleted = isDeleted;
    //     CreateBy = createBy;
    //     CraeteDate = craeteDate;
    //     UpdateBy = updateBy;
    //     UpdateDate = updateDate;
    // }
    // public Category(long id, String name, String description){
    //     this(
    //         id, 
    //         name, 
    //         description ,
    //         false, 
    //         1 ,
    //         LocalDateTime.now(),
    //         1, 
    //         LocalDateTime.now());
    // }
    // public Category(){
    //     this(0, "", "", false, 0, null, 0, null);
    // }
}
