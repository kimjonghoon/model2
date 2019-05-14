package net.java_school.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.commons.WebContants;
import net.java_school.user.User;
import net.java_school.user.UserService;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		String url = req.getParameter("url");
		String email = req.getParameter("email");
		String passwd = req.getParameter("passwd");

		UserService service = new UserService();
		User user = service.login(email, passwd);

		ActionForward forward = new ActionForward();

		if (user == null) {
			forward.setView("/users/login.do?url=" + url + "&msg=Login-Failed");
			forward.setRedirect(true);
		} else {
			HttpSession session = req.getSession();
			session.setAttribute(WebContants.USER_KEY, user);
			if (url != null && !url.equals("")) {
				forward.setView(url);
				forward.setRedirect(true);
			} else {
				forward.setView("/bbs/list.do?boardCd=chat&page=1");
				forward.setRedirect(true);
			}
		}

		return forward;
	}

}