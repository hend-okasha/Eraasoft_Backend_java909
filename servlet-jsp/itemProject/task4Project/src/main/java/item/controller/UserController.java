package item.controller;

import java.io.IOException;
import java.util.Objects;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import item.model.User;
import item.service.UserService;
import item.service.impl.UserServiceImpl;

@WebServlet("/UserController")
public class UserController extends HttpServlet {

    @Resource(name = "jdbc/connection")
    private DataSource dataSource;

    private UserService userService;

    @Override
    public void init() {
        userService = new UserServiceImpl(dataSource);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (Objects.isNull(action)) action = "login";

        switch (action) {
            case "login": login(request, response); break;
            case "signup": signup(request, response); break;
            case "logout": logout(request, response); break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.login(email, password);

        if (user != null) {
            
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user);

            
            Cookie cookie = new Cookie("userEmail", email);
            cookie.setMaxAge(60 * 60 * 24); 
            response.addCookie(cookie);

            response.sendRedirect(request.getContextPath() + "/ItemController");
        } else {
            request.setAttribute("message", "Invalid username or password");
            request.getRequestDispatcher("/item/login.jsp").forward(request, response);
        }
    }

    private void signup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

  
        User existingUser = userService.getUserByEmail(email);
        if (existingUser != null) {
            request.setAttribute("message", "Email already exists");
            request.getRequestDispatcher("/item/signup.jsp").forward(request, response);
            return;
        }

        Boolean isCreated = userService.signup(new User(name, email, password));
        if (isCreated) {
            request.setAttribute("message", "Account created successfully! Please login.");
            request.getRequestDispatcher("/item/login.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Signup failed, please try again");
            request.getRequestDispatcher("/item/signup.jsp").forward(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
       
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        
        Cookie cookie = new Cookie("userEmail", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        response.sendRedirect(request.getContextPath() + "/item/login.jsp");
    }
}