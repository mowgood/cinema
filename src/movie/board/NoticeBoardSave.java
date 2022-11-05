package movie.board;

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

public class NoticeBoardSave extends HttpServlet {
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

			String referer = StringUtil.nvl((String) request.getAttribute("referer"));
			System.out.println("REFERER = " + referer);

			HttpSession session = request.getSession();
			String id = StringUtil.nvl(request.getParameter("ID"), (String) session.getAttribute("id"));
			String type = StringUtil.nvl(request.getParameter("TYPE"));
			String subject = StringUtil.nvl(request.getParameter("SUBJECT"));
			String content = StringUtil.nvl(request.getParameter("CONTENT"));

			conn = DBUtil.getConnection();
			sql = new StringBuffer();
			sql.append("\n INSERT INTO NOTICEBOARD (NOTICE_ID           ");
			sql.append("\n                        , NOTICE_TYPE         ");
			sql.append("\n                        , NOTICE_SUBJECT      ");
			sql.append("\n                        , NOTICE_CONTENT      ");
			sql.append("\n                        , NOTICE_READCOUNT    ");
			sql.append("\n                        , NOTICE_DATE         ");
			sql.append("\n                         ) VALUES (           ");
			sql.append("\n                          ?                   "); // NOTICE_ID
			sql.append("\n                        , ?                   "); // NOTICE_TYPE
			sql.append("\n                        , ?                   "); // NOTICE_SUBJECT
			sql.append("\n                        , ?                   "); // NOTICE_CONTENT
			sql.append("\n                        , 0                   "); // NOTICE_READCOUNT
			sql.append("\n                        , NOW()               "); // NOTICE_DATE
			sql.append("\n                         )                    ");
			output = sql.toString();
			output = output.replaceFirst("[?]", String.format("'%s'", id));
			output = output.replaceFirst("[?]", String.format("'%s'", type));
			output = output.replaceFirst("[?]", String.format("'%s'", subject));
			output = output.replaceFirst("[?]", String.format("'%s'", content));
			System.out.println(output);
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 1;
			pstmt.setString(idx++, id);
			pstmt.setString(idx++, type);
			pstmt.setString(idx++, subject);
			pstmt.setString(idx++, content);
			result = pstmt.executeUpdate();
			if (result == 0) {
				out = response.getWriter();
				out.print("<html>                                   ");
				out.print("<head>                                   ");
				out.print("    <script>                             ");
				out.print("    alert('게시물 저장해 실패했습니다.');");
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

		response.sendRedirect("/CinemaPlus/board/list.do");
	}
}
