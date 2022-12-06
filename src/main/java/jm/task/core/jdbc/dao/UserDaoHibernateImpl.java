package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl extends SessionUtil implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        //open session with a transaction
        openTransactionSession();

        String sql = "CREATE TABLE USER (" +
                "`ID` BIGINT NOT NULL AUTO_INCREMENT," +
                "`NAME` VARCHAR(255) NOT NULL," +
                "`LASTNAME` VARCHAR(255) NOT NULL," +
                "`AGE` int NOT NULL," +
                "PRIMARY KEY (`ID`))";

        Session session = getSession();
        //Query query = session.createSQLQuery(sql);
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        query.executeUpdate();
        closeTransactionSession();

    }

    @Override
    public void dropUsersTable() {
        openTransactionSession();

        String sql = "DROP TABLE IF EXISTS USER";

        Session session = getSession();
        //Query query = session.createSQLQuery(sql);
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        query.executeUpdate();
        //close session with a transaction
        closeTransactionSession();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        //open session with a transaction
        openTransactionSession();

        String sql = "INSERT INTO USER (NAME, LASTNAME, AGE) VALUES(:name,:lastname,:age)";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        query.setParameter("name", name);
        query.setParameter("lastname", lastName);
        query.setParameter("age", age);
        query.executeUpdate();
        System.out.println("User with name " + name + " add to database");
        //close session with a transaction
        closeTransactionSession();

    }

    @Override
    public void removeUserById(long id) {
        //open session with a transaction
        openTransactionSession();

        String sql = "DELETE FROM USER WHERE ID=:id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        query.setParameter("id", id);
        query.executeUpdate();
        //close session with a transaction
        closeTransactionSession();

    }

    @Override
    public List<User> getAllUsers() {

        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM USER";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        List<User> userList = query.list();

        //close session with a transaction
        closeTransactionSession();
        for(User user : userList) {
            System.out.println(user.toString());
        }

        return userList;
    }

    @Override
    public void cleanUsersTable() {
        openTransactionSession();

        String sql = "DELETE FROM USER";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        query.executeUpdate();
        //close session with a transaction
        closeTransactionSession();

    }
}
