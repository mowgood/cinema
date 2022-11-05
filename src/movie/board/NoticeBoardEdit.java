package movie.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBUtil;
import util.StringUtil;

public class NoticeBoardEdit extends HttpServlet {
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
		NoticeBoardBean bean = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sql = null;

		HttpSession session = request.getSession();
		PrintWriter out = null;

		try {
			request.setCharacterEncoding("utf-8");
			response.setLocale(new Locale("ko_KR"));
			response.setContentType("text/html; charset=utf-8");

			bean = new NoticeBoardBean();

			conn = DBUtil.getConnection();

			// 파라미터로 넘어오는 기본키 NOTICE_NUM
			int noticeNum = Integer.parseInt(StringUtil.nvl(request.getParameter("noticeNum"), "0"));
			System.out.println("EDIT NOTICE_NUM = " + noticeNum);
			bean.setNoticeNum(noticeNum);
			// 파라미터로 넘어오는 작성자 ID
			String noticeId = StringUtil.nvl(request.getParameter("noticeId"));
			// 세션에 담겨있는 로그인 ID
			String sessionId = StringUtil.nvl((String) session.getAttribute("id"));
			System.out.printf("noticeId = %s, sessionId = %s\n", noticeId, sessionId);

			// 파라미터로 넘어오는 작성자 ID와 세션에 담겨있는 ID가 같을 때만
			if (noticeId.equals(sessionId)) {
				sql = new StringBuffer();
				sql.append("\n SELECT A.NOTICE_ID                    ");
				sql.append("\n      , A.NOTICE_SUBJECT               ");
				sql.append("\n      , A.NOTICE_CONTENT               ");
				sql.append("\n      , A.NOTICE_READCOUNT             ");
				sql.append("\n      , B.UNAME                        ");
				sql.append("\n   FROM NOTICEBOARD A                  ");
				sql.append("\n      , MEMBER B                       ");
				sql.append("\n  WHERE A.NOTICE_ID = B.ID             ");
				sql.append("\n    AND A.NOTICE_NUM = ?               ");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, noticeNum);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					bean.setNoticeId(rs.getString("NOTICE_ID"));
					bean.setNoticeSubject(rs.getString("NOTICE_SUBJECT"));
					bean.setNoticeContent(rs.getString("NOTICE_CONTENT"));
					bean.setReadCount(rs.getInt("NOTICE_READCOUNT"));
					bean.setUserName(rs.getString("UNAME"));
				}

				request.setAttribute("notice", bean);
				RequestDispatcher rd = request.getRequestDispatcher("/board/noticeEdit.jsp");
				rd.forward(request, response);
			} else {
				// 같지 않으면 해당 스크립트를 수행
				out = response.getWriter();
				out.print("<html>                                 ");
				out.print("<head>                                 ");
				out.print("    <script>                           ");
				out.print("    alert('작성자만 수정 가능합니다.');");
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
