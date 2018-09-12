package com.example.WebTask.service;

import com.example.WebTask.WebTaskApplicationTests;
import com.example.WebTask.modules.role.Role;
import com.example.WebTask.modules.role.RoleRepository;
import com.example.WebTask.modules.user.User;
import com.example.WebTask.modules.user.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Transactional
public class UserServiceTest extends WebTaskApplicationTests {

    @Autowired
    @InjectMocks
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

        Assert.assertNotNull("failure - expected not null", list);
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
        service.findById(id);
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

    @Test
    public void testUpdateAsAdmin() throws Exception {

        Mockito.when(principal.getName()).thenReturn("ajax");
        Long id = 3L;
        int encryptedPasswordLength = 60;
        long count = service.getUsersCount();


        User entity = new User();
        entity.setPassword("password");
        entity.setEmail("georgi1@gmail.com");
        entity.setUsername("Georgi1");
        entity.setRole(roleRepository.findOne(1L));

        User createdEntity = service.update(entity, id, principal);

        Assert.assertNotNull("failure - expected not null", createdEntity);
        Assert.assertNotNull("failure - expected id attribute not null",
                createdEntity.getId());
        Assert.assertEquals("failure - expected text attribute match", "georgi1@gmail.com",
                createdEntity.getEmail());
        Assert.assertEquals("failure - expected text attribute match", "Georgi1",
                createdEntity.getUsername());
        Assert.assertEquals("failure - expected list size", encryptedPasswordLength, createdEntity.getPassword().length());
        Assert.assertEquals("failure - expected text attribute match", "ADMIN",
                createdEntity.getRole().getName());

        List<User> list = service.findAll();

        Assert.assertNotNull(list);
        Assert.assertEquals("failure - expected list size", count, list.size());
    }

    @Test
    public void testUpdateSelfAsUser() throws Exception {

        Mockito.when(principal.getName()).thenReturn("Georgi");
        Long id = 3L;
        int encryptedPasswordLength = 60;
        long count = service.getUsersCount();

        User entity = new User();
        entity.setPassword("password");
        entity.setEmail("georgi1@gmail.com");
        entity.setUsername("Georgi1");
        entity.setRole(roleRepository.findOne(1L));

        User createdEntity = service.update(entity, id, principal);

        Assert.assertNotNull("failure - expected not null", createdEntity);
        Assert.assertNotNull("failure - expected id attribute not null",
                createdEntity.getId());
        Assert.assertEquals("failure - expected text attribute match", "georgi1@gmail.com",
                createdEntity.getEmail());
        Assert.assertEquals("failure - expected text attribute match", "Georgi1",
                createdEntity.getUsername());
        Assert.assertEquals("failure - expected list size", encryptedPasswordLength, createdEntity.getPassword().length());

        // Users of type USER cannot alter their role only admins can
        Assert.assertEquals("failure - expected text attribute match", "USER",
                createdEntity.getRole().getName());

        List<User> list = service.findAll();

        Assert.assertNotNull(list);
        Assert.assertEquals("failure - expected list size", count, list.size());
    }

    @Test(expected = Exception.class)
    public void testUpdateAnotherAsUser() throws Exception {

        Mockito.when(principal.getName()).thenReturn("Georgi");
        Long id = 2L;

        User entity = new User();
        entity.setPassword("password");
        entity.setEmail("georgi1@gmail.com");
        entity.setUsername("Georgi1");
        entity.setRole(roleRepository.findOne(1L));

        service.update(entity, id, principal);
    }

    @Test
    public void delete() throws Exception {


        Long id = 3L;

        long count = service.getUsersCount();


        User deletedEntity = service.delete(id);

        Assert.assertNotNull("failure - expected not null", deletedEntity);

        Assert.assertEquals("failure - expected text attribute match", "georgi@gmail.com",
                deletedEntity.getEmail());
        Assert.assertEquals("failure - expected text attribute match", "Georgi",
                deletedEntity.getUsername());


        List<User> list = service.findAll();

        Assert.assertNotNull(list);
        Assert.assertEquals("failure - expected list size", count - 1, list.size());
    }

    @Test(expected = Exception.class)
    public void deleteNotFound() throws Exception {

        Long id = Long.MAX_VALUE;
        service.delete(id);
    }

    @Test
    public void getUsersPageable() throws Exception {

        int pages = 1;
        int elementPerPage = 5;
        String searchString = "";
        long count = service.getUsersCount();

        Page<User> page = service.getUsersPage(pages, elementPerPage, searchString);

        Assert.assertNotNull("failure - expected not null", page);
        Assert.assertEquals("failure - expected list size", count, page.getTotalElements());
        Assert.assertEquals("failure - expected total pages", (int) Math.ceil((double) count/elementPerPage), page.getTotalPages());
    }

    @Test
    public void getUsersPageableWithSearch() throws Exception {
        int pages = 1;
        int elementPerPage = 5;
        String searchString = "an";
        long count = service.getUsersCount();

        Page<User> page = service.getUsersPage(pages, elementPerPage, searchString);

        Assert.assertNotNull("failure - expected not null", page);
        Assert.assertNotEquals("failure - expected list size", count, page.getTotalElements());
    }

    @Test
    public void usernameExists() throws Exception {
        long id = 2;
        String username = service.findById(1L).getUsername();

        Assert.assertTrue(service.usernameExists(username, id));
    }

    @Test
    public void usernameExistsExceptSelf() throws Exception {
        long id = 1;
        String username = service.findById(1L).getUsername();

        Assert.assertFalse(service.usernameExists(username, id));
    }

    @Test
    public void emailExists() throws Exception {
        long id = 2;
        String email = service.findById(1L).getEmail();

        Assert.assertTrue(service.emailExists(email, id));
    }

    @Test
    public void emailExistsExceptSelf() throws Exception {
        long id = 1;
        String email = service.findById(1L).getEmail();

        Assert.assertFalse(service.emailExists(email, id));
    }

    @Test
    public void findByUsername() throws Exception {
        User entity = service.findById(3L);
        User userFound = service.findByUsername(entity.getUsername());
        Assert.assertNotNull("failure - expected not null", userFound);
    }

    @Test
    public void findByUsernameNotFound() throws Exception {

        User userFound = service.findByUsername("asd1212fg");
        Assert.assertNull(userFound);
    }
}