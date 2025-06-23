package org.example.util;

import org.example.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Утилитный класс для настройки и предоставления {@link SessionFactory} Hibernate.
 * <p>
 * Используется для получения сессий для работы с базой данных.
 */
public class HibernateUtil {
    /**
     * Единственный экземпляр {@link SessionFactory}, инициализируется при загрузке класса.
     */
    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Создаёт и настраивает {@link SessionFactory} с использованием конфигурационного файла Hibernate
     * и регистрирует аннотированные сущности.
     *
     * @return сконфигурированная {@link SessionFactory}
     * @throws ExceptionInInitializerError если происходит ошибка во время инициализации
     */
    private static SessionFactory buildSessionFactory() {
        try {
            Configuration cfg = new Configuration().configure();
            cfg.addAnnotatedClass(User.class);
            return cfg.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Возвращает статический экземпляр {@link SessionFactory}.
     *
     * @return объект {@link SessionFactory}
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
