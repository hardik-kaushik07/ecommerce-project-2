package com.hardik.ecom_project.Service;

import com.hardik.ecom_project.Model.Users;
import com.hardik.ecom_project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLSession;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Users register(Users user){
//        user.setId(null);
        user.setPassword(encoder.encode(user.getPassword()));
        if(user.getRole().equalsIgnoreCase("ADMIN")){
            user.setRole("ROLE_ADMIN");
        }
        else{
            user.setRole("ROLE_USER");
        }
        return userRepository.save(user);
    }

    public String verify(Users user) {
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername(), user.getRole());
        }
        else{
            return "Fail";
        }
    }

    public Users getUserByUsername(String username) {
        Users user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User Not Found");
        }
        return user;
    }
}
