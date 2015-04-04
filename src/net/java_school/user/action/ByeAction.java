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

public class ByeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute(WebContants.USER_KEY);
		
		if (user == null) {
			throw new AuthenticationException(WebContants.NOT_LOGIN);
		}
		
		String email = req.getParameter("email");
		String passwd = req.getParameter("passwd");
		
		UserService service = new UserService();
		
		service.bye(email, passwd);
		session.removeAttribute(WebContants.USER_KEY);
		
		forward.setView("/users/bye_confirm.jsp");
		
		return forward;
	}

}
