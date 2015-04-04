package net.java_school.board.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.board.Article;
import net.java_school.board.AttachFile;
import net.java_school.board.BoardService;
import net.java_school.board.Comment;
import net.java_school.commons.PagingHelper;
import net.java_school.commons.WebContants;
import net.java_school.user.User;

public class ViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		
		ActionForward forward = new ActionForward();
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute(WebContants.USER_KEY);
		
		if (user == null) {
			String url = req.getRequestURI();
			String query = req.getQueryString();
			if (query != null) url += "?" + query;
			url = URLEncoder.encode(url, "UTF-8");
			String contextPath = req.getContextPath();
			forward.setView(contextPath + "/users/login.do?url=" + url);
			forward.setRedirect(true);
			
			return forward;
		}
		
		int articleNo = Integer.parseInt(req.getParameter("articleNo"));
		String boardCd = req.getParameter("boardCd");
		int curPage = Integer.parseInt(req.getParameter("curPage"));
		String searchWord = req.getParameter("searchWord");
		
		BoardService service = new BoardService();
		
		int totalRecord = service.getTotalRecord(boardCd, searchWord);
		int numPerPage = 10;
		int pagePerBlock = 10;
		PagingHelper pagingHelper = 
				new PagingHelper(totalRecord, curPage, numPerPage, pagePerBlock);
		
		service.setPagingHelper(pagingHelper);
		
		service.increaseHit(articleNo);
		
		Article article = service.getArticle(articleNo);
		ArrayList<AttachFile> attachFileList = service.getAttachFileList(articleNo);
		Article nextArticle = service.getNextArticle(articleNo, boardCd, searchWord);
		Article prevArticle = service.getPrevArticle(articleNo, boardCd, searchWord);
		ArrayList<Article> list = service.getArticleList(boardCd, searchWord);
		ArrayList<Comment> commentList = service.getCommentList(articleNo);
		String boardNm = service.getBoardNm(boardCd);
		
		String title = article.getTitle();
		String content = article.getContent();
		content = content.replaceAll(WebContants.LINE_SEPARATOR, "<br />");
		int hit = article.getHit();
		String name = article.getName();
		String email = article.getEmail();
		Date regdate = article.getRegdate();
		String contextPath = req.getContextPath();
		String uploadPath = contextPath + "/upload/";
		
		int listItemNo = service.getListItemNo();
		int prevPage = service.getPrevPage();
		int firstPage = service.getFirstPage();
		int lastPage = service.getLastPage();
		int nextPage = service.getNextPage();
		
		req.setAttribute("title", title);
		req.setAttribute("content", content);
		req.setAttribute("hit", hit);
		req.setAttribute("name", name);
		req.setAttribute("email", email);
		req.setAttribute("regdate", regdate);
		req.setAttribute("uploadPath", uploadPath);
		req.setAttribute("attachFileList", attachFileList);
		req.setAttribute("nextArticle", nextArticle);
		req.setAttribute("prevArticle", prevArticle);
		req.setAttribute("commentList", commentList);
		
		req.setAttribute("list", list);
		req.setAttribute("listItemNo", listItemNo);
		req.setAttribute("prevPage", prevPage);
		req.setAttribute("firstPage", firstPage);
		req.setAttribute("lastPage", lastPage);
		req.setAttribute("nextPage", nextPage);
		req.setAttribute("boardNm", boardNm);

		forward.setView("/bbs/view.jsp");
		
		return forward;
	}

}
