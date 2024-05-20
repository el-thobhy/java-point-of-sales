package com.elthobhy.javapos.reposiotries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elthobhy.javapos.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {//datatype dan primary key
}
