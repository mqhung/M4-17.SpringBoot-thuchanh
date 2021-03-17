package com.codegym.demospringboot.repo;

import com.codegym.demospringboot.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICategoryRepository extends PagingAndSortingRepository<Category, Long> {
}
