package org.example.dao;

import org.example.model.User;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Реализация интерфейса {@link UserDao} с использованием Hibernate.
 * <p>
 * Обеспечивает доступ к данным сущности {@link User}.
 */
public class UserDaoImpl implements UserDao {

    /**
     * Сохраняет нового пользователя в базе данных.
     *
     * @param user объект {@link User}, который нужно сохранить
     */
    @Override
    public void save(User user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    /**
     * Ищет пользователя по его ID.
     *
     * @param id идентификатор пользователя
     * @return {@link User}, если найден, иначе {@code null}
     */
    @Override
    public User findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(User.class, id);
        }
    }

    /**
     * Возвращает список всех пользователей из базы данных.
     *
     * @return список объектов {@link User}
     */
    @Override
    public List<User> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    /**
     * Обновляет существующего пользователя в базе данных.
     *
     * @param user объект {@link User} с обновлёнными данными
     */
    @Override
    public void update(User user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    /**
     * Удаляет пользователя из базы данных.
     *
     * @param user объект {@link User}, который нужно удалить
     */
    @Override
    public void delete(User user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
