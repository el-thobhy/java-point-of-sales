package com.elthobhy.javapos.reposiotries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elthobhy.javapos.models.OrderHeader;

public interface OrderHeaderRepositroy extends JpaRepository<OrderHeader, Long> {

}
