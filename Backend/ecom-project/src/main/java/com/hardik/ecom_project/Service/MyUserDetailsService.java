package com.hardik.ecom_project.Service;

import com.hardik.ecom_project.Model.UserPrincipal;
import com.hardik.ecom_project.Model.Users;
import com.hardik.ecom_project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User Not Found");
        }

        return new UserPrincipal(user);
    }
}
