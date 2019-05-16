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

public class ChangeUserPasswdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();

		HttpSession session = req.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute(WebContants.USER_KEY);

		if (userInfo == null || userInfo.isAdmin() == false) {
			throw new AuthenticationException(WebContants.NOT_ADMIN);
		}

		String page = req.getParameter("page");
		String search = req.getParameter("search");
		String email = req.getParameter("email");
		String passwd = req.getParameter("passwd");

		UserService service = new UserService();
		service.changeUserPasswd(email, passwd);

		forward.setView("editAccount.do?email=" + email + "&page=" + page + "&search=" + search);
		forward.setRedirect(true);

		return forward;
	}

}