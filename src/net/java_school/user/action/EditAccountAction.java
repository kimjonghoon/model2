package net.java_school.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.commons.WebContants;
import net.java_school.exception.AuthenticationException;
import net.java_school.user.User;
import net.java_school.user.UserService;

public class EditAccountAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute(WebContants.USER_KEY);

		//전달되는 파라미터 name, mobile, passwd
		String name = req.getParameter("name");
		String mobile = req.getParameter("mobile");
		String passwd = req.getParameter("passwd");

		if (user == null) {
			throw new AuthenticationException(WebContants.NOT_LOGIN);
		}

		String email = user.getEmail();

		UserService service = new UserService();

		if (service.login(email, passwd) == null) {
			throw new AuthenticationException(WebContants.AUTHENTICATION_FAILED);
		}

		//user.setEmail(email);
		user.setPasswd(passwd);
		user.setName(name);
		user.setMobile(mobile);

		int check = service.editAccount(user);

		if (check > 0) {
			session.setAttribute(WebContants.USER_KEY, user);
			forward.setView("changePasswd.do");
			forward.setRedirect(true);
		} else {
			throw new AuthenticationException(WebContants.AUTHENTICATION_FAILED);
		}

		return forward;
	}

}
