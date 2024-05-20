package com.elthobhy.javapos.reposiotries;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elthobhy.javapos.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {// datatype dan primary key
    Optional<Product> findByName(String name);

    Optional<Product> findById(long id);

    Optional<List<Product>> findByNameContainsIgnoreCase(String name); // jika cek kata yang banyak

    Optional<List<Product>> findByDescriptionContainsIgnoreCase(String description); // jika cek kata yang banyak

    Optional<List<Product>> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String name,
            String description); // jika cek
    // kata yang
    // banyak
}
