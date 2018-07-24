package com.example.WebTask.controller;



import com.example.WebTask.AbstractMvcTest;

import com.example.WebTask.modules.role.RoleRepository;
import com.example.WebTask.modules.user.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Transactional
public class UserControllerTest extends AbstractMvcTest {

    private String baseUri = "/users";

    @Autowired
    private UserService service;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected void doInit() throws Exception {
        registerUser("spawn", "password", "spawn@gmail.com").andExpect(status().isCreated());
    }


    @Test
    public void getAll() throws Exception {
        String uri = baseUri;

        String someUri = "http://localhost:8080/WebTask/users/exists/username?username=ajax&id=0";

        mockMvc.perform(
                post(someUri)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        final String token = extractToken(login("ajax", "password").andReturn());

        MvcResult result =
                mockMvc.perform(get(uri)
                            .header("Authorization", "Bearer " + token)
                            .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn();


    }

    private ResultActions registerUser(String username, String password, String email) throws Exception {
        return mockMvc.perform(
                post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"" + username + "\"," +
                                 "\"password\":\"" + password + "\"," +
                                " \"email\": \"" + email + "\"}"));
    }
}
