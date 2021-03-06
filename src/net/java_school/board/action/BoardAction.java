package net.java_school.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.board.Board;
import net.java_school.board.BoardService;
import net.java_school.commons.WebContants;
import net.java_school.exception.AuthenticationException;
import net.java_school.user.UserInfo;

public class BoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();

		HttpSession session = req.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute(WebContants.USER_KEY);

		if (userInfo == null || userInfo.isAdmin() == false) {
			throw new AuthenticationException(WebContants.NOT_ADMIN);
		}

		BoardService service = new BoardService();
		List<Board> boards = service.getBoards();

		req.setAttribute("boards", boards);

		forward.setView("/admin/boards.jsp");

		return forward;
	}

}