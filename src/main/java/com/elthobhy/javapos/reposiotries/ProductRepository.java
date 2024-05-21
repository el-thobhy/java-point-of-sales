package com.elthobhy.javapos.reposiotries;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elthobhy.javapos.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {// datatype dan primary key
        Optional<Product> findByName(String name);

        Optional<Product> findById(long id);

        Optional<List<Product>> findByDeleted(boolean deleted);

        Optional<List<Product>> findByNameContainsIgnoreCase(String name); // jika cek kata yang banyak

        Optional<List<Product>> findByDescriptionContainsIgnoreCase(String description); // jika cek kata yang banyak

        Optional<List<Product>> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String name,
                        String description); // jika cek
        // kata yang
        // banyak

        /**
         * @return
         */
        @Query(value = "SELECT " + //
                        "    p.create_by createBy, " + //
                        "    p.is_delete deleted, " + //
                        "    p.price, " + //
                        "    p.stock, " + //
                        "    v.name as variantName, " + //
                        "    c.name as categoryName, " + //
                        "    p.update_by updateBy, " + //
                        "    p.create_date createDate, " + //
                        "    p.id, " + //
                        "    p.update_date updateDate, " + //
                        "    p.variant_id variantId, " + //
                        "    p.name, " + //
                        "    p.description, " + //
                        "    p.image " + //
                        "FROM " + //
                        "    tbl_m_product AS p " + //
                        "    INNER JOIN tbl_m_variant AS v ON p.variant_id = v.id " +
                        " INNER JOIN tbl_m_category AS c ON v.category_id=c.id " + //
                        " WHERE " + //
                        "    p.is_delete IS NOT true", nativeQuery = true)
        Optional<List<Map<String, Object[]>>> findAllNative();
}