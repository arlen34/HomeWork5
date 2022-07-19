package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final Util util = new Util();
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String sqlCreaate = "CREATE TABLE if not exists users(" +
                "id serial primary key,"+
                "name VARCHAR," +
                "last_name VARCHAR, " +
                "age int)";
        try (Connection connection = util.connection();
            Statement statement = connection.createStatement()){
            statement.executeUpdate(sqlCreaate);
            System.out.println("table created");
        }catch (SQLException e){
            e.getMessage();
            System.out.println("!!!");
        }
    }

    @Override
    public void dropUsersTable() {
        String sqlDrop = "drop table users";
        try (Connection connection = util.connection();
        Statement statement = connection.createStatement()){
            statement.executeUpdate(sqlDrop);
            System.out.println("Table deleted!");
        }catch (SQLException e){
            e.getMessage();
            System.out.println("(exception)\n request failed");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sqlSave = "insert into users (name, last_name, age) VALUES (?,?,?)";
        try (Connection connection = util.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlSave)){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setInt(3,age);
            preparedStatement.executeUpdate();
            System.out.println("Added data!");
        }catch (SQLException e){
            e.getMessage();
            System.out.println("Not added data.");
        }
    }

    @Override
    public void removeUserById(long id) {
        String sqlRemoveById = "Delete from users where id = ?";
        try (Connection connection = util.connection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlRemoveById)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Removed!");
        }catch (SQLException e){
            e.getMessage();
            System.out.println("Not removed!");
        }
    }

    @Override
    public List<User> getAllUsers() {
        User user = new User();
        String sqlGetUsers = "select * from users";
        try (Connection connection = util.connection();
        Statement statement = connection.createStatement()){
             ResultSet resultSet = statement.executeQuery(sqlGetUsers);
             while (resultSet.next()){
                List<User> users = new ArrayList<>();
                users.add(new User(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age")));
                return users;
            }
        }catch (SQLException e){
            e.getMessage();
            System.out.println("Not done");
        }
        return null;
    }


    @Override
    public void cleanUsersTable() {
        String sqlCleanTable = "truncate table users";
        try (Connection connection = util.connection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(sqlCleanTable);
            System.out.println("Cleaned!");
        }catch (SQLException e){
            e.getMessage();
            System.out.println("Not cleaned!");
        }
    }
}
