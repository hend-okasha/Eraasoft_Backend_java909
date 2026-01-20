package servlet;

import model.Item;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet("/itemServlet")
public class ItemServlet extends HttpServlet {

    @Resource(name ="jdbc/connection")  
    private DataSource dataSource;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = dataSource.getConnection()) {

            request.setAttribute("allItems", getItems(conn, "SELECT * FROM ITEM"));
            request.setAttribute("byId", getItems(conn, "SELECT * FROM ITEM WHERE ID IN (1,2)"));
            request.setAttribute("byName", getItems(conn, "SELECT * FROM ITEM WHERE LOWER(NAME) LIKE '%i%'"));
            request.setAttribute("byPrice", getItems(conn, "SELECT * FROM ITEM WHERE PRICE > 50 OR PRICE < 20"));

            request.getRequestDispatcher("items.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private ArrayList<Item> getItems(Connection conn, String sql) throws SQLException {
        ArrayList<Item> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Item(rs.getInt("ID"), rs.getString("NAME"), rs.getDouble("PRICE")));
            }
        }
        return list;
    }
}
