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
 * Servlet implementation class UpdateBookServlet
 */
public class UpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBookServlet() {
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
			request.setCharacterEncoding("UTF-8");
			String bookId = (String)request.getParameter("book_id");
			String bookName = (String)request.getParameter("book_name");
			String author = (String)request.getParameter("author");
			String price = (String)request.getParameter("price");
			String updateBookSQL = "update book set book_name='" + bookName + "', author='" + author + "', price=" + price + " where book_id=" + bookId;
			connection = DBConnection.getConnection();
			stmt = connection.createStatement();
			int result = stmt.executeUpdate(updateBookSQL);
			if (result > 0) {
				request.setAttribute("status", "更新成功");
			} else {
				request.setAttribute("status", "更新失败");
			}
			request.getRequestDispatcher("update_result.jsp").forward(request, response);
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
