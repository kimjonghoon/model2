package net.java_school.board.action;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.board.BoardService;
import net.java_school.commons.WebContants;
import net.java_school.user.User;

public class WriteFormAction implements Action {

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
		
		BoardService service = new BoardService();
		String boardNm = service.getBoardNm(boardCd);
		
		req.setAttribute("boardNm", boardNm);
		
		forward.setView("/bbs/write_form.jsp");
		
		return forward;
	}

}
