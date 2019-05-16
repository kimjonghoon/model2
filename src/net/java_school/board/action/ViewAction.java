package net.java_school.board.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.board.Article;
import net.java_school.board.AttachFile;
import net.java_school.board.Board;
import net.java_school.board.BoardService;
import net.java_school.board.Comment;
import net.java_school.commons.NumbersForPaging;
import net.java_school.commons.Paginator;
import net.java_school.commons.WebContants;
import net.java_school.user.UserInfo;

public class ViewAction extends Paginator implements Action  {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();

		HttpSession session = req.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute(WebContants.USER_KEY);

		if (userInfo == null) {
			String url = req.getRequestURI();
			String query = req.getQueryString();
			if (query != null) url += "?" + query;
			url = URLEncoder.encode(url, "UTF-8");
			forward.setView("/users/login.do?url=" + url);
			forward.setRedirect(true);

			return forward;
		}

		int articleNo = Integer.parseInt(req.getParameter("articleNo"));
		String boardCd = req.getParameter("boardCd");
		int page = Integer.parseInt(req.getParameter("page"));
		String searchWord = req.getParameter("searchWord");

		BoardService service = new BoardService();

		int numPerPage = 20;
		int pagePerBlock = 10;

		int totalRecord = service.getTotalRecord(boardCd, searchWord);

		NumbersForPaging numbers = this.getNumbersForPaging(totalRecord, page, numPerPage, pagePerBlock);

		//articleNo, user'ip, yearMonthDayHour
		String ip = req.getRemoteAddr();
		LocalDateTime now = LocalDateTime.now();
		Integer year = now.getYear();
		Integer month = now.getMonthValue();
		Integer day = now.getDayOfMonth();
		Integer hour = now.getHour();
		String yearMonthDayHour = year.toString() + month.toString() + day.toString() + hour.toString();

		service.increaseHit(articleNo, ip, yearMonthDayHour);

		Article article = service.getArticle(articleNo);
		List<AttachFile> attachFileList = service.getAttachFileList(articleNo);
		Article nextArticle = service.getNextArticle(articleNo, boardCd, searchWord);
		Article prevArticle = service.getPrevArticle(articleNo, boardCd, searchWord);

		int startRecord = (page - 1) * numPerPage + 1;
		int endRecord = page * numPerPage;
		List<Article> list = service.getArticleList(boardCd, searchWord, startRecord, endRecord);
		List<Comment> commentList = service.getCommentList(articleNo);
		String boardNm = service.getBoardNm(boardCd);
		List<Board> boards = service.getBoards();

		String title = article.getTitle();
		String content = article.getContent();
		content = content.replaceAll(WebContants.LINE_SEPARATOR, "<br />");
		int hit = service.getTotalViews(articleNo);
		String name = article.getName();
		String email = article.getEmail();
		Date regdate = article.getRegdate();
		String uploadPath = "/upload/";

		int listItemNo = numbers.getListItemNo();
		int prevPage = numbers.getPrevBlock();
		int firstPage = numbers.getFirstPage();
		int lastPage = numbers.getLastPage();
		int nextPage = numbers.getNextBlock();
		int totalPage = numbers.getTotalPage();

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
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("boardNm", boardNm);
		req.setAttribute("boards", boards);

		forward.setView("/bbs/view.jsp");

		return forward;
	}

}
