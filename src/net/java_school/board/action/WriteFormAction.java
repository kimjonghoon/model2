package net.java_school.board.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.board.Board;
import net.java_school.board.BoardService;
import net.java_school.commons.WebContants;
import net.java_school.user.UserInfo;

public class WriteFormAction implements Action {

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

		String boardCd = req.getParameter("boardCd");

		BoardService service = new BoardService();
		String boardNm = service.getBoardNm(boardCd);
		List<Board> boards = service.getBoards();

		req.setAttribute("boardNm", boardNm);
		req.setAttribute("boards", boards);

		forward.setView("/bbs/write.jsp");

		return forward;
	}

}