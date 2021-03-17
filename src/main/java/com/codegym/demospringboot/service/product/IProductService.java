package com.codegym.demospringboot.service.product;

import com.codegym.demospringboot.model.Product;
import com.codegym.demospringboot.service.ISevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService extends ISevice<Product> {
    Page<Product> findByProductName(String name, Pageable pageable);
    Page<Product> findByCategoryName(Long id, Pageable pageable);
}
