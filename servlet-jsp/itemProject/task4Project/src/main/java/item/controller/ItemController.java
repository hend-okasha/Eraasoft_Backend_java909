package item.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import item.model.Item;
import item.model.ItemDetails;
import item.service.ItemDetailsService;
import item.service.ItemService;
import item.service.impl.ItemDetailsServiceImpl;
import item.service.impl.ItemServiceImpl;

//http://localhost:9090/ItemService/ItemController?action=
//http://localhost:9090/ItemService/ItemController?action=abc
//http://localhost:9090/ItemService/ItemController?action=add-item&name=&price=&totalnumber=
//http://localhost:9090/ItemService/ItemController?action=remove-item&id=
//http://localhost:9090/ItemService/ItemController?action=update-item&id=&name=&price=&totalnumber=
//http://localhost:9090/ItemService/ItemController?action=show-item
//http://localhost:9090/ItemService/ItemController?action=show-items
@WebServlet("/ItemController")
public class ItemController extends HttpServlet {
	
	 @Resource(name = "jdbc/connection")
	    private DataSource dataSource;

	    private ItemService itemService;
	    private ItemDetailsService itemDetailsService;

	    @Override
	    public void init() {
	        itemService = new ItemServiceImpl(dataSource);
	        itemDetailsService = new ItemDetailsServiceImpl(dataSource);
	    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(Objects.isNull(action)) {
			action="show-items";
		}
		
		switch(action) {
			case "add-item" :
					addItem(request,response);
				break;
			case "remove-item" :
				  	removeItem(request,response);
				break;
			case "update-item" :
					updateItem(request,response);
				break;
			case "show-item" :
					showItem(request,response);
				break;
			case "show-items" :
					showItems(request,response);
				break;
			case "show-add-details":
			    showAddDetails(request, response);
			    break;
			case "add-item-details":
			    addItemDetails(request, response);
			    break;
			case "delete-item-details":
			    deleteItemDetails(request, response);
			    break;
			default:
				showItems(request,response);
				break;
	
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	
	
	private void showItems(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        List<Item> items = itemService.getItems();
	        List<ItemDetails> allDetails = new ArrayList<>();

	        for (Item item : items) {
	            ItemDetails details = itemDetailsService.getItemDetailsByItemId(item.getId());
	            if (details != null) {
	                allDetails.add(details);
	            }
	        }

	        request.setAttribute("allItems", items);
	        request.setAttribute("allDetails", allDetails);
	        request.getRequestDispatcher("/item/show-items.jsp").forward(request, response);
	    } catch (Exception exception) {
	    	System.out.println("exception =>" + exception.getMessage());
	    }
	}


	private void showItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			Long id = Long.parseLong( request.getParameter("id"));
			Item item = itemService.getItemById(id);
			
			request.setAttribute("item", item);
			request.getRequestDispatcher("/item/update-item.jsp").forward(request, response);
			
		} catch (Exception exception) {
			System.out.println("exception =>" + exception.getMessage());
			
		}
		
	}


	private void updateItem(HttpServletRequest request, HttpServletResponse response) {
	    try {


	        Long id = Long.parseLong(request.getParameter("id"));
	        String name = request.getParameter("name");
	        Double price = Double.parseDouble(request.getParameter("price"));
	        Integer totalNumber = Integer.parseInt(request.getParameter("totalNumber"));

	        
	        Item existingItem = itemService.getItemByName(name);

	        if (existingItem != null && !existingItem.getId().equals(id)) {

	            request.setAttribute("message", "Item name already exists in the system");
	            request.setAttribute("messageType", "error");

	            request.setAttribute("item", itemService.getItemById(id));

	            request.getRequestDispatcher("/item/update-item.jsp").forward(request, response);
	            return;
	        }

	        
	        Item oldItem = itemService.getItemById(id);

	       
	        if (oldItem.getName().equals(name)
	                && oldItem.getPrice().equals(price)
	                && oldItem.getTotalNumber().equals(totalNumber)) {

	            request.getSession().setAttribute("message", "No changes were made");
	            response.sendRedirect(request.getContextPath() + "/ItemController");
	            return;
	        }

	        
	        Item item = new Item(id, name, price, totalNumber);
	        Boolean isItemUpdated = itemService.updateItem(item);

	        if (isItemUpdated) {
	            request.getSession().setAttribute("message", "Item updated successfully");
	        } else {
	            request.getSession().setAttribute("message", "Update failed");
	        }

	        response.sendRedirect(request.getContextPath() + "/ItemController");

	    } catch (Exception exception) {
	        exception.printStackTrace();
	    }
	}



	private void removeItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			Long id = Long.parseLong(request.getParameter("id"));
			
			Boolean isItemRemoved = itemService.removeItem(id);
			
			if(isItemRemoved) {
				request.getSession().setAttribute("message", "Item deleted successfully");
				response.sendRedirect(request.getContextPath() + "/ItemController");

			}
		} catch (Exception exception) {
			
			System.out.println("exception =>" + exception.getMessage());
			
		}
		
	}


	private void addItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			String name = request.getParameter("name");
			Double price= Double.parseDouble(request.getParameter("price"));
			Integer totalNumber = Integer.parseInt(request.getParameter("totalNumber"));
			
			  Item existingItem = itemService.getItemByName(name);
		        if (existingItem != null) {
		            request.setAttribute("message", "Item name already exists in the system");
		            request.setAttribute("messageType", "error");
		            request.getRequestDispatcher("/item/add-item.jsp").forward(request, response);
		            return;
		        }
		        
		        Item item = new Item(name, price, totalNumber);
		        Boolean isItemCreated = itemService.createItem(item);
		        
		        if (isItemCreated) {
		        	request.getSession().setAttribute("message", "Item added successfully");
		        	response.sendRedirect(request.getContextPath() + "/ItemController");
		        }
		} catch (Exception exception) {
			
			System.out.println("exception =>" + exception.getMessage());
			
		}
	}
	
	private void showAddDetails(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        Long itemId = Long.parseLong(request.getParameter("itemId"));
	        request.setAttribute("itemId", itemId);
	        request.getRequestDispatcher("/item/add-item-details.jsp").forward(request, response);
	    } catch (Exception exception) {
	    	System.out.println("exception =>" + exception.getMessage());
	    }
	}

	private void addItemDetails(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        Long itemId = Long.parseLong(request.getParameter("itemId"));
	        String description = request.getParameter("description");
	        Date issueDate = Date.valueOf(request.getParameter("issueDate"));
	        Date expiryDate = Date.valueOf(request.getParameter("expiryDate"));

	        ItemDetails itemDetails = new ItemDetails(itemId, description, issueDate, expiryDate);
	        Boolean isCreated = itemDetailsService.createItemDetails(itemDetails);

	        if (isCreated) {
	            request.getSession().setAttribute("message", "Item details added successfully");
	            response.sendRedirect(request.getContextPath() + "/ItemController");
	        }
	    } catch (Exception exception) {
	    	System.out.println("exception =>" + exception.getMessage());
	    }
	}

	private void deleteItemDetails(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        Long itemId = Long.parseLong(request.getParameter("itemId"));
	        Boolean isDeleted = itemDetailsService.deleteItemDetails(itemId);

	        if (isDeleted) {
	            request.getSession().setAttribute("message", "Item details deleted successfully");
	            response.sendRedirect(request.getContextPath() + "/ItemController");
	        }
	    } catch (Exception exception) {
	    	System.out.println("exception =>" + exception.getMessage());
	    }
	}


	

}
