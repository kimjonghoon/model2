package net.java_school.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.board.Article;
import net.java_school.board.Board;
import net.java_school.board.BoardService;
import net.java_school.commons.WebContants;
import net.java_school.exception.AuthenticationException;
import net.java_school.user.User;

public class ModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();
		
		int articleNo = Integer.parseInt(req.getParameter("articleNo"));
		String boardCd = req.getParameter("boardCd");
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute(WebContants.USER_KEY);
		
		BoardService service = new BoardService();
		Article article = service.getArticle(articleNo);
		
		if (user == null || !user.getEmail().equals(article.getEmail())) {
			throw new AuthenticationException(WebContants.AUTHENTICATION_FAILED);
		}
		
		String title = article.getTitle();
		String content = article.getContent();
		String boardNm = service.getBoardNm(boardCd);
		List<Board> boards = service.getAllBoard();
		
		req.setAttribute("title", title);
		req.setAttribute("content", content);
		req.setAttribute("boardNm", boardNm);
		req.setAttribute("boards", boards);
		
		forward.setView("/bbs/modify.jsp");
		
		return forward;
	}

}
