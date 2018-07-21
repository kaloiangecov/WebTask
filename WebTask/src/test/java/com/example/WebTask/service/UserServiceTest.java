package com.example.WebTask.service;

import com.example.WebTask.WebTaskApplicationTests;
import com.example.WebTask.modules.role.Role;
import com.example.WebTask.modules.role.RoleRepository;
import com.example.WebTask.modules.user.User;
import com.example.WebTask.modules.user.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;



import java.security.Principal;
import java.util.Collection;
import java.util.List;

public class UserServiceTest extends WebTaskApplicationTests {

    @Autowired
    private UserService service;
    @Autowired
    private RoleRepository roleRepository;
    @Mock
    private Principal principal;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testFindAll() {
        List<User> list = service.findAll();
        long count = service.getUsersCount();

        Assert.assertNotNull(list);
        Assert.assertEquals("failure - expected list size", count, list.size());
    }

    @Test
    public void testFindOne() throws Exception {

        Long id = new Long(1);

        User entity = service.findById(id);

        Assert.assertNotNull("failure - expected not null", entity);
        Assert.assertEquals("failure - expected id attribute match", id,
                entity.getId());

    }

    @Test(expected = Exception.class)
    public void testFindByIdNotFound() throws Exception {

        Long id = Long.MAX_VALUE;

        User entity = service.findById(id);
    }

    @Test
    public void testCreate() {

        Long id = null;
        int encryptedPasswordLength = 60;
        Long count = service.getUsersCount();

        User entity = new User();
        entity.setPassword("password");
        entity.setEmail("test@gmail.com");
        entity.setUsername("username");

        User createdEntity = service.create(entity, id);

        Assert.assertNotNull("failure - expected not null", createdEntity);
        Assert.assertNotNull("failure - expected id attribute not null",
                createdEntity.getId());
        Assert.assertEquals("failure - expected text attribute match", "test@gmail.com",
                createdEntity.getEmail());
        Assert.assertEquals("failure - expected text attribute match", "username",
                createdEntity.getUsername());
        Assert.assertEquals("failure - expected list size", encryptedPasswordLength, createdEntity.getPassword().length());
        Assert.assertEquals("failure - expected text attribute match", "USER",
                createdEntity.getRole().getName());

        Collection<User> list = service.findAll();

        Assert.assertEquals("failure - expected size", count + 1, list.size());

    }

    @Test
    public void testCreateWithRoleId() {

        Long id = 1L;

        User entity = new User();
        entity.setPassword("password");
        entity.setEmail("test@gmail.com");
        entity.setUsername("username");

        Role role = roleRepository.findOne(id);
        User createdEntity = service.create(entity, id);
        Assert.assertEquals("failure - expected text attribute match", role.getName(),
                createdEntity.getRole().getName());

    }

//    @Test
//    public void testUpdate() {
//
//
//        Long id = null;
//        int encryptedPasswordLength = 60;
//        Long count = service.getUsersCount();
//
//        User entity = new User();
//        entity.setPassword("password");
//        entity.setEmail("test@gmail.com");
//        entity.setUsername("username");
//
//        User createdEntity = service.create(entity, id);
//
//        Assert.assertNotNull("failure - expected not null", createdEntity);
//        Assert.assertNotNull("failure - expected id attribute not null",
//                createdEntity.getId());
//        Assert.assertEquals("failure - expected text attribute match", "test@gmail.com",
//                createdEntity.getEmail());
//        Assert.assertEquals("failure - expected text attribute match", "username",
//                createdEntity.getUsername());
//        Assert.assertEquals("failure - expected list size", encryptedPasswordLength, createdEntity.getPassword().length());
//        Assert.assertEquals("failure - expected text attribute match", "USER",
//                createdEntity.getRole().getName());
//
//        Collection<User> list = service.findAll();
//
//        Assert.assertEquals("failure - expected size", count + 1, list.size());
//
//    }
}