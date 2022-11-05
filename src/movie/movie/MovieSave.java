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

public class MovieSave extends HttpServlet {
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
			String mcode = StringUtil.nvl(request.getParameter("MCODE"));
			String mtitle = StringUtil.nvl(request.getParameter("MTITLE"));
			String mdirector = StringUtil.nvl(request.getParameter("MDIRECTOR"));
			String mcompany = StringUtil.nvl(request.getParameter("MCOMPANY"));
			String mactor = StringUtil.nvl(request.getParameter("MACTOR"));
			String mcontents = StringUtil.nvl(request.getParameter("MCONTENTS"));
			String mimage = StringUtil.nvl(request.getParameter("MIMAGE"));

			conn = DBUtil.getConnection();
			sql = new StringBuffer();
			sql.append("\n INSERT INTO MOVIE (MCODE             ");
			sql.append("\n                        , MTITLE      ");
			sql.append("\n                        , MDIRECTOR   ");
			sql.append("\n                        , MCOMPANY    ");
			sql.append("\n                        , MACTOR      ");
			sql.append("\n                        , MCONTENTS   ");
			sql.append("\n                        , MIMAGE      ");
			sql.append("\n                         ) VALUES (   ");
			sql.append("\n                          ?           "); // MCODE
			sql.append("\n                        , ?           "); // MTITLE
			sql.append("\n                        , ?           "); // MDIRECTOR
			sql.append("\n                        , ?           "); // MCOMPANY
			sql.append("\n                        , ?           "); // MACTOR
			sql.append("\n                        , ?           "); // MCONTENTS
			sql.append("\n                        , ?           "); // MIMAGE
			sql.append("\n                         )            ");
			output = sql.toString();
			output = output.replaceFirst("[?]", String.format("'%s'", mcode));
			output = output.replaceFirst("[?]", String.format("'%s'", mtitle));
			output = output.replaceFirst("[?]", String.format("'%s'", mdirector));
			output = output.replaceFirst("[?]", String.format("'%s'", mcompany));
			output = output.replaceFirst("[?]", String.format("'%s'", mactor));
			output = output.replaceFirst("[?]", String.format("'%s'", mcontents));
			output = output.replaceFirst("[?]", String.format("'%s'", mimage));
			System.out.println(output);
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 1;
			pstmt.setString(idx++, id);
			pstmt.setString(idx++, mcode);
			pstmt.setString(idx++, mtitle);
			pstmt.setString(idx++, mdirector);
			pstmt.setString(idx++, mcompany);
			pstmt.setString(idx++, mactor);
			pstmt.setString(idx++, mcontents);
			pstmt.setString(idx++, mimage);
			result = pstmt.executeUpdate();
			if (result == 0) {
				out = response.getWriter();
				out.print("<html>                                   ");
				out.print("<head>                                   ");
				out.print("    <script>                             ");
				out.print("    alert('저장에 실패했습니다.');             ");
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

		response.sendRedirect("/CinemaPlus/movie/list.do");
	}
}
