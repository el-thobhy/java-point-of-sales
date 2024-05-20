package com.elthobhy.javapos.reposiotries;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elthobhy.javapos.models.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {// datatype dan primary key
    Optional<Variant> findByName(String name);

    Optional<Variant> findById(long id);

    Optional<List<Variant>> findByNameContainsIgnoreCase(String name); // jika cek kata yang banyak

    Optional<List<Variant>> findByDescriptionContainsIgnoreCase(String description); // jika cek kata yang banyak

    Optional<List<Variant>> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String name,
            String description); // jika cek
    // kata yang
    // banyak
}
