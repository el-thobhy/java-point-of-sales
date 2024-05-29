package com.elthobhy.javapos.reposiotries;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elthobhy.javapos.models.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {// datatype dan primary key
        Optional<Variant> findByName(String name);

        Optional<Variant> findById(long id);

        Optional<List<Variant>> findByDeleted(boolean deleted);

        Optional<List<Variant>> findByNameContainsIgnoreCase(String name); // jika cek kata yang banyak

        Optional<List<Variant>> findByDescriptionContainsIgnoreCase(String description); // jika cek kata yang banyak

        Optional<List<Variant>> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String name,
                        String description); // jika cek
        // kata yang
        // banyak

        @Query(value = "SELECT " + //
                        "    v.create_by \"createBy\", " + //
                        "    v.is_deleted \"deleted\", " + //
                        "    c.name As \"categoryName\", " + //
                        "    v.update_by \"updateBy\", " + //
                        "    v.category_id \"categoryId\", " + //
                        "    v.create_date \"createDate\", " + //
                        "    v.id, " + //
                        "    v.update_date \"updateDate\", " + //
                        "    v.name, " + //
                        "    v.description " + //
                        "FROM " + //
                        "    tbl_m_variant AS v " + //
                        "    LEFT JOIN tbl_m_category AS c ON v.category_id = c.id " + //
                        "WHERE " + //
                        "    v.is_deleted IS NOT true" +
                        " ORDER BY v.id", nativeQuery = true)
        Optional<List<Map<String, Object>>> findAllNative();

        @Query(value = "SELECT " + //
                        "    v.create_by \"createBy\", " + //
                        "    v.is_deleted \"deleted\", " + //
                        "    c.name As \"categoryName\", " + //
                        "    v.update_by \"updateBy\", " + //
                        "    v.category_id \"categoryId\", " + //
                        "    v.create_date \"createDate\", " + //
                        "    v.id, " + //
                        "    v.update_date \"updateDate\", " + //
                        "    v.name, " + //
                        "    v.description " + //
                        "FROM " + //
                        "    tbl_m_variant AS v " + //
                        "    LEFT JOIN tbl_m_category AS c ON v.category_id = c.id " + //
                        "WHERE " + //
                        "    v.is_deleted IS NOT true " +
                        " AND v.id=:id", nativeQuery = true)
        Optional<Map<String, Object>> findByIdNative(@Param("id") long id);

        @Query(value = "SELECT " + " v.*, c.name As \"categoryName\" " +
                        " FROM tbl_m_variant AS v " +
                        " LEFT JOIN tbl_m_category AS c ON v.category_id = c.id " +
                        " WHERE " +
                        " v.is_deleted IS NOT true" +
                        " AND " + " LOWER(c.name) LIKE " + " %:name% ", nativeQuery = true)
        Optional<List<Map<String, Object>>> findByCategoryNameNative(@Param("name") String name);

        @Query(value = "SELECT * from vw_variant " +
                        " WHERE " +
                        " is_deleted IS NOT true" +
                        " AND " + " category_id = " + ":id ", nativeQuery = true)
        Optional<List<Map<String, Object>>> findByCategoryNameByIdNative(@Param("id") long id);

}
