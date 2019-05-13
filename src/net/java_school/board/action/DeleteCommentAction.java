package net.java_school.board.action;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.board.BoardService;
import net.java_school.board.Comment;
import net.java_school.commons.WebContants;
import net.java_school.exception.AuthenticationException;
import net.java_school.user.User;

public class DeleteCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute(WebContants.USER_KEY);

		int commentNo = Integer.parseInt(req.getParameter("commentNo"));

		BoardService service = new BoardService();
		Comment comment = service.getComment(commentNo);
		if (user == null || !user.getEmail().equals(comment.getEmail())) {
			throw new AuthenticationException(WebContants.AUTHENTICATION_FAILED);
		}

		int articleNo = Integer.parseInt(req.getParameter("articleNo"));
		String boardCd = req.getParameter("boardCd");
		int page = Integer.parseInt(req.getParameter("page"));
		String searchWord = req.getParameter("searchWord");

		service.removeComment(commentNo);

		searchWord = URLEncoder.encode(searchWord, "UTF-8");
		forward.setView("view.do?articleNo=" + articleNo + "&boardCd=" + boardCd + "&page=" + page + "&searchWord=" + searchWord);
		forward.setRedirect(true);

		return forward;
	}

}
