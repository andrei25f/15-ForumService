package ait.cohort46.security;

import ait.cohort46.accounting.dao.UserRepository;
import ait.cohort46.accounting.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
        String[] roles = user.getRoles().stream()
                .map(r -> "ROLE_" + r.name())
                .toArray(String[]::new);
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
                AuthorityUtils.createAuthorityList(roles));
    }
}
