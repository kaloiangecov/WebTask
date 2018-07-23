package com.example.WebTask.modules.user;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.WebTask.validator.ResponseGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class UserController {

    private UserService userService;
    private static final int EMPTY = 0;
    private static final long NEW_USER_ID = 0L;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private Map<String, String> errors;
    
    @GetMapping(value = "/users")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping(value = "/users/count")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public long findUsersCount() {
        return userService.getUsersCount();
    }

    @GetMapping(value = "/users/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public User findUserById(@PathVariable Long id) throws Exception {
        return userService.findById(id);
    }

    @PostMapping(value = "/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createUser(@RequestBody @Valid User user,
                                             BindingResult bindingResult,
                                             @RequestParam(name = "id", required = false) Long id) throws Exception {
        errors = new HashMap<>();

        if (bindingResult.hasErrors()) {
            errors = ResponseGenerator.setErrors(bindingResult);
        }
        errors = setAlreadyExistsErrors(user, NEW_USER_ID, errors);
        if (errors.size() != EMPTY) {
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(userService.create(user, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public User removeUser(@PathVariable Long id) throws Exception {
        return userService.delete(id);
    }

    @PutMapping(value = "/users/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> updateUser(@PathVariable Long id,
                                             @RequestBody @Valid User user,
                                             BindingResult bindingResult,
                                             Principal principal) throws Exception {
        errors = new HashMap<>();

        if (bindingResult.hasErrors()) {
            errors = ResponseGenerator.setErrors(bindingResult);
        }
        errors = setAlreadyExistsErrors(user, id, errors);
        if (errors.size() != EMPTY) {
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(userService.update(user, id, principal), HttpStatus.OK);
    }

    @GetMapping(value = "/users/page")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<User> findUsersPage(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(name = "elements_per_page", defaultValue = "8") int elementsPerPage,
                                    @RequestParam(name = "searchTerm", defaultValue = "") String searchTerm) {

        return userService.getUsersPage(page, elementsPerPage, searchTerm);
    }

    @GetMapping(value = "/users/exists/username")
    public Boolean usernameExists(@RequestParam(name = "username") String username,
                                  @RequestParam(name = "id") long id) {
        if (userService.usernameExists(username, id) != null) {
            return true;
        }
        return false;
    }

    @GetMapping(value = "/users/exists/email")
    public Boolean emailExists(@RequestParam(name = "email") String email,
                               @RequestParam(name = "id") long id) {
        if (userService.emailExists(email, id) != null) {
            return true;
        }
        return false;
    }


    private Map<String, String> setAlreadyExistsErrors(User user, Long id, Map<String, String> errors) {

        if (!errors.containsKey("email")) {
            User emailSearch = userService.emailExists(user.getEmail(), id);
            if (emailSearch != null) {
                errors.put("email", "Email already exists");
            }
        }
        if (!errors.containsKey("username")) {
            User usernameSearch = userService.usernameExists(user.getUsername(), id);
            if (usernameSearch != null) {
                errors.put("username", "Username already exists");
            }
        }

        return errors;
    }

    @PostMapping(value="/signup")
    public User saveUser(@RequestBody User user, @RequestParam(name = "id", required = false) Long id){
        return userService.create(user, id);
    }

    @GetMapping(value = "/users/username/{username}")
    public User findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

}
