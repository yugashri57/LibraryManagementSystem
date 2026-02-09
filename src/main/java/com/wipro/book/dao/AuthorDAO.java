package com.wipro.book.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wipro.book.bean.AuthorBean;
import com.wipro.book.util.DBUtil;

public class AuthorDAO {
	public AuthorBean getAuthor(int authorCode) {
		Connection con=DBUtil.getDBConnection();
		String query="select * from Author_tbl where Author_code=?";
		
		try {
			PreparedStatement pr = con.prepareStatement(query);
			pr.setInt(1, authorCode);
			ResultSet rs=pr.executeQuery();
			if(rs.next()) {
				AuthorBean ab=new AuthorBean();
				ab.setAuthorCode(rs.getInt("author_Code"));
				ab.setAuthorName(rs.getString("author_Name"));
				ab.setContactNo(rs.getLong("contact_No"));
				return ab;
			}
			return null;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}	
	}
	
	public AuthorBean getAuthor(String authorName) {
		Connection con=DBUtil.getDBConnection();
		String query="select * from Author_tbl where Author_name=?";
		
		try {
			PreparedStatement pr = con.prepareStatement(query);
			pr.setString(1, authorName);
			ResultSet rs=pr.executeQuery();
			if(rs.next()) {
				AuthorBean ab=new AuthorBean();
				ab.setAuthorCode(rs.getInt("author_Code"));
				ab.setAuthorName(rs.getString("author_Name"));
				ab.setContactNo(rs.getLong("contact_No"));
				return ab;
			}
			return null;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}	
	}


}
