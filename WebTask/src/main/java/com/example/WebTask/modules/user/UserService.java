package com.example.WebTask.modules.user;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.WebTask.modules.role.Role;
import com.example.WebTask.modules.role.RoleRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import javafx.scene.shape.QuadCurve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author kaloi
 */
@Service
public class UserService implements UserDetailsService {

    private static final long USER_ROLE = 2L;
    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bcryptEncoder) {
        this.bcryptEncoder = bcryptEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }



    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usernameExists(username, 0L);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));
        return authorities;
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) throws Exception{
        User user = validateId(id);
        return user;
    }

    public User create(User user, Long id){
        Role role;
        if (id != null) {
            role = roleRepository.findOne(id);
            user.setRole(role);
        } else if (id == null && user.getRole() == null) {
            role = roleRepository.findOne(USER_ROLE);
            user.setRole(role);
        }
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(User user, Long id, Principal principal) throws Exception{
        User dbUser = validateId(id);
        User principalUser = userRepository.findByUsername(principal.getName());
        if (principalUser.getId() == id || principalUser.getRole().getName().equals("ADMIN")) {
            dbUser.setEmail(user.getEmail());
            dbUser.setUsername(user.getUsername());
            dbUser.setPassword(bcryptEncoder.encode(user.getPassword()));
            if (principalUser.getRole().getName().equals("ADMIN")) {
                dbUser.setRole(user.getRole());
            }
        } else {
            throw new Exception();
        }

        return userRepository.save(dbUser);
    }

    public User delete(Long id) throws Exception{
        User user = validateId(id);
        userRepository.delete(user);
        return user;
    }

    public Page<User> getUsersPage(int page, int elementsPerPage, String searchTerm) {
        PageRequest pageRequest = new PageRequest(page, elementsPerPage);
        Predicate predicate = UserPredicates.searchFields(searchTerm);
        return userRepository.findAll(predicate, pageRequest);
    }

    public long getUsersCount() {
        return userRepository.count();
    }

    private User validateId(Long id) throws Exception {
        User user = userRepository.findOne(id);
        if(user != null) {
            return user;
        }
        throw new Exception();
    }

    public User usernameExists(String username, long id) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getId() != id) {
            return user;
        }
        return null;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User emailExists(String email, long id) {
        User user = userRepository.findByEmailContaining(email);
        if (user != null && user.getId() != id) {
            return user;
        }
        return null;
    }

}
