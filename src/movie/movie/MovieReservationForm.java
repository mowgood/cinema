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

import movie.board.NoticeBoardBean;
import util.DBUtil;
import util.StringUtil;

public class MovieReservationForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<ScheduleBean> list = null;
		ScheduleBean bean = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String output = "";

		StringBuffer sql = null;

		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			
			String mtitle = StringUtil.nvl(request.getParameter("MTITLE"));

			list = new ArrayList<ScheduleBean>();

			conn = DBUtil.getConnection();
			sql = new StringBuffer();

			sql.setLength(0);
			sql.append("\n SELECT *     		 ");
			sql.append("\n   FROM SCHEDULE 		 ");
			sql.append("\n   WHERE SCMVTITLE = ? ");
			output = sql.toString();
			output = output.replaceFirst("[?]", String.format("'%s'", mtitle));
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 1;
			pstmt.setString(idx++, mtitle);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean = new ScheduleBean();
				bean.setScNum(rs.getInt("SCNUM"));
				bean.setScMVTITLE(rs.getString("SCMVTITLE"));
				bean.setScMVTIME(rs.getString("SCMVTIME"));
				bean.setScMVDATE(rs.getString("SCMVDATE"));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}

		request.setAttribute("scheduleList", list);
		RequestDispatcher rd = request.getRequestDispatcher("/movie/reservationForm.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
