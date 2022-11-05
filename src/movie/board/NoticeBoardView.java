package movie.board;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBUtil;
import util.StringUtil;

public class NoticeBoardView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		NoticeBoardBean bean = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sql = null;

		try {
			request.setCharacterEncoding("utf-8");
			response.setLocale(new Locale("ko_KR"));
			response.setContentType("text/html; charset=utf-8");

			bean = new NoticeBoardBean();

			conn = DBUtil.getConnection();

			int noticeNum = Integer.parseInt(StringUtil.nvl(request.getParameter("noticeNum"), "0"));
			System.out.println("NOTICE_NUM = " + noticeNum);
			bean.setNoticeNum(noticeNum);

			sql = new StringBuffer();
			sql.append("\n UPDATE NOTICEBOARD                             ");
			sql.append("\n    SET NOTICE_READCOUNT = NOTICE_READCOUNT + 1 ");
			sql.append("\n  WHERE NOTICE_NUM = ?                          ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, noticeNum);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				pstmt.close();

				sql.setLength(0);
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
				if(rs.next()) {
					
					bean.setNoticeId(rs.getString("NOTICE_ID"));
					bean.setNoticeSubject(rs.getString("NOTICE_SUBJECT"));
					bean.setNoticeContent(rs.getString("NOTICE_CONTENT"));
					bean.setReadCount(rs.getInt("NOTICE_READCOUNT"));
					bean.setUserName(rs.getString("UNAME"));
				}
			}
		} catch (Exception e) {
			System.out.println(sql.toString());
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}

		request.setAttribute("notice", bean);
		RequestDispatcher rd = request.getRequestDispatcher("/board/noticeView.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
