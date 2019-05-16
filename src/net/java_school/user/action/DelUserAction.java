package net.java_school.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.commons.WebContants;
import net.java_school.exception.AuthenticationException;
import net.java_school.user.UserInfo;
import net.java_school.user.UserService;

public class DelUserAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();

		HttpSession session = req.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute(WebContants.USER_KEY);

		if (userInfo.isAdmin() == false) {
			throw new AuthenticationException("You are not Admin!");
		}

		String email = req.getParameter("email");

		UserService service = new UserService();

		service.deleteUser(email);

		String page = req.getParameter("page");
		String search = req.getParameter("search");

		forward.setView("list.do?page=" + page + "&searchWord=" + search);
		forward.setRedirect(true);

		return forward;
	}

}
