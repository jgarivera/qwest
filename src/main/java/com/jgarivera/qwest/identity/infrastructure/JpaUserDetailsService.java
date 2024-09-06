package com.jgarivera.qwest.identity.infrastructure;

import com.jgarivera.qwest.identity.domain.model.User;
import com.jgarivera.qwest.identity.domain.model.UserRepository;
import com.jgarivera.qwest.identity.domain.model.Username;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    JpaUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!Username.isValid(username)) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = repository.findByUsername(new Username(username));

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        } else {
            return user;
        }
    }
}
