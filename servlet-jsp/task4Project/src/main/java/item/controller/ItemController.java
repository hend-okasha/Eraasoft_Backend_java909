package item.controller;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//http://localhost:8080/ItemService/ItemConroller?action=
//http://localhost:8080/ItemService/ItemConroller?action=abc
//http://localhost:8080/ItemService/ItemConroller?action=add-item
//http://localhost:8080/ItemService/ItemConroller?action=remove-item
//http://localhost:8080/ItemService/ItemConroller?action=update-item
//http://localhost:8080/ItemService/ItemConroller?action=show-item
//http://localhost:8080/ItemService/ItemConroller?action=show-items
@WebServlet("/ItemController")
public class ItemController extends HttpServlet {
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(Objects.isNull(action)) {
			showItems(request,response);
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
			response.getWriter().append("<h1> SHOW ITEMS </h1>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void showItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().append("<h1> SHOW ITEM </h1>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void updateItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().append("<h1> update Item </h1>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void removeItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().append("<h1> remove Item </h1>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void addItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().append("<h1> add Item </h1>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	

}
