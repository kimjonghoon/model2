package net.java_school.board.action;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.board.AttachFile;
import net.java_school.board.BoardService;
import net.java_school.commons.WebContants;
import net.java_school.exception.AuthenticationException;
import net.java_school.user.UserInfo;

public class DeleteAttachFileAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();

		HttpSession session = req.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute(WebContants.USER_KEY);

		int attachFileNo = Integer.parseInt(req.getParameter("attachFileNo"));

		BoardService service = new BoardService();
		AttachFile attachFile = service.getAttachFile(attachFileNo);

		if (userInfo == null) {
			throw new AuthenticationException(WebContants.AUTHENTICATION_FAILED);
		}

		if (!userInfo.getUser().getEmail().equals(attachFile.getEmail()) && userInfo.isAdmin() == false) {
			throw new AuthenticationException(WebContants.AUTHENTICATION_FAILED);
		}
		
		int articleNo = Integer.parseInt(req.getParameter("articleNo"));
		String boardCd = req.getParameter("boardCd");
		int page = Integer.parseInt(req.getParameter("page"));
		String searchWord = req.getParameter("searchWord");

		service.removeAttachFile(attachFileNo);

		searchWord = URLEncoder.encode(searchWord, "UTF-8");

		forward.setView("view.do?articleNo=" + articleNo + "&boardCd=" + boardCd + "&page=" + page + "&searchWord=" + searchWord);
		forward.setRedirect(true);

		return forward;
	}

}
