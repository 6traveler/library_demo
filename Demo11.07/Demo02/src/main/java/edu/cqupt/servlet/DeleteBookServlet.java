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
 * Servlet implementation class DeleteBookServlet
 */
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBookServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = null;
		Statement stmt = null;
		try {
			String bookId = request.getParameter("book_id");
			String deleteBookSQL = "delete from book where book_id=" + bookId;
			connection = DBConnection.getConnection();
			stmt = connection.createStatement();
			int result = stmt.executeUpdate(deleteBookSQL);
			if (result > 0) {
				response.sendRedirect("list_all_book");
			} else {
				request.setAttribute("error", "删除失败，图书不存在");
				request.getRequestDispatcher("list_all_book").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "删除失败：" + e.getMessage());
			response.sendRedirect("list_all_book");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

