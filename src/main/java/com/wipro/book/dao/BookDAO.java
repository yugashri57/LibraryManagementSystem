package com.wipro.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.wipro.book.bean.BookBean;
import com.wipro.book.util.DBUtil;

public class BookDAO {
	public int createBook(BookBean bookBean) {
		Connection con=DBUtil.getDBConnection();
		String query1="select * from Author_Tbl where Author_Code=?";
		try {
			 PreparedStatement pr = con.prepareStatement(query1);
			 pr.setInt(1, bookBean.getAuthor().getAuthorCode());
			ResultSet rs=pr.executeQuery();
			System.out.println("Author Code = " + bookBean.getAuthor().getAuthorCode());

			if(!rs.next()) {
				String query="insert into Author_Tbl values(?,?,?)";
				PreparedStatement pr1=con.prepareStatement(query);
				pr1.setInt(1,bookBean.getAuthor().getAuthorCode());
				pr1.setString(2,bookBean.getAuthor().getAuthorName());
				pr1.setDouble(3,bookBean.getAuthor().getContactNo());
				pr1.executeUpdate();
			}
	
		String query="insert into Book_Tbl values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, bookBean.getIsbn());
			ps.setString(2,bookBean.getBookName());
			ps.setString(3,String.valueOf(bookBean.getBookType()));
			ps.setInt(4,bookBean.getAuthor().getAuthorCode());
			ps.setFloat(5,bookBean.getCost());
			int isupd=ps.executeUpdate();
			if(isupd>0) {
				return 1;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}
	public BookBean fetchBook(String isbn){
		BookBean bb=null;
		Connection con=DBUtil.getDBConnection();
		String query1="select * from Book_Tbl where Isbn=?";
		try {
			 PreparedStatement pr = con.prepareStatement(query1);
			 pr.setString(1,isbn);
			 ResultSet rs=pr.executeQuery();
			 if(rs.next()) {
				 bb=new BookBean();
				 bb.setIsbn(rs.getString(1));
				 bb.setBookName(rs.getString(2));
				 bb.setBookType(rs.getString(3).charAt(0));
				 bb.setAuthor(new AuthorDAO().getAuthor(rs.getInt(4)));
				 bb.setCost(rs.getFloat(5));
				 return bb; 
			  }
			 }catch (SQLException e) {
					e.printStackTrace();
				}
		return  null;
		}
			

		
		
	}
	

