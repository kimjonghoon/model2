package net.java_school.user.action;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.commons.WebContants;
import net.java_school.user.User;

public class ByeFormAction implements Action {

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
			forward.setView("/users/login.do?url=" + url);
			forward.setRedirect(true);
			
			return forward;
		}
		
		forward.setView("/users/bye.jsp");
		
		return forward;
	}

}
