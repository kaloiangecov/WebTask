package com.example.WebTask.validator;

import com.example.WebTask.modules.academics.Academics;
import com.example.WebTask.modules.academics.AcademicsRepository;
import com.example.WebTask.modules.academics.AcademicsService;
import com.example.WebTask.modules.user.User;
import com.example.WebTask.modules.user.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class ResponseGenerator {

    private static final long NEW_ID = 0L;

    public static Map<String, String> setErrors(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error: bindingResult.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }


    public static Map<String, String> codeExists(Academics academics, AcademicsService academicsService,
                                                 Map<String, String> errors, Long id) {
        if (!errors.containsKey("code")) {
            if (academicsService.codeExists(academics.getCode(), id)) {
                errors.put("code", "Code already exists");
            }
        }

        return errors;
    }

    public static Map<String, String> codeExists(Academics academics, AcademicsService academicsService,
                                                 Map<String, String> errors) {
        if (!errors.containsKey("code")) {
            if (academicsService.codeExists(academics.getCode(), NEW_ID)) {
                errors.put("code", "Code already exists");
            }
        }

        return errors;
    }

    public static Map<String, String> usernameExists(User user, UserService userService,
                                                     Map<String, String> errors, Long id) {
        if (!errors.containsKey("username")) {
            if (userService.usernameExists(user.getUsername(), id)) {
                errors.put("username", "Username already exists");
            }
        }

        return errors;
    }

    public static Map<String, String> usernameExists(User user, UserService userService,
                                                     Map<String, String> errors) {
        if (!errors.containsKey("username")) {
            if (userService.usernameExists(user.getUsername(), NEW_ID)) {
                errors.put("username", "Username already exists");
            }
        }

        return errors;
    }

    public static Map<String, String> emailExists(User user, UserService userService,
                                                  Map<String, String> errors, Long id) {
        if (!errors.containsKey("email")) {
            if (userService.emailExists(user.getEmail(), id)) {
                errors.put("email", "Email already exists");
            }
        }

        return errors;
    }

    public static Map<String, String> emailExists(User user, UserService userService,
                                                  Map<String, String> errors) {
        if (!errors.containsKey("email")) {
            if (userService.emailExists(user.getEmail(), NEW_ID)) {
                errors.put("email", "Email already exists");
            }
        }

        return errors;
    }

}
