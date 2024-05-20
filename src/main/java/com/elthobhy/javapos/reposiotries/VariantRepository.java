package com.elthobhy.javapos.reposiotries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elthobhy.javapos.models.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {// datatype dan primary key
    Optional<Variant> findByName(String name);
}
