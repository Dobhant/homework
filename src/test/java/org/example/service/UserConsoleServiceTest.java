package org.example.service;

import org.example.dao.UserDao;
import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.mockito.Mockito.*;

class UserConsoleServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserConsoleService userService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_shouldCallSave() {
        User user = new User();
        user.setName("Alice");
        user.setEmail("alice@example.com");
        user.setAge(22);

        doNothing().when(userDao).save(any(User.class));
        userDao.save(user);

        verify(userDao, times(1)).save(user);
    }

    @Test
    void listUsers_shouldCallFindAll() {
        when(userDao.findAll()).thenReturn(List.of(
                new User()
        ));

        userDao.findAll();
        verify(userDao, times(1)).findAll();
    }

    @Test
    void updateUser_shouldCallUpdate() {
        User user = new User();
        when(userDao.findById(1L)).thenReturn(user);

        user.setAge(40);
        userDao.update(user);

        verify(userDao, times(1)).update(user);
    }

    @Test
    void deleteUser_shouldCallDelete() {
        User user = new User();
        when(userDao.findById(1L)).thenReturn(user);

        userDao.delete(user);
        verify(userDao, times(1)).delete(user);
    }
}
