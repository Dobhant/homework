package org.example.dao;

import org.example.model.User;
import java.util.List;

/**
 * Реализация интерфейса {@link UserDao} с использованием Hibernate.
 * <p>
 * Обеспечивает доступ к данным сущности {@link User}.
 */
public interface UserDao {

    /**
     * Сохраняет нового пользователя в базе данных.
     *
     * @param user объект {@link User}, который нужно сохранить
     */
    void save(User user);

    /**
     * Ищет пользователя по его ID.
     *
     * @param id идентификатор пользователя
     *
     * @return {@link User}, если найден, иначе {@code null}
     */
    User findById(Long id);

    /**
     * Возвращает список всех пользователей из базы данных.
     *
     * @return список объектов {@link User}
     */
    List<User> findAll();

    /**
     * Обновляет существующего пользователя в базе данных.
     *
     * @param user объект {@link User} с обновлёнными данными
     */
    void update(User user);

    /**
     * Удаляет пользователя из базы данных.
     *
     * @param user объект {@link User}, который нужно удалить
     */
    void delete(User user);
}
