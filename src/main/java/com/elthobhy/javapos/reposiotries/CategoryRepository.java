package com.elthobhy.javapos.reposiotries;

// import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elthobhy.javapos.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {//datatype dan primary key
    Optional<Category> findByName(String name);
    // Optional<List<Category>> findByDescription(String description); jika cek kata yang banyak
}
