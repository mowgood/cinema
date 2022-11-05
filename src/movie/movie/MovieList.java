package movie.movie;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.movie.MovieBean;
import util.DBUtil;
import util.StringUtil;

public class MovieList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<MovieBean> list = null;
		MovieBean bean = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sql = null;

		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");

			list = new ArrayList<MovieBean>();

			conn = DBUtil.getConnection();
			sql = new StringBuffer();

			sql.setLength(0);
			sql.append("\n SELECT *     ");
			sql.append("\n   FROM MOVIE ");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean = new MovieBean();
				bean.setMNUM(rs.getInt("MNUM"));
				bean.setMCODE(rs.getString("MCODE"));
				bean.setMTITLE(rs.getString("MTITLE"));
				bean.setMDIRECTOR(rs.getString("MDIRECTOR"));
				bean.setMCOMPANY(rs.getString("MCOMPANY"));
				bean.setMACTOR(rs.getString("MACTOR"));
				bean.setMCONTENTS(rs.getString("MCONTENTS"));
				bean.setMIMAGE(rs.getString("MIMAGE"));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}

		request.setAttribute("movieList", list);
		RequestDispatcher rd = request.getRequestDispatcher("/movie/movieList.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
