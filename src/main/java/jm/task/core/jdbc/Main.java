package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    private static final UserService us = new UserServiceImpl();
    private static final User user1 = new User("Ivan", "Ivanov", (byte) 40);
    private static final User user2 = new User("Petr", "Petrov", (byte) 30);
    private static final User user3 = new User("Sanya", "Sankov", (byte) 25);
    private static final User user4 = new User("Vasya", "Vaskin", (byte) 20);

    public static void main(String[] args) {
    us.createUsersTable();

    us.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
    us.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
    us.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
    us.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

    us.getAllUsers();

    us.cleanUsersTable();

    us.dropUsersTable();
    }
}
