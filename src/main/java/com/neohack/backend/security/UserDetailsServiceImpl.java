package com.neohack.backend.security;

import com.neohack.backend.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import com.neohack.backend.dao.UserRepository;
import com.neohack.backend.exception.UserDisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(value).orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь c именем %s не найден!", value)));
        User user = userRepository.findByEmail(value).orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь c email %s не найден!", value)));

        if (!user.isActive()) {
            throw new UserDisabledException(String.format("Пользователь %s отключён!", user.getUsername()));
        }

        return new UserDetailsImpl(user);
    }
}
