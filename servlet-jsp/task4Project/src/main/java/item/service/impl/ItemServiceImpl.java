package item.service.impl;

import java.sql.Connection;
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
		Statement statement = null;
		
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			String query = "select * from item";
			ResultSet resultSet= statement.executeQuery(query);
			
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
				
				if(Objects.nonNull(statement)) {
					statement.close();
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
		Statement statement = null;
		
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			String query = "select * from item where id = " + id;
			ResultSet resultSet = statement.executeQuery(query);
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
				
				if(Objects.nonNull(statement)) {
					statement.close();
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
		Statement statement = null;
		
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			String query = "insert into item (name, price, total_number) values('"+
								item.getName()+"'," +item.getPrice()+", "+ item.getTotalNumber()+")";
			statement.execute(query);
			
			return true;
			
		}catch(Exception exception) {
			System.out.println("exception" + exception.getMessage());
		}finally {
			try {
				if(Objects.nonNull(connection)) {
					connection.close();
				}
				
				if(Objects.nonNull(statement)) {
					statement.close();
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
		Statement statement = null;
		
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			String query = "update item set name = '"
					+item.getName()+"', price = "+item.getPrice()+", total_number = "+item.getTotalNumber()+" where id = "+ item.getId()+"";
			
		    statement.execute(query);
			
			return true;
			
		}catch(Exception exception) {
			System.out.println("exception" + exception.getMessage());
		}finally {
			try {
				if(Objects.nonNull(connection)) {
					connection.close();
				}
				
				if(Objects.nonNull(statement)) {
					statement.close();
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
		Statement statement = null;
		
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			String query = "delete from item where id = "+id;
			
		    statement.execute(query);
			
			return true;
			
		}catch(Exception exception) {
			System.out.println("exception" + exception.getMessage());
		}finally {
			try {
				if(Objects.nonNull(connection)) {
					connection.close();
				}
				
				if(Objects.nonNull(statement)) {
					statement.close();
				}
				
			} catch (SQLException exception) {
				
				System.out.println("exception" + exception.getMessage());
			}
		}
		
		return false;
	}

}
