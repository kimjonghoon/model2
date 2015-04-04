package net.java_school.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.commons.WebContants;

public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		
		HttpSession session = req.getSession();
		session.removeAttribute(WebContants.USER_KEY);
		
		ActionForward forward = new ActionForward();
		String contextPath = req.getContextPath();
		forward.setView(contextPath);
		forward.setRedirect(true);
		
		return forward;
	}

}
