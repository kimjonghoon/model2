package net.java_school.board.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.board.Article;
import net.java_school.board.AttachFile;
import net.java_school.board.BoardService;
import net.java_school.commons.WebContants;
import net.java_school.exception.AuthenticationException;
import net.java_school.user.UserInfo;

public class WriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();

		HttpSession session = req.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute(WebContants.USER_KEY);

		if (userInfo == null) {
			throw new AuthenticationException(WebContants.NOT_LOGIN);
		}

		ServletContext servletContext = req.getServletContext();
		String dir = servletContext.getRealPath("/upload");

		MultipartRequest multi = new MultipartRequest(req,dir,5*1024*1024,"UTF-8",new DefaultFileRenamePolicy());

		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String filename = multi.getFilesystemName("attachFile");
		String filetype = multi.getContentType("attachFile");

		File f = multi.getFile("attachFile");
		long filesize = 0L;
		AttachFile attachFile = null;

		if (f != null) {
			filesize = f.length();
			attachFile = new AttachFile();
			attachFile.setFilename(filename);
			attachFile.setFiletype(filetype);
			attachFile.setFilesize(filesize);
			attachFile.setEmail(userInfo.getUser().getEmail());
		}

		String boardCd = multi.getParameter("boardCd");

		Article article = new Article();
		article.setEmail(userInfo.getUser().getEmail());
		article.setTitle(title);
		article.setContent(content);
		article.setBoardCd(boardCd);

		BoardService service = new BoardService();
		service.addArticle(article, attachFile);

		forward.setView("list.do?boardCd=" + boardCd + "&page=1");
		forward.setRedirect(true);

		return forward;
	}

}
