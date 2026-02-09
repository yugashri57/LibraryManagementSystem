package com.wipro.book.service;

import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.BookDAO;

public class Administrator {
	public String addBook(BookBean bookBean) {
		if(bookBean==null||bookBean.getIsbn().isEmpty()||bookBean.getBookName().isEmpty()||bookBean.getBookType() == ' '||(bookBean.getBookType() != 'G' && bookBean.getBookType() != 'T')||
				bookBean.getCost()==0||bookBean.getAuthor().getAuthorName().isEmpty()) {
			return "INVALID";
		}
		else {
				int add=new BookDAO().createBook(bookBean);
				if(add>0) {
					return "SUCCESS";
				}
		}
		return "FAILURE";
	}
	public BookBean viewBook(String isbn) {
		if(isbn.isEmpty()) {
			return null;
		}
		BookBean book=new BookBean();
		book=new BookDAO().fetchBook(isbn);
		if(book==null) 
			return null;
		
		return book;//return new BookDAO.fetchBook(isbn);
	}

}