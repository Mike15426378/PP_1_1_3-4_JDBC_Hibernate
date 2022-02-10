package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class Util {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Kalmik1994";
    private static SessionFactory sessionFactory = null;

    public SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            configuration.setProperty("hibernate.connection.driver", DB_DRIVER);
            configuration.setProperty("hibernate.connection.username", DB_USER);
            configuration.setProperty("hibernate.connection.password", DB_PASSWORD);
            configuration.setProperty("hibernate.connection.url", DB_URL);
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            System.out.println("Исключение " + e);
        }

        return sessionFactory;
    }
    // реализуйте настройку соеденения с БД
}
