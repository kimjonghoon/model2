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

public class ByeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();

		HttpSession session = req.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute(WebContants.USER_KEY);

		if (userInfo == null) {
			throw new AuthenticationException(WebContants.NOT_LOGIN);
		}

		String email = req.getParameter("email");
		String passwd = req.getParameter("passwd");

		UserService service = new UserService();
		int check = service.authentication(email, passwd);
		if (check != 1) {
			throw new AuthenticationException(WebContants.AUTHENTICATION_FAILED);
		}
		service.removeUser(email);
		
		session.removeAttribute(WebContants.USER_KEY);

		forward.setView("/users/bye_confirm.jsp");

		return forward;
	}

}