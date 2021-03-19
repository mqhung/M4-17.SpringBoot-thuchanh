package com.codegym.demospringboot.service.appuser;

import com.codegym.demospringboot.model.AppUser;
import com.codegym.demospringboot.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AppUserService implements IAppUserService, UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public AppUser getUserByName(String name) {
        return appUserRepository.getAppUsersByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser= this.getUserByName(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(appUser.getRole());
        UserDetails userDetails = new User(
                appUser.getName(),
                appUser.getPassword(),
                authorities
        );
        return userDetails;
    }

    @Override
    public AppUser getCurrentUser() {
        AppUser appUser;
        String name;
        Object ob = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ob instanceof UserDetails){
            name = ((UserDetails)ob).getUsername();
        }
        else {
            name = ob.toString();
        }
        appUser = this.getUserByName(name);

        return appUser;
    }
}
