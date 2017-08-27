package net.java_school.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BoardDao {
	private Log log = LogFactory.getLog(BoardDao.class);
	private static BoardDao instance = new BoardDao();
	private DataSource ds;
	public static String NEW_LINE = System.getProperty("line.separator");

	public static BoardDao getInstance() {
		return instance;
	}

	private BoardDao() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/jsppjt");
		} catch (NamingException e) {
			if (log.isDebugEnabled()) {
				log.debug(e.getMessage());
			}
		}
	}

	private Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	private void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//특정 페이지에 해당하는  게시글 리스트
	public ArrayList<Article> selectListOfArticles(
			String boardCd, 
			String searchWord, 
			int startRownum, 
			int endRownum) {

		ArrayList<Article> articleList = new ArrayList<Article>();

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT articleno, title, regdate, hit, attachfileNum, commentNum FROM ");
		sb.append("( ");
		sb.append("     SELECT rownum R,A.* FROM ");
		sb.append("     ( ");
		sb.append("     SELECT ");
		sb.append("         a.articleno,"); 
		sb.append("         a.title,");
		sb.append("         a.regdate,");
		sb.append("         COUNT(DISTINCT(v.no)) hit,");
		sb.append("         COUNT(DISTINCT(f.attachfileno)) attachfileNum,");
		sb.append("         COUNT(DISTINCT(c.commentno)) commentNum ");
		sb.append("     FROM ");
		sb.append("         article a left join attachfile f on a.articleno = f.articleno ");
		sb.append("         left join comments c on a.articleno = c.articleno ");
		sb.append("         left join views v on a.articleno = v.articleno "); 
		sb.append("     WHERE ");
		sb.append("         a.boardcd = ? ");
		if (searchWord != null && !searchWord.equals("")) {
			sb.append("         AND (title LIKE '%" + searchWord + "%' OR DBMS_LOB.INSTR(content, '%" + searchWord + "%') > 0) ");
		}
		sb.append("     GROUP BY a.articleno, a.title, a.regdate ORDER BY articleno DESC ");
		sb.append("     ) A ");
		sb.append(") ");
		sb.append("WHERE R BETWEEN ? AND ?");

		final String sql = sb.toString();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardCd);
			pstmt.setInt(2, startRownum);
			pstmt.setInt(3, endRownum);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Article article = new Article();
				int articleNo = rs.getInt("articleno");
				String title = rs.getString("title");
				Date regdate = rs.getDate("regdate");
				int hit = rs.getInt("hit");
				int attachfileNum = rs.getInt("attachfileNum");
				int commentNum = rs.getInt("commentNum");
				article.setArticleNo(articleNo);
				article.setTitle(title);
				article.setRegdate(regdate);
				article.setHit(hit);
				article.setAttachFileNum(attachfileNum);
				article.setCommentNum(commentNum);
				articleList.add(article);
			}
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			close(rs, pstmt, con);
		}

		return articleList;
	}

	//총게시글수
	public int selectCountOfArticles(String boardCd, String searchWord) {
		int totalRecord = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT count(*) FROM article WHERE boardcd=? ";
		if (searchWord != null && !searchWord.equals("")) {
			sql = sql + "AND (title LIKE '%" + searchWord + "%' OR content LIKE '%" + searchWord + "%') ";
		} 
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardCd);
			rs = pstmt.executeQuery();
			rs.next();
			totalRecord = rs.getInt(1);
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			close(rs, pstmt, con);
		}

		return totalRecord;
	}

	//게시글 삽입
	public void insert(Article article, AttachFile attachFile) {
		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO article " +
				"(articleno, boardcd, title, content, email, regdate) " +
				"VALUES " +
				"(SEQ_ARTICLE.nextval, ?, ?, ?, ?, sysdate)";

		int chk = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getBoardCd());
			pstmt.setString(2, article.getTitle());
			pstmt.setString(3, article.getContent());
			pstmt.setString(4, article.getEmail());
			chk = pstmt.executeUpdate();
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		try {
			if (chk > 0 && attachFile != null) {
				sql = "INSERT INTO attachfile " +
						"(attachfileno, filename, filetype, filesize, articleno, email) " +
						"VALUES " +
						"(SEQ_ATTACHFILE.nextval, ?, ?, ?, SEQ_ARTICLE.currval, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, attachFile.getFilename());
				pstmt.setString(2, attachFile.getFiletype());
				pstmt.setLong(3, attachFile.getFilesize());
				pstmt.setString(4, attachFile.getEmail());
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			close(null, pstmt, con);
		}

	}

	//게시글 수정
	public void update(Article article, AttachFile attachFile) {
		String sql = "UPDATE article SET title=?, content=? WHERE articleno=?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			pstmt.setInt(3, article.getArticleNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		try {
			if (attachFile != null) {
				sql = "INSERT INTO attachfile "
						+ "(attachfileno, filename, filetype, filesize, articleno, email) "
						+ "VALUES (SEQ_ATTACHFILE.nextval, ?, ?, ?, ?, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, attachFile.getFilename());
				pstmt.setString(2, attachFile.getFiletype());
				pstmt.setLong(3, attachFile.getFilesize());
				pstmt.setInt(4, attachFile.getArticleNo());
				pstmt.setString(5, attachFile.getEmail());
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			close(null, pstmt, con);
		}

	}

	//게시글 삭제
	public void delete(int articleNo) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;

		String sql = "DELETE FROM comments WHERE articleno = ?";

		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setInt(1, articleNo);
			pstmt1.executeUpdate();

			sql = "DELETE FROM attachfile WHERE articleno = ?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, articleNo);
			pstmt2.executeUpdate();

			sql = "DELETE FROM article WHERE articleno = ?";
			pstmt3 = con.prepareStatement(sql);
			pstmt3.setInt(1, articleNo);
			pstmt3.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}

			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (pstmt1 != null) {
				try {
					pstmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt2 != null) {
				try {
					pstmt2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			close(null, pstmt3, con);
		}   

	}

	//P.K 에 해당하는 게시글 조회
	public Article selectOne(int articleNo) {
		Article article = null;
		final String sql = "SELECT "
				+ "articleno, title, content, a.email, NVL(name,'Anonymous') name, regdate "
				+ "FROM "
				+ "article a left join member m on a.email = m.email "
				+ "WHERE "
				+ "articleno = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new Article();
				article.setArticleNo(rs.getInt("articleno"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setEmail(rs.getString("email"));
				article.setName(rs.getString("name"));
				article.setRegdate(rs.getDate("regdate"));
			}
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			close(rs, pstmt, con);
		}
		return article;

	}

	//P.K 에 해당하는 게시글의 다음글 조회
	public Article selectNextOne(int articleNo, String boardCd, String searchWord) {
		Article article = null;
		String sql = "SELECT articleno, title " +
				"FROM " +
				"(SELECT rownum r,a.* " +
				"FROM " +
				"(SELECT articleno, title "
				+ "FROM article "
				+ "WHERE boardCd = ? AND articleno > ? ";

		if (searchWord != null && !searchWord.equals("")) {
			sql = sql + "AND (title like '%" + searchWord + "%' "
					+ "OR content like '%" + searchWord + "%') ";
		} 

		sql = sql + "ORDER BY articleno) a) WHERE r = 1";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardCd);
			pstmt.setInt(2, articleNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new Article();
				article.setArticleNo(rs.getInt("articleno"));
				article.setTitle(rs.getString("title"));
			}
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			close(rs, pstmt, con);
		}

		return article;

	}

	//P.K 에 해당하는 게시글의 이전글 조회
	public Article selectPrevOne(int articleNo, String boardCd, String searchWord) {
		Article article = null;
		String sql = "SELECT articleno, title " +
				"FROM " +
				"(SELECT rownum r,a.* " +
				"FROM " +
				"(SELECT articleno, title "
				+ "FROM article "
				+ "WHERE boardCd = ? AND articleno < ? ";

		if (searchWord != null && !searchWord.equals("")) {
			sql = sql + "AND (title like '%" + searchWord + "%' "
					+ "OR content5 like '%" + searchWord + "%') ";
		} 

		sql = sql + "ORDER BY articleno DESC) a) WHERE r = 1";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardCd);
			pstmt.setInt(2, articleNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new Article();
				article.setArticleNo(rs.getInt("articleno"));
				article.setTitle(rs.getString("title"));
			}
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			close(rs, pstmt, con);
		}

		return article;

	}

	//첨부파일 리스트 조회
	public ArrayList<AttachFile> selectListOfAttachFiles(int articleNo) {
		ArrayList<AttachFile> attachFileList = new ArrayList<AttachFile>();
		String sql = "SELECT attachfileno, filename, filetype, filesize, articleno, email " +
				"FROM attachfile WHERE articleno=? ORDER BY attachfileno ASC";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				AttachFile attachFile = new AttachFile();
				int attachFileNo = rs.getInt("attachfileno");
				String filename = rs.getString("filename");
				String filetype = rs.getString("filetype");
				long filesize = rs.getLong("filesize");
				String email = rs.getString("email");
				attachFile.setAttachFileNo(attachFileNo);
				attachFile.setFilename(filename);
				attachFile.setFiletype(filetype);
				attachFile.setFilesize(filesize);
				attachFile.setEmail(email);
				attachFileList.add(attachFile);
			}
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			close(rs, pstmt, con);  
		}

		return attachFileList;

	}

	//첨부파일 삭제
	public void deleteFile(int attachFileNo) {
		String sql = "DELETE FROM attachfile WHERE attachfileno=?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, attachFileNo);
			pstmt.executeQuery();
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			close(null, pstmt, con);    
		}

	}

	//게시판 코드로 게시판 이름 조회
	public String selectOneBoardName(String boardCd) {
		String boardNm = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT boardNm FROM board WHERE boardcd = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardCd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				boardNm = rs.getString("boardnm");
			}
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			close(rs, pstmt, con);
		}

		return boardNm;

	}

	//댓글삽입
	public void insertComment(Comment comment) {
		String sql = "INSERT INTO comments (commentno, articleno, email, memo, regdate) "
				+ "VALUES (SEQ_COMMENTS.nextval, ?, ?, ?, sysdate)";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment.getArticleNo());
			pstmt.setString(2, comment.getEmail());
			pstmt.setString(3, comment.getMemo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			close(null, pstmt, con);
		}

	}

	//댓글수정
	public void updateComment(Comment comment) {
		String sql = "UPDATE comments SET memo = ? WHERE commentno = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, comment.getMemo());
			pstmt.setInt(2, comment.getCommentNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			close(null, pstmt, con);
		}
	}

	//댓글삭제
	public void deleteComment(int commentNo) {
		String sql = "DELETE FROM comments WHERE commentno = ?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commentNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			close(null, pstmt, con);
		}

	}

	//댓글리스트 조회
	public ArrayList<Comment> selectListOfComments(int articleNo) {
		ArrayList<Comment> commentList = new ArrayList<Comment>();

		String sql = "SELECT "
				+ "commentno, articleno, c.email, NVL(name,'Anonymous') name, memo, regdate "
				+ "FROM "
				+ "comments c, member m "
				+ "WHERE "
				+ "c.email = m.email(+) AND "
				+ "articleno = ? "
				+ "ORDER BY commentno DESC"; 

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Comment comment = new Comment();

				comment.setCommentNo(rs.getInt("commentno"));
				comment.setArticleNo(rs.getInt("articleno"));
				comment.setEmail(rs.getString("email"));
				comment.setName(rs.getString("name"));
				comment.setMemo(rs.getString("memo"));
				comment.setRegdate(rs.getTimestamp("regdate"));

				commentList.add(comment);
			}
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			close(null, pstmt, con);
		}

		return commentList;
	}

	//P.K로 첨부파일 찾기
	public AttachFile selectOneAttachFile(int attachFileNo) {
		AttachFile attachFile = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//attachfileno,filename,filetype,filesize,articleno,email
		String sql = "SELECT attachfileno, filename, filetype, filesize, articleno, email "
				+ "FROM attachFile WHERE attachfileno = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, attachFileNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				attachFile = new AttachFile();
				attachFile.setAttachFileNo(rs.getInt("attachfileno"));
				attachFile.setFilename(rs.getString("filename"));
				attachFile.setFiletype(rs.getString("filetype"));
				attachFile.setFilesize(rs.getLong("filesize"));
				attachFile.setArticleNo(rs.getInt("articleno"));
				attachFile.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			close(rs, pstmt, con);
		}

		return attachFile;
	}

	//P.K로 댓글 찾기
	public Comment selectOneComment(int commentNo) {
		Comment comment = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//commentno,articleno,email,memo,regdate
		String sql = "SELECT commentno, articleno, email, memo, regdate "
				+ "FROM comments WHERE commentno = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commentNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				comment = new Comment();
				comment.setCommentNo(rs.getInt("commentno"));
				comment.setArticleNo(rs.getInt("articleno"));
				comment.setEmail(rs.getString("email"));
				comment.setMemo(rs.getString("memo"));
				comment.setRegdate(rs.getDate("regdate"));
			}
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			close(rs, pstmt, con);
		}

		return comment;
	}
	
	//조회수 증가
	public void insertOneViews(int aritcleNo, String ip, String yearMonthDayHour) {
		String sql = "INSERT INTO views values (seq_views.nextval, ?, ?, ?)";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, aritcleNo);
			pstmt.setString(2, ip);
			pstmt.setString(3, yearMonthDayHour);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			close(null, pstmt, con);
		}
	}
	
	//조회수
	public int selectCountOfViews(int articleNo) {
		String sql = "SELECT count(*) FROM views WHERE articleNo = ?";
		
		int totalRecord = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			rs.next();
			totalRecord = rs.getInt(1);
		} catch (SQLException e) {
			if (log.isDebugEnabled()) {
				StringBuilder msg = new StringBuilder();
				msg.append("SQLState : " + e.getSQLState() + NEW_LINE);
				msg.append("Message : " + e.getMessage() + NEW_LINE);
				msg.append("Oracle Error Code : " + e.getErrorCode() + NEW_LINE);
				msg.append("sql : " + sql + NEW_LINE);
				log.debug(msg);
			}
		} finally {
			close(rs, pstmt, con);
		}

		return totalRecord;

	}

}


