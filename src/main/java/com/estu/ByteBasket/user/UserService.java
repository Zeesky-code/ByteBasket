package com.estu.ByteBasket.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> authUser = userRepository.findByUsername(username.toLowerCase());
        if (!authUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        } else {
            return (UserDetails) User.builder()
                    .username(authUser.get().getUsername())
                    .password(authUser.get().getPassword())
                    .build();
        }
    }
}