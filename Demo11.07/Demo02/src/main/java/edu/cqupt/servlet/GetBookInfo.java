package edu.cqupt.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.cqupt.dao.Book;
import edu.cqupt.db.DBConnection;

/**
 * Servlet implementation class GetBookInfo
 */
public class GetBookInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBookInfo() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String bookId = (String)request.getParameter("book_id");
			String queryBookDetailsSQL = "select * from book where book_id=" + bookId;
			connection = DBConnection.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(queryBookDetailsSQL);
			Book book = new Book();
			if (rs.next()) {
				book.setBookId(rs.getInt("book_id"));
				book.setBookName(rs.getString("book_name"));
				book.setAuthor(rs.getString("author"));
				book.setPrice(rs.getFloat("price"));
			}
			request.setAttribute("book", book);
			request.getRequestDispatcher("update_book.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
