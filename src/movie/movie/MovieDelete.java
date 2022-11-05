package movie.movie;

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

public class MovieDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// GET ������� �Ѿ���� ���� ����ְ� �� �� �������� ���ư���.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setLocale(new Locale("ko_KR"));
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<html>                          ");
		out.print("<head>                          ");
		out.print("    <script>                    ");
		out.print("    alert('�߸��� �����Դϴ�.');      ");
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

			// �Ķ���ͷ� �Ѿ���� �⺻Ű
			int MovieNum = Integer.parseInt(StringUtil.nvl(request.getParameter("MNUM"), "0"));
			// ���ǿ� ����ִ� �α��� ID
			String sessionId = StringUtil.nvl((String) session.getAttribute("id"));

			// �Ķ���ͷ� �Ѿ���� ID�� �������϶�
			if (sessionId.equals("admin")) {
				sql = new StringBuffer();
				sql.append("\n DELETE FROM MOVIE ");
				sql.append("\n  WHERE MNUM = ?   ");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, MovieNum);
				result = pstmt.executeUpdate();
				if (result == 0) {
					out = response.getWriter();
					out.print("<html>                                   ");
					out.print("<head>                                   ");
					out.print("    <script>                             ");
					out.print("    alert('��ȭ ������ �����߽��ϴ�.');          ");
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
				// ���� ������ �ش� ��ũ��Ʈ�� ����
				out = response.getWriter();
				out.print("<html>                                 ");
				out.print("<head>                                 ");
				out.print("    <script>                           ");
				out.print("    alert('�����ڸ� ���� �����մϴ�.');        ");
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

		// ����Ʈ�� ���ư���.
		response.sendRedirect("/CinemaPlus/movie/list.do");
	}
}
