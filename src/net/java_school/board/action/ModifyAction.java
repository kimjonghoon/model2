package net.java_school.board.action;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

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
import net.java_school.user.User;

public class ModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute(WebContants.USER_KEY);
		
		ServletContext servletContext = req.getServletContext();
		String dir = servletContext.getRealPath("/upload");
		
		MultipartRequest multi = new MultipartRequest(req,dir,5*1024*1024,"UTF-8",new DefaultFileRenamePolicy());
		
		BoardService service = new BoardService();
		int articleNo = Integer.parseInt(multi.getParameter("articleNo"));
		
		if (!service.getArticle(articleNo).getEmail().equals(user.getEmail())) {
			throw new AuthenticationException(WebContants.AUTHENTICATION_FAILED);
		}
		
		String boardCd = multi.getParameter("boardCd");
		int page = Integer.parseInt(multi.getParameter("page"));
		String searchWord = multi.getParameter("searchWord");
		searchWord = URLEncoder.encode(searchWord, "UTF-8");
		
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
			attachFile.setEmail(user.getEmail());
			attachFile.setArticleNo(articleNo);
		}
		
		Article article = new Article();
		article.setArticleNo(articleNo);
		article.setBoardCd(boardCd);
		article.setTitle(title);
		article.setContent(content);
		article.setEmail(user.getEmail());
		
		service.modifyArticle(article, attachFile);
		
		forward.setView("view.do?articleNo=" + articleNo + "&boardCd=" + boardCd + "&page=" + page + "&searchWord=" + searchWord);
		forward.setRedirect(true);
		
		return forward;
	}

}
