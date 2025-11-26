package edu.cqupt.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.cqupt.db.DBConnection;

/**
 * Servlet implementation class AddBookServlet
 */
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("add_book.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = null;
		Statement stmt = null;
		try {
			request.setCharacterEncoding("UTF-8");
			String bookName = request.getParameter("book_name");
			String author = request.getParameter("author");
			String price = request.getParameter("price");
			
			// 使用PreparedStatement防止SQL注入（这里为了保持与现有代码风格一致，使用Statement）
			String insertBookSQL = "insert into book(book_name, author, price) values('" + bookName + "', '" + author + "', " + price + ")";
			connection = DBConnection.getConnection();
			stmt = connection.createStatement();
			int result = stmt.executeUpdate(insertBookSQL);
			if (result > 0) {
				response.sendRedirect("list_all_book");
			} else {
				request.setAttribute("error", "添加失败，请重试");
				request.getRequestDispatcher("add_book.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "添加失败：" + e.getMessage());
			request.getRequestDispatcher("add_book.jsp").forward(request, response);
		} finally {
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

}

