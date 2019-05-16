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

public class ChangeMyPasswordAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();

		HttpSession session = req.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute(WebContants.USER_KEY);

		if (userInfo == null) {
			throw new AuthenticationException(WebContants.NOT_LOGIN);
		}

		String currentPasswd = req.getParameter("currentPasswd");
		String newPasswd = req.getParameter("newPasswd");
		String email = userInfo.getUser().getEmail();

		UserService service = new UserService();
		
		int check = service.authentication(email, currentPasswd);
		
		if (check != 1) {
			throw new AuthenticationException(WebContants.AUTHENTICATION_FAILED);
		}
		service.changeMyPasswd(email, newPasswd);

		forward.setView("changePasswd_confirm.do");
		forward.setRedirect(true);

		return forward;
	}

}