package com.codegym.demospringboot.service.appuser;

import com.codegym.demospringboot.model.AppUser;
import com.codegym.demospringboot.service.ISevice;

public interface IAppUserService {
    AppUser getUserByName(String name);
    AppUser getCurrentUser();
}
