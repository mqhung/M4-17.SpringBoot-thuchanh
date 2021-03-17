package com.codegym.demospringboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISevice<T> {
    Iterable<T> findAll();
    Page<T> findAll(Pageable pageable);
    T findById(Long id);
    T save(T t);
    void delete(Long id);
}
