package item.service.impl;

import item.service.ItemDetailsService;
import java.sql.*;
import java.util.Objects;
import javax.sql.DataSource;
import item.model.ItemDetails;


public class ItemDetailsServiceImpl implements ItemDetailsService {
	
	private DataSource dataSource;

    public ItemDetailsServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ItemDetails getItemDetailsByItemId(Long itemId) {
    	
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = dataSource.getConnection();
            String query = "SELECT * FROM item_details WHERE item_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, itemId);
            ResultSet resultSet  = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                return new ItemDetails(
                		resultSet.getLong("id"),
                		resultSet.getLong("item_id"),
                		resultSet.getString("description"),
                		resultSet.getDate("issue_date"),
                		resultSet.getDate("expiry_date")
                );
            }
        } catch (Exception exception) {
        	
        	System.out.println("exception" + exception.getMessage());
            
        } finally {
            closeResources(connection, preparedStatement);
        }
        return null;
    }

    @Override
    public Boolean createItemDetails(ItemDetails itemDetails) {
    	
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
        	
            connection = dataSource.getConnection();
            String query = "INSERT INTO item_details (description, issue_date, expiry_date, item_id) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, itemDetails.getDescription());
            preparedStatement.setDate(2, new java.sql.Date(itemDetails.getIssueDate().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(itemDetails.getExpiryDate().getTime()));
            preparedStatement.setLong(4, itemDetails.getItemId());
            preparedStatement.executeUpdate();
            return true;
            
        } catch (Exception exception) {
        	
        	System.out.println("exception" + exception.getMessage());
            
        } finally {
        	
            closeResources(connection, preparedStatement);
        }
        return false;
    }

    @Override
    public Boolean deleteItemDetails(Long itemId) {
    	
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
        	
            connection = dataSource.getConnection();
            String query = "DELETE FROM item_details WHERE item_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, itemId);
            return preparedStatement.executeUpdate() > 0;
            
        } catch (Exception exception) {
        	
        	System.out.println("exception" + exception.getMessage());
        	
        } finally {
        	
            closeResources(connection, preparedStatement);
            
        }
        return false;
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
