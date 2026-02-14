package item.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import item.model.Item;
import item.service.ItemService;

public class ItemServiceImpl implements ItemService {

	private DataSource dataSource;
	
	public ItemServiceImpl(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	 
	@Override
	public List<Item> getItems() {
		Connection connection= null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from item where deleted= 0";
			preparedStatement = connection.prepareStatement(query);
			
			ResultSet resultSet= preparedStatement.executeQuery(query);
			
			List<Item> items = new ArrayList<Item>();
			while(resultSet.next()){
				
				Item item = new Item(
						resultSet.getLong("id"),
						resultSet.getString("name"),
						resultSet.getDouble("price"),
						resultSet.getInt("total_number")
						);
				items.add(item);
			}
			
			return items;
			 
		}catch(Exception exception){
			System.out.println("exception =>" + exception.getMessage());
		}finally {
			
			try {
				if(Objects.nonNull(connection)) {
					connection.close();
				}
				
				if(Objects.nonNull(preparedStatement)) {
					preparedStatement.close();
				}
				
			} catch (SQLException exception) {
				
				System.out.println("exception" + exception.getMessage());
			}
		}
		
		return null;
	}

	@Override
	public Item getItem(Long id) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from item where id = ? and deleted = 0";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery(query);
			resultSet.next();
			
			Item item = new Item(
					resultSet.getLong("id"),
					resultSet.getString("name"),
					resultSet.getDouble("price"),
					resultSet.getInt("total_number")
					);
			return item;
			
		}catch(Exception exception) {
			System.out.println("exception" + exception.getMessage());
		}finally {
			try {
				if(Objects.nonNull(connection)) {
					connection.close();
				}
				
				if(Objects.nonNull(preparedStatement)) {
					preparedStatement.close();
				}
				
			} catch (SQLException exception) {
				
				System.out.println("exception" + exception.getMessage());
			}
		}
		
		return null;
	}

	@Override
	public Boolean createItem(Item item) {
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "insert into item (name, price, total_number) values(?, ?, ?)";
			
			preparedstatement = connection.prepareStatement(query);
			
			preparedstatement.setString(1, item.getName());
			preparedstatement.setDouble(2, item.getPrice());
			preparedstatement.setInt(3, item.getTotalNumber());
			
			preparedstatement.executeUpdate();
			
			return true;
			
		}catch(Exception exception) {
			System.out.println("exception" + exception.getMessage());
		}finally {
			try {
				if(Objects.nonNull(connection)) {
					connection.close();
				}
				
				if(Objects.nonNull(preparedstatement)) {
					preparedstatement.close();
				}
				
			} catch (SQLException exception) {
				
				System.out.println("exception" + exception.getMessage());
			}
		}
		
		return false;
	}

	@Override
	public Boolean updateItem(Item item) {
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		
		try {
			connection = dataSource.getConnection();

			String query = "update item set name = ?, price = ?, total_number = ? WHERE id = ?";
			
			preparedstatement = connection.prepareStatement(query);
			
			preparedstatement.setString(1, item.getName());
			preparedstatement.setDouble(2, item.getPrice());
			preparedstatement.setInt(3, item.getTotalNumber());
			preparedstatement.setLong(4, item.getId());
			
			preparedstatement.executeUpdate();
			
			return true;
			
		}catch(Exception exception) {
			System.out.println("exception" + exception.getMessage());
		}finally {
			try {
				if(Objects.nonNull(connection)) {
					connection.close();
				}
				
				if(Objects.nonNull(preparedstatement)) {
					preparedstatement.close();
				}
				
			} catch (SQLException exception) {
				
				System.out.println("exception" + exception.getMessage());
			}
		}
		
		return false;
	}

	@Override
	public Boolean removeItem(Long id) {
		
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "UPDATE item SET deleted = 1 WHERE id = ?";
			preparedstatement = connection.prepareStatement(query);
			preparedstatement.setLong(1, id);
			
			int rowsUpdated = preparedstatement.executeUpdate();

			return rowsUpdated > 0;
			
		}catch(Exception exception) {
			System.out.println("exception" + exception.getMessage());
		}finally {
			try {
				if(Objects.nonNull(connection)) {
					connection.close();
				}
				
				if(Objects.nonNull(preparedstatement)) {
					preparedstatement.close();
				}
				
			} catch (SQLException exception) {
				
				System.out.println("exception" + exception.getMessage());
			}
		}
		
		return false;
	}

}
