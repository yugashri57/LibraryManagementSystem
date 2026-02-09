package com.wipro.book.servlets;
import java.io.IOException;

import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.AuthorDAO;
import com.wipro.book.service.Administrator;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String operation = request.getParameter("operation");

    	if ("AddBook".equals(operation)) {
    	    String result = addbook(request);

    	    if ("SUCCESS".equals(result)) {
    	        response.sendRedirect("Menu.html");
    	    } else if ("INVALID".equals(result)) {
    	        response.sendRedirect("Invalid.html");
    	    } else {
    	        response.sendRedirect("Failure.html");
    	    }
    	}
    	else if ("Search".equals(operation)) {
    	    String isbn = request.getParameter("isbn");
    	    BookBean bb = viewbook(isbn);

    	    if (bb == null) {
    	        response.sendRedirect("Invalid.html");
    	    } else {
    	        HttpSession session = request.getSession();
    	        session.setAttribute("book", bb);
    	        RequestDispatcher rs = request.getRequestDispatcher("ViewServlet");
    	        rs.forward(request, response);
    	    }
    	}
    	else {
    	    response.sendRedirect("Invalid.html");
    	}
    }

    public String addbook(HttpServletRequest request) {

        String isbn = request.getParameter("isbn");
        String bookName = request.getParameter("bookName");
        String bookType = request.getParameter("bookType");
        String authorName = request.getParameter("authorName");
        String cost = request.getParameter("cost");

        BookBean bb = new BookBean();
        bb.setIsbn(isbn);
        bb.setBookName(bookName);
        bb.setBookType(bookType.charAt(0));
        bb.setCost(Float.parseFloat(cost));
        bb.setAuthor(new AuthorDAO().getAuthor(authorName));

        return new Administrator().addBook(bb);
    }

    public BookBean viewbook(String isbn) {
        return new Administrator().viewBook(isbn);
    }
}