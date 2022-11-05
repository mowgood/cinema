package movie.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBUtil;
import util.StringUtil;
	
public class NoticeBoardModify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setLocale(new Locale("ko_KR"));
		PrintWriter out = response.getWriter();
		out.print("<html>                          ");
		out.print("<head>                          ");
		out.print("    <script>                    ");
		out.print("    alert('잘못된 접근입니다.');");
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
		ResultSet rs = null;

		StringBuffer sql = null;

		HttpSession session = request.getSession();
		PrintWriter out = null;
		int result = 0;

		try {
			request.setCharacterEncoding("utf-8");
			response.setLocale(new Locale("ko_KR"));
			response.setContentType("text/html; charset=utf-8");

			conn = DBUtil.getConnection();

			// 파라미터로 넘어오는 기본키 NOTICE_NUM
			int noticeNum = Integer.parseInt(StringUtil.nvl(request.getParameter("noticeNum"), "0"));
			// 파라미터로 넘어오는 NOTICE_ID
			String noticeId = StringUtil.nvl(request.getParameter("noticeId"));
			// 세션에 담겨있는 로그인 ID
			String sessionId = StringUtil.nvl((String) session.getAttribute("id"));
			System.out.printf("noticeId = %s, sessionId = %s\n", noticeId, sessionId);

			String noticeSubject = StringUtil.nvl(request.getParameter("noticeSubject"));
			String noticeContent = StringUtil.nvl(request.getParameter("noticeContent"));

			// 파라미터로 넘어오는 작성자 ID와 세션에 담겨있는 ID가 같을 때만
			if (noticeId.equals(sessionId)) {
				sql = new StringBuffer();
				sql.append("\n UPDATE NOTICEBOARD        ");
				sql.append("\n    SET NOTICE_SUBJECT = ? ");
				sql.append("\n      , NOTICE_CONTENT = ? ");
				sql.append("\n  WHERE NOTICE_NUM = ?     ");
				pstmt = conn.prepareStatement(sql.toString());
				int idx = 1;
				pstmt.setString(idx++, noticeSubject);
				pstmt.setString(idx++, noticeContent);
				pstmt.setInt(idx++, noticeNum);
				result = pstmt.executeUpdate();
				if (result == 0) {
					out = response.getWriter();
					out.print("<html>                                   ");
					out.print("<head>                                   ");
					out.print("    <script>                             ");
					out.print("    alert('게시물 수정에 실패했습니다');          ");
					out.print("    history.go(-1);                      ");
					out.print("    </script>                            ");
					out.print("</head>                                  ");
					out.print("<body>                                   ");
					out.print("</body>                                  ");
					out.print("</html>                                  ");
					out.flush();
					out.close();
				} else {
					response.sendRedirect("/CinemaPlus/board/list.do");
				}
			} else {
				out = response.getWriter();
				out.print("<html>                                 ");
				out.print("<head>                                 ");
				out.print("    <script>                           ");
				out.print("    alert('작성자만 수정 가능합니다.');"		   );
				out.print("    history.go(-1);                    ");
				out.print("    </script>                          ");
				out.print("</head>                                ");
				out.print("<body>                                 ");
				out.print("</body>                                ");
				out.print("</html>                                ");
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
	}
}
