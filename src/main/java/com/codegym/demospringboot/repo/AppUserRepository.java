package com.codegym.demospringboot.repo;

import com.codegym.demospringboot.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser getAppUsersByName(String name);
}
