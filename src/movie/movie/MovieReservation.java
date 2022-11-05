package movie.movie;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBUtil;
import util.StringUtil;

public class MovieReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setLocale(new Locale("ko_KR"));
		PrintWriter out = response.getWriter();
		out.print("<html>                          ");
		out.print("<head>                          ");
		out.print("    <script>                    ");
		out.print("    alert('잘못된 접근입니다.');      ");
		out.print("    history.go(-1);             ");
		out.print("    </script>                   ");
		out.print("</head>                         ");
		out.print("<body>                          ");
		out.print("</body>                         ");
		out.print("</html>                         ");
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		StringBuffer sql = null;
		String output = "";
		int result = 0;

		PrintWriter out = null;
		try {
			request.setCharacterEncoding("utf-8");
			response.setLocale(new Locale("ko_KR"));
			response.setContentType("text/html; charset=utf-8");

			HttpSession session = request.getSession();
			String id = StringUtil.nvl(request.getParameter("ID"), (String) session.getAttribute("id"));
			String restitle = StringUtil.nvl(request.getParameter("RESTITLE"));
			String resdate = StringUtil.nvl(request.getParameter("RESDATE"));
			String restime = StringUtil.nvl(request.getParameter("RESTIME"));
			String resamount = StringUtil.nvl(request.getParameter("RESAMOUNT"));
			String resseat = StringUtil.nvl(request.getParameter("RESSEAT"));

			conn = DBUtil.getConnection();
			sql = new StringBuffer();
			sql.append("\n INSERT INTO RESERVATION (RESID            ");
			sql.append("\n                        , RESTITLE         ");
			sql.append("\n                        , RESTIME          ");
			sql.append("\n                        , RESDATE          ");
			sql.append("\n                        , RESAMOUNT        ");
			sql.append("\n                        , RESSEAT          ");
			sql.append("\n                         ) VALUES (        ");
			sql.append("\n                          ?                "); // 
			sql.append("\n                          ?                ");
			sql.append("\n                        , ?                "); // 
			sql.append("\n                        , ?                "); // 
			sql.append("\n                        , ?                "); // 
			sql.append("\n                        , ?                "); // 
			sql.append("\n                         )                 ");
			output = sql.toString();
			output = output.replaceFirst("[?]", String.format("'%s'", id));
			output = output.replaceFirst("[?]", String.format("'%s'", restitle));
			output = output.replaceFirst("[?]", String.format("'%s'", restime));
			output = output.replaceFirst("[?]", String.format("'%s'", resdate));
			output = output.replaceFirst("[?]", String.format("'%s'", resamount));
			output = output.replaceFirst("[?]", String.format("'%s'", resseat));
			System.out.println(output);
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 1;
			pstmt.setString(idx++, id);
			pstmt.setString(idx++, restitle);
			pstmt.setString(idx++, restime);
			pstmt.setString(idx++, resdate);
			pstmt.setString(idx++, resamount);
			pstmt.setString(idx++, resseat);
			result = pstmt.executeUpdate();
			if (result == 0) {
				out = response.getWriter();
				out.print("<html>                                   ");
				out.print("<head>                                   ");
				out.print("    <script>                             ");
				out.print("    alert('예매에 실패했습니다.');              ");
				out.print("    history.go(-1);                      ");
				out.print("    </script>                            ");
				out.print("</head>                                  ");
				out.print("<body>                                   ");
				out.print("</body>                                  ");
				out.print("</html>                                  ");
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt);
		}

		response.sendRedirect("/CinemaPlus/movie/reservationList.do");
	}
}
