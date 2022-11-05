package movie.board;

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

import util.DBUtil;
import util.StringUtil;

public class NoticeBoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<NoticeBoardBean> list = null;
		NoticeBoardBean bean = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sql = null;

		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			
			int pageNo = Integer.parseInt(StringUtil.nvl(request.getParameter("pageNo"), "1"));
			int pageSize = 10;
			int start = (pageNo - 1) * pageSize;
			request.setAttribute("pageNo", pageNo);

			list = new ArrayList<NoticeBoardBean>();

			conn = DBUtil.getConnection();
			sql = new StringBuffer();
			sql.append("\n SELECT COUNT(*) FROM NOTICEBOARD ");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				request.setAttribute("totalRows", (int) Math.ceil(rs.getDouble(1) / pageSize));
			}

			rs.close();
			pstmt.close();

			sql.setLength(0);
			sql.append("\n SELECT NOTICE.*                                                           ");
			sql.append("\n   FROM (SELECT @ROWNUM := @ROWNUM + 1                 AS RNUM             ");
			sql.append("\n              , A.NOTICE_NUM                           AS NOTICE_NUM       ");
			sql.append("\n              , A.NOTICE_ID                            AS NOTICE_ID        ");
			sql.append("\n              , A.NOTICE_SUBJECT                       AS NOTICE_SUBJECT   ");
			sql.append("\n              , A.NOTICE_READCOUNT                     AS NOTICE_READCOUNT ");
			sql.append("\n              , DATE_FORMAT(A.NOTICE_DATE, '%Y-%m-%d') AS NOTICE_DATE      ");
			sql.append("\n              , B.UNAME                                AS USERNAME         ");
			sql.append("\n           FROM NOTICEBOARD A                                              ");
			sql.append("\n              , MEMBER B                                                   ");
			sql.append("\n              , (SELECT @ROWNUM := 0) C                                    ");
			sql.append("\n          WHERE A.NOTICE_ID = B.ID                                         ");
			sql.append("\n         ORDER BY NOTICE_NUM ASC                                           ");
			sql.append("\n        ) NOTICE                                                           ");
			sql.append("\n ORDER BY NOTICE.RNUM DESC                                                 ");
			sql.append("\n LIMIT ?, ?                                                                ");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 1;
			pstmt.setInt(idx++, start);
			pstmt.setInt(idx++, pageSize);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean = new NoticeBoardBean();
				bean.setRownum(rs.getInt("RNUM"));
				bean.setNoticeNum(rs.getInt("NOTICE_NUM"));
				bean.setNoticeId(rs.getString("NOTICE_ID"));
				bean.setNoticeSubject(rs.getString("NOTICE_SUBJECT"));
				bean.setReadCount(rs.getInt("NOTICE_READCOUNT"));
				bean.setNoticeDate(rs.getString("NOTICE_DATE"));
				bean.setUserName(rs.getString("USERNAME"));
				System.out.println("NOTICE_NUM = " + bean.getNoticeNum());
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}

		request.setAttribute("noticeList", list);
		RequestDispatcher rd = request.getRequestDispatcher("/board/noticeList.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
