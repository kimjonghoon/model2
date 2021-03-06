package net.java_school.board.action;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.board.Article;
import net.java_school.board.BoardService;
import net.java_school.commons.WebContants;
import net.java_school.exception.AuthenticationException;
import net.java_school.user.UserInfo;

public class DeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();

		HttpSession session = req.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute(WebContants.USER_KEY);

		int articleNo = Integer.parseInt(req.getParameter("articleNo"));

		BoardService service = new BoardService();
		Article article = service.getArticle(articleNo);

		if (userInfo == null) {
			throw new AuthenticationException(WebContants.AUTHENTICATION_FAILED);
		}

		if (!userInfo.getUser().getEmail().equals(article.getEmail()) && userInfo.isAdmin() == false) {
			throw new AuthenticationException(WebContants.AUTHENTICATION_FAILED);
		}

		service.removeArticle(articleNo);

		String boardCd = req.getParameter("boardCd");
		String page = req.getParameter("page");
		String searchWord = req.getParameter("searchWord");

		searchWord = URLEncoder.encode(searchWord, "UTF-8");

		forward.setView("list.do?boardCd=" + boardCd + "&page=" + page + "&searchWord=" + searchWord);
		forward.setRedirect(true);

		return forward;
	}

}
