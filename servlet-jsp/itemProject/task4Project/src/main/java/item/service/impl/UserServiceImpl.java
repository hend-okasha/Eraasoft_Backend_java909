package item.service.impl;

import java.sql.*;
import java.util.Objects;
import javax.sql.DataSource;
import item.model.User;
import item.service.UserService;

public class UserServiceImpl implements UserService {
	
	private DataSource dataSource;

    public UserServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User login(String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            String query = "SELECT * FROM USERS WHERE email = ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                return user;
            }
        } catch (Exception exception) {
        	System.out.println("exception =>" + exception.getMessage());
        } finally {
            closeResources(connection, preparedStatement);
        }
        return null;
    }

    @Override
    public Boolean signup(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            String query = "INSERT INTO USERS (name, email, password) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception exception) {
        	System.out.println("exception =>" + exception.getMessage());
        } finally {
            closeResources(connection, preparedStatement);
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            String query = "SELECT * FROM USERS WHERE email = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                return user;
            }
        } catch (Exception exception) {
        	
        	System.out.println("exception =>" + exception.getMessage());
        	
        } finally {
        	
            closeResources(connection, preparedStatement);
        }
        return null;
    }

    private void closeResources(Connection connection, PreparedStatement preparedStatement) {
        try {
            if (Objects.nonNull(connection)) connection.close();
            if (Objects.nonNull(preparedStatement)) preparedStatement.close();
        } catch (SQLException exception) {
        	System.out.println("exception" + exception.getMessage());
        }
    }

}
