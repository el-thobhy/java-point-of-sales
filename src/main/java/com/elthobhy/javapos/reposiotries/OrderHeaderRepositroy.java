package com.elthobhy.javapos.reposiotries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.elthobhy.javapos.models.OrderHeader;

public interface OrderHeaderRepositroy extends JpaRepository<OrderHeader, Long> {
    @Modifying
    @Query(value = "UPDATE tbl_t_order_header SET is_delete=true, update_by=:updateBy, update_date=now() "
            +
            " WHERE id =:id", nativeQuery = true)
    public void deleteOrderHeader(@Param("id") Long id, @Param("updateBy") Integer updateBy);
}
