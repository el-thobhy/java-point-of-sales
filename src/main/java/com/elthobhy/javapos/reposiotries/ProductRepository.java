package com.elthobhy.javapos.reposiotries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elthobhy.javapos.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {//datatype dan primary key
}