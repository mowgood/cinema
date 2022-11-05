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
import javax.servlet.http.HttpSession;

import movie.movie.ReservationBean;
import util.DBUtil;
import util.StringUtil;

public class MovieReservationList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<ReservationBean> list = null;
		ReservationBean bean = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sql = null;
		
		HttpSession session = request.getSession();
		
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");		
			
			String sessionId = StringUtil.nvl((String) session.getAttribute("id"));

			list = new ArrayList<ReservationBean>();

			conn = DBUtil.getConnection();
			sql = new StringBuffer();

			sql.setLength(0);
			sql.append("\n SELECT *              ");
			sql.append("\n   FROM RESERVATION    ");
			sql.append("\n   WHERE RESID = ?     ");
			sql.append(sessionId);
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 1;
			pstmt.setString(idx++, sessionId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean = new ReservationBean();
				bean.setResIdx(rs.getInt("RESIDX"));
				bean.setResId(rs.getString("RESID"));
				bean.setResTitle(rs.getString("RESTITLE"));
				bean.setResTime(rs.getString("RESTIME"));
				bean.setResDate(rs.getString("RESDATE"));
				bean.setResAmount(rs.getInt("RESAMOUNT"));
				bean.setResSeat(rs.getString("RESSEAT"));				
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}

		request.setAttribute("reservationList", list);
		RequestDispatcher rd = request.getRequestDispatcher("/movie/reservationList.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
