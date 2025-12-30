package com.cjc.app.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cjc.app.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
   
	    List<Product> findByName(String name);
	
	    @Query(
		    "SELECT p FROM Product p " +
		    "WHERE (:category IS NULL OR p.category = :category) " +
		    "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
		    "AND (:maxPrice IS NULL OR p.price <= :maxPrice)"
		)
        List<Product> filterProducts(
                @Param("category") String category,
                @Param("minPrice") Double minPrice,
                @Param("maxPrice") Double maxPrice
        );
}	
