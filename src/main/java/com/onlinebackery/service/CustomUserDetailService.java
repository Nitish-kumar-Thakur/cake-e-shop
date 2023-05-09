package com.onlinebackery.service;

import com.onlinebackery.entity.CustomUserDetail;
import com.onlinebackery.entity.User;
import com.onlinebackery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CustomUserDetailService implements UserDetailsService  {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> list = userRepository.findByEmail(username);
        list.orElseThrow(()-> new UsernameNotFoundException("user nhi mila"));
        return list.map(CustomUserDetail::new).get();
    }
}
