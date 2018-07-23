//package com.example.WebTask.controller;
//
//
//import com.example.WebTask.AbstractControllerTest;
//import com.example.WebTask.AbstractMvcTest;
//import com.example.WebTask.WebTaskApplicationTests;
//import com.example.WebTask.modules.role.Role;
//import com.example.WebTask.modules.user.User;
//
//import com.example.WebTask.modules.user.UserController;
//import com.example.WebTask.modules.user.UserService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.mockito.BDDMockito.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Transactional
//public class UserControllerTest extends AbstractMvcTest {
//
//    private String baseUri = "/users";
//
//    @Override
//    protected void doInit() throws Exception {
//        registerUser("spawn", "password").andExpect(status().isCreated());
//    }
//
//    @Test
//    public void getAll() throws Exception {
//        String uri = baseUri;
//
//        final String token = extractToken(login("spawn", "password").andReturn());
//        System.out.println(">>>>>>>>>>>>> " + token);
//        MvcResult result =
//                mockMvc.perform(get(uri)
//                            .header("Authorization", "Bearer " + token)
//                            .accept(MediaType.APPLICATION_JSON))
//                            .andExpect(status().isOk())
//                            .andReturn();
//
//
//    }
//
//    private ResultActions registerUser(String username, String password) throws Exception {
//        return mockMvc.perform(
//                post("/api/users")
//                        .content("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}"));
//    }
//}
