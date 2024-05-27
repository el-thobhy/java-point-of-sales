package com.elthobhy.javapos.reposiotries;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// import org.springframework.transaction.annotation.Transactional;

import com.elthobhy.javapos.models.OrderDetail;

// @Transactional(readOnly = true)
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
        @Query(value = "SELECT od.id, " +
                        " od.order_headerid, " +
                        " od.product_id, " +
                        " od.qty, " +
                        " od.price, " +
                        " od.is_delete, " +
                        " od.create_by, " +
                        " od.create_date, " +
                        " od.update_by, " +
                        " od.update_date " +
                        " FROM tbl_t_order_detail od " +
                        " WHERE od.is_delete = false AND od.order_headerid = :orderHeaderId", nativeQuery = true)
        Optional<List<OrderDetail>> findByOrderHeaderId(@Param("orderHeaderId") long orderHeaderId);

        // untuk save
        // @Modifying
        // @Transactional

        @Modifying(clearAutomatically = true)
        @Query(value = "UPDATE tbl_t_order_detail SET is_delete=true, update_by=:updateBy, update_date=:updateDateTime "
                        +
                        " WHERE order_headerid = :orderHeaderId", nativeQuery = true)
        public void deleteOrderDetail(@Param("orderHeaderId") Long orderHeaderId, @Param("updateBy") int updateBy,
                        @Param("updateDateTime") LocalDateTime updateDateTime);
}
