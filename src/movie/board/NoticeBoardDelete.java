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

public class NoticeBoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// GET 방식으로 넘어오면 얼럿을 띄어주고 그 전 페이지로 돌아간다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setLocale(new Locale("ko_KR"));
		response.setContentType("text/html; charset=utf-8");
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
			// 파라미터로 넘어오는 작성자 ID
			String noticeId = StringUtil.nvl(request.getParameter("noticeId"));
			// 세션에 담겨있는 로그인 ID
			String sessionId = StringUtil.nvl((String) session.getAttribute("id"));
			System.out.printf("noticeId = %s, sessionId = %s\n", noticeId, sessionId);

			// 파라미터로 넘어오는 작성자 ID와 세션에 담겨있는 ID가 같을 때랑 관리자일때
			if (noticeId.equals(sessionId) || sessionId.equals("admin")) {
				sql = new StringBuffer();
				sql.append("\n DELETE FROM NOTICEBOARD ");
				sql.append("\n  WHERE NOTICE_NUM = ?   ");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, noticeNum);
				result = pstmt.executeUpdate();
				if (result == 0) {
					out = response.getWriter();
					out.print("<html>                                   ");
					out.print("<head>                                   ");
					out.print("    <script>                             ");
					out.print("    alert('게시물 삭제에 실패했습니다.');");
					out.print("    history.go(-1);                      ");
					out.print("    </script>                            ");
					out.print("</head>                                  ");
					out.print("<body>                                   ");
					out.print("</body>                                  ");
					out.print("</html>                                  ");
					out.flush();
					out.close();
				}
			} else {
				// 같지 않으면 해당 스크립트를 수행
				out = response.getWriter();
				out.print("<html>                                 ");
				out.print("<head>                                 ");
				out.print("    <script>                           ");
				out.print("    alert('작성자만 삭제 가능합니다.');");
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

		// 리스트로 돌아간다.
		response.sendRedirect("/CinemaPlus/board/list.do");
	}
}
