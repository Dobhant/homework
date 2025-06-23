package org.example.dao;

import org.example.model.User;
import org.example.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserDaoImplIntegrationTest {

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.3")
            .withDatabaseName("userdb")
            .withUsername("postgres")
            .withPassword("123456");

    private UserDaoImpl userDao;

    @BeforeAll
    void setup() {
        System.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
        System.setProperty("hibernate.connection.username", postgres.getUsername());
        System.setProperty("hibernate.connection.password", postgres.getPassword());

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        userDao = new UserDaoImpl();
    }

    @BeforeEach
    void cleanUp() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var tx = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            tx.commit();
        }
    }

    @Test
    void save_and_findById_should_work() {
        User user = new User();
        user.setName("Алиса");
        user.setEmail("alice@example.com");
        user.setAge(25);

        userDao.save(user);

        User found = userDao.findById(user.getId());
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Алиса");
        assertThat(found.getEmail()).isEqualTo("alice@example.com");
        assertThat(found.getAge()).isEqualTo(25);
    }

    @Test
    void findAll_should_return_all_users() {
        User u1 = new User();
        u1.setName("Егор");
        u1.setEmail("егор@example.com");
        u1.setAge(30);

        User u2 = new User();
        u2.setName("Kent");
        u2.setEmail("kent@example.com");
        u2.setAge(28);

        userDao.save(u1);
        userDao.save(u2);

        List<User> users = userDao.findAll();
        assertThat(users).hasSize(2);
    }

    @Test
    void update_should_modify_user() {
        User user = new User();
        user.setName("Dan");
        user.setEmail("dan@example.com");
        user.setAge(40);

        userDao.save(user);

        user.setName("Daniel");
        user.setEmail("daniel@example.com");
        user.setAge(41);

        userDao.update(user);

        User updated = userDao.findById(user.getId());
        assertThat(updated.getName()).isEqualTo("Daniel");
        assertThat(updated.getEmail()).isEqualTo("daniel@example.com");
        assertThat(updated.getAge()).isEqualTo(41);
    }

    @Test
    void delete_should_remove_user() {
        User user = new User();
        user.setName("Ann");
        user.setEmail("ann@example.com");
        user.setAge(22);

        userDao.save(user);
        Long id = user.getId();

        userDao.delete(user);

        User deleted = userDao.findById(id);
        assertThat(deleted).isNull();
    }
}
