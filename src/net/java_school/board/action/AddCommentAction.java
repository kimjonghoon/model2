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

public class AddCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute(WebContants.USER_KEY);
		
		if (user == null) {
			throw new AuthenticationException(WebContants.NOT_LOGIN);
		}

		int articleNo = Integer.parseInt(req.getParameter("articleNo"));
		String boardCd = req.getParameter("boardCd");
		int curPage = Integer.parseInt(req.getParameter("curPage"));
		String searchWord = req.getParameter("searchWord");
		String memo = req.getParameter("memo");
		
		Comment comment = new Comment();
		comment.setArticleNo(articleNo);
		comment.setEmail(user.getEmail());
		comment.setMemo(memo);
		
		BoardService service = new BoardService();
		service.addComment(comment);
		
		searchWord = URLEncoder.encode(searchWord, "UTF-8");
		forward.setView("view.do?articleNo=" + articleNo + "&boardCd=" + boardCd + "&curPage=" + curPage + "&searchWord=" + searchWord);
		forward.setRedirect(true);
		
		return forward;
	}

}
