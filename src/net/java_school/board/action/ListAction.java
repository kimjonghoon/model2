package net.java_school.board.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.board.Article;
import net.java_school.board.BoardService;
import net.java_school.commons.NumbersForPaging;
import net.java_school.commons.Paginator;
import net.java_school.commons.WebContants;
import net.java_school.user.User;

public class ListAction extends Paginator implements Action {

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
		
		String boardCd = req.getParameter("boardCd");
		int page = Integer.parseInt(req.getParameter("page"));
		String searchWord = req.getParameter("searchWord");
		
		BoardService service = new BoardService();
		
		int numPerPage = 20;
		int pagePerBlock = 10;
		
		int totalRecord = service.getTotalRecord(boardCd, searchWord);
		NumbersForPaging numbers = this.getNumbersForPaging(totalRecord, page, numPerPage, pagePerBlock);
		
		int startRecord = (page - 1) * numPerPage + 1;
		int endRecord = page * numPerPage;
		
		List<Article> list = service.getArticleList(boardCd, searchWord, startRecord, endRecord);
		int listItemNo = numbers.getListItemNo();
		int prevPage = numbers.getPrevBlock();
		int firstPage = numbers.getFirstPage();
		int lastPage = numbers.getLastPage();
		int nextPage = numbers.getNextBlock();
		int totalPage = numbers.getTotalPage();
		String boardNm = service.getBoardNm(boardCd);
		
		req.setAttribute("list", list);
		req.setAttribute("listItemNo", listItemNo);
		req.setAttribute("prevPage", prevPage);
		req.setAttribute("firstPage", firstPage);
		req.setAttribute("lastPage", lastPage);
		req.setAttribute("nextPage", nextPage);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("boardNm", boardNm);
		
		forward.setView("/bbs/list.jsp");
		
		return forward;
	}

}
