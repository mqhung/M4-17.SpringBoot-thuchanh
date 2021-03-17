package com.codegym.demospringboot.repo;

import com.codegym.demospringboot.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
    //tim kiem san pham theo category
    @Query(value = "select * from product where product.category_id = ?", nativeQuery = true)
    Page<Product> findProductByCategoryName(Long id, Pageable pageable);

    //tim kiem san pham theo ten
    @Query(value = "select  * from product where product.name like ?", nativeQuery = true)
    Page<Product> findProductByName(String name, Pageable pageable);
}
