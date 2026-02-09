package com.wipro.book.servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.wipro.book.bean.BookBean;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session =request.getSession();
		BookBean bookBean =(BookBean)session.getAttribute("book");
		
		out.print("<html><body>");
		out.print("Book Title:"+bookBean.getBookName());
		out.print("<br>Author Name:"+bookBean.getAuthor().getAuthorName());
		out.print("<br>Author Contact:"+bookBean.getAuthor().getContactNo());
		out.print("<br>Book Price"+bookBean.getCost());
		out.print("<br>Book ISBN:"+bookBean.getIsbn());
		out.print("</html></body>");		
	}

}
